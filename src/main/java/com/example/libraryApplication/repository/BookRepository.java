package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.Books;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Books,Long> {
    List<Books> findByUserIdAndAuthorId(Long userId, Long authorId);
    List<Books> findByAuthorId(Long authorId);
    List<Books> findByUserId(Long userId);
    void deleteByAuthorIdAndUserId(Long userId, Long authorId);
}
