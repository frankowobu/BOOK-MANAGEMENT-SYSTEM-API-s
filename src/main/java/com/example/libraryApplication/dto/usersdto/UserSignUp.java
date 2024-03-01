package com.example.libraryApplication.dto.usersdto;

import com.example.libraryApplication.pojo.Role;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
public class UserSignUp {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
