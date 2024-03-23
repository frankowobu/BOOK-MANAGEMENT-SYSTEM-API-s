package com.example.libraryApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "student_name",nullable = false)
    private String name;
    @NonNull
    @Column(name = "email",nullable = false)
    private String email;
    @NonNull
    @Column(name = "password",nullable = false)
    private String password;
    @NonNull
    @Column(name = "gender",nullable = false)
    private String gender;
    @NonNull
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @NonNull
    @Column(name = "student_department",nullable = false)
    private String department;
    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<BorrowedBooks> borrowedBooks;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;
}
