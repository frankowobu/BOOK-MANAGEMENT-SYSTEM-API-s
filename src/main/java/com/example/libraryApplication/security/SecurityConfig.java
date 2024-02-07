package com.example.libraryApplication.security;

import com.example.libraryApplication.security.filter.AuthenticationFilter;
import com.example.libraryApplication.security.filter.ExceptionHandlerFilter;
import com.example.libraryApplication.security.filter.JWTAuthenticationFilter;
import com.example.libraryApplication.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        ExceptionHandlerFilter exceptionHandlerFilter = new ExceptionHandlerFilter();
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers(HttpMethod.POST,SecurityConstant.register_path).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(exceptionHandlerFilter, AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtAuthenticationFilter, AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
