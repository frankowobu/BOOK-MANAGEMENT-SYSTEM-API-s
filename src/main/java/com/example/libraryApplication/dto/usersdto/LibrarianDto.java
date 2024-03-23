package com.example.libraryApplication.dto.usersdto;

import lombok.Data;

@Data
public class LibrarianDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
