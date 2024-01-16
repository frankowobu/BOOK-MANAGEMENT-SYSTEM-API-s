package com.example.libraryApplication.service;



import com.example.libraryApplication.pojo.Author;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);
    Author getAuthor(Long authorId);
    Author updateAuthor(Author author, Long authorId);
    void deleteAuthor(Long authorId);
    List<Author> getAllAuthor();

}
