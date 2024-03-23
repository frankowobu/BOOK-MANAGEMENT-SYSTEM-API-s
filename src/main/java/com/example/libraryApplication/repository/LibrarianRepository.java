package com.example.libraryApplication.repository;

import com.example.libraryApplication.entity.Librarian;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LibrarianRepository extends CrudRepository<Librarian,Long> {
    Optional<Librarian> findByEmail(String email);
}
