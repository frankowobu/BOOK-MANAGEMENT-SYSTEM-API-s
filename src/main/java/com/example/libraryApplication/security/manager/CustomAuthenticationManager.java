package com.example.libraryApplication.security.manager;

import com.example.libraryApplication.pojo.Users;
import com.example.libraryApplication.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
        return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials());
    }
}
