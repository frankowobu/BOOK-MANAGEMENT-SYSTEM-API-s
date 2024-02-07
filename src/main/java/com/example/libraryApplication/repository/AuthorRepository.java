package com.example.libraryApplication.repository;

import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.dto.authorDto.SearchAuthorDto;
import com.example.libraryApplication.pojo.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    Optional<Author> findByFirstName(String firstName);

}
