package com.example.libraryApplication.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @NonNull
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @NonNull
    @Column(name = "email",nullable = false)
    private String email;
    @NonNull
    @Column(name = "password",nullable = false)
    private String password;
    @NonNull
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
