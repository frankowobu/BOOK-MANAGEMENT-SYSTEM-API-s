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
                .antMatchers(HttpMethod.GET, "/book/**").hasAnyRole("LIBRARIAN", "STUDENT")
                .antMatchers(HttpMethod.POST, "/book/**").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.PUT, "/book/**").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/book/**").hasRole("LIBRARIAN")

                // Librarian Controller
                .antMatchers(HttpMethod.GET, "/librarian/**").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.PUT, "/librarian/**").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/librarian/**").hasRole("LIBRARIAN")

                // Student Controller
                .antMatchers(HttpMethod.GET, "/student/**").hasRole("STUDENT")
                .antMatchers(HttpMethod.PUT, "/student/**").hasRole("STUDENT")
                .antMatchers(HttpMethod.DELETE, "/student/**").hasRole("STUDENT")

                // Borrowed Books Controller
                .antMatchers(HttpMethod.GET, "/borrowed/**").hasAnyRole("LIBRARIAN", "STUDENT")
                .antMatchers(HttpMethod.POST, "/borrowed/**").hasAnyRole("LIBRARIAN", "STUDENT")
                .antMatchers(HttpMethod.PUT, "/borrowed/**").hasRole("LIBRARIAN")

                // Returned Books Controller
                .antMatchers(HttpMethod.GET, "/returned/**").hasAnyRole("LIBRARIAN", "STUDENT")
                .antMatchers(HttpMethod.POST, "/returned/**").hasAnyRole("LIBRARIAN","STUDENT")


                .anyRequest().authenticated()
                .and()
                .addFilterBefore(exceptionHandlerFilter, AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtAuthenticationFilter, AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
