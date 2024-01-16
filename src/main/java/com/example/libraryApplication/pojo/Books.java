package com.example.libraryApplication.pojo;


import lombok.*;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserLogin user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

}
