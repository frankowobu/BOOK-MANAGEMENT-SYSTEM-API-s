package com.example.libraryApplication.service;

import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.pojo.Books;


import java.util.List;

public interface BookService {
    Books addBook(Books books);
    Books getBook(Long bookId);
    void deleteBook(Long bookId);
    List<Books> getAllBook();
    Books addBookByAuthorId(Books books, Long authorId);
    Books updateBookAuthorId(Author author, Long authorId);
    List<Books> getBookByAuthorId(Long authorId);
    List<Books> getBookByUserId(Long userId);
    List<Books> getBookByUserIdAndAuthorId(Long authorId, Long userId);
    void deleteBookAuthorId(Long authorId);
    void deleteBookUerId(Long userId);
    void deleteBookByUserIdAndAuthorId(Long userId, Long authorId);
}
