package com.example.libraryApplication.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BOOKS")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @NonNull
    @Column(name = "book_title",nullable = false)
    private String title;
    @NonNull
    @Column(name = "book_pages",nullable = false)
    private Long pages;
    @NonNull
    @Column(name = "book_summary",nullable = false)
    private String summary;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;
    @JsonIgnore
    @OneToMany(mappedBy = "books",cascade = CascadeType.ALL)
    private List<BorrowedBooks> borrowedBooks;
    @JsonIgnore
    @OneToMany(mappedBy = "books",cascade = CascadeType.ALL)
    private List<ReturnedBooks> returnedBooks ;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private BookStatus bookStatus;
}