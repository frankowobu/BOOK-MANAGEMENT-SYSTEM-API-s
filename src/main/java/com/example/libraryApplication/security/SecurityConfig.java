package com.example.libraryApplication.security;

import com.example.libraryApplication.entity.Role;
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


@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        ExceptionHandlerFilter exceptionHandlerFilter = new ExceptionHandlerFilter();
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**", "/user/signup/**").permitAll()
                .and().authorizeRequests()

                // Author Controller
                .antMatchers(HttpMethod.POST, "/author/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.GET, "/author/**").hasAnyAuthority(Role.LIBRARIAN.name(), Role.STUDENT.name())
                .antMatchers(HttpMethod.PUT, "/author/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.DELETE, "/author/**").hasAnyAuthority(Role.LIBRARIAN.name())

                // Book Controller
                .antMatchers(HttpMethod.GET, "/book/**").hasAnyAuthority(Role.LIBRARIAN.name(),Role.STUDENT.name())
                .antMatchers(HttpMethod.POST, "/book/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.PUT, "/book/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.DELETE, "/book/**").hasAnyAuthority(Role.LIBRARIAN.name())

                // Librarian Controller
                .antMatchers(HttpMethod.GET, "/librarian/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.PUT, "/librarian/**").hasAnyAuthority(Role.LIBRARIAN.name())
                .antMatchers(HttpMethod.DELETE, "/librarian/**").hasAnyAuthority(Role.LIBRARIAN.name())

                // Student Controller
                .antMatchers(HttpMethod.GET, "/student/**").hasAnyAuthority(Role.STUDENT.name())
                .antMatchers(HttpMethod.PUT, "/student/**").hasAnyAuthority(Role.STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAnyAuthority(Role.STUDENT.name())

                // Borrowed Books Controller
                .antMatchers(HttpMethod.GET, "/borrowed/**").hasAnyAuthority(Role.LIBRARIAN.name(),Role.STUDENT.name())
                .antMatchers(HttpMethod.POST, "/borrowed/**").hasAnyAuthority(Role.LIBRARIAN.name(),Role.STUDENT.name())
                .antMatchers(HttpMethod.PUT, "/borrowed/**").hasAnyAuthority(Role.LIBRARIAN.name())

                // Returned Books Controller
                .antMatchers(HttpMethod.GET, "/returned/**").hasAnyAuthority(Role.LIBRARIAN.name(),Role.STUDENT.name())
                .antMatchers(HttpMethod.POST, "/returned/**").hasAnyAuthority(Role.LIBRARIAN.name(),Role.STUDENT.name())


                .anyRequest().authenticated()
                .and()
                .addFilterBefore(exceptionHandlerFilter, AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtAuthenticationFilter, AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
