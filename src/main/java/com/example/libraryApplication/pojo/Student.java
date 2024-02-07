package com.example.libraryApplication.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Column(name = "student_email",nullable = false)
    private String email;
    @NonNull
    @Column(name = "student_department",nullable = false)
    private String department;

    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<BorrowedBooks> borrowedBooks;
//    @ManyToMany
//    @JoinTable(name = "STUDENT_AUTHOR",
//            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id",referencedColumnName = "id")
//    )
//    private Set<Author> authors;

}
