package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.Books;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Books,Long> {
    List<Books> findByAuthorId(Long authorId);
}
