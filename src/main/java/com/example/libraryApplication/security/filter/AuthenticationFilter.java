package com.example.libraryApplication.security.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.security.SecurityConstant;
import com.example.libraryApplication.security.manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    CustomAuthenticationManager customAuthenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserSignIn userSignIn = new ObjectMapper().readValue(request.getInputStream(),UserSignIn.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userSignIn.getEmail(),userSignIn.getPassword());
            return customAuthenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        List<String> roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = com.auth0.jwt.JWT.create()
                .withSubject(authResult.getName())
                .withClaim("role", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET_KEY));
        response.addHeader(SecurityConstant.AUTHORIZATION, SecurityConstant.BEARER + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
