package com.example.libraryApplication.security.manager;

import com.example.libraryApplication.entity.Librarian;
import com.example.libraryApplication.entity.Users;
import com.example.libraryApplication.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    UserServiceImpl userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Users users = userService.getUser(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), users.getPassword())){
            throw new BadCredentialsException("the password you provided is incorrect");
        }
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(users.getRole().name()));
        return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials(),authority);
    }
}
