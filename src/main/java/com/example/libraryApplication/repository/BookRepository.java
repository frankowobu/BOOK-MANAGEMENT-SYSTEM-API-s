package com.example.libraryApplication.repository;

import com.example.libraryApplication.dto.authorDto.SearchAuthorDto;
import com.example.libraryApplication.entity.Books;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Books,Long> {
    List<Books> findByAuthorId(Long authorId);
    List<Books> findByAuthor(SearchAuthorDto authorName);
}
