package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
