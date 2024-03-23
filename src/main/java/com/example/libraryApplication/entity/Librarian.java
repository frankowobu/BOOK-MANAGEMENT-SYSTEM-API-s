package com.example.libraryApplication.entity;

import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LIBRARIAN")
public class Librarian {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

}
