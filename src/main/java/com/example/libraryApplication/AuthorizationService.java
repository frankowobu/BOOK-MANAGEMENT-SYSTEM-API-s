package com.example.libraryApplication;

import com.example.libraryApplication.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationService {
    public static boolean isLibrarianAuthenticated(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals(Role.LIBRARIAN.name()));
    }
}
