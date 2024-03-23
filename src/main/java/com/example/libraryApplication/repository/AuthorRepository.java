package com.example.libraryApplication.repository;

import com.example.libraryApplication.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    Optional<Author> findByFirstName(String firstName);

}
