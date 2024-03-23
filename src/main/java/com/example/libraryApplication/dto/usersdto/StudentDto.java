package com.example.libraryApplication.dto.usersdto;

import lombok.Data;
@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String department;
}
