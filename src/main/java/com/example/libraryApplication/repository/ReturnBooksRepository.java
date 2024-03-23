package com.example.libraryApplication.repository;

import com.example.libraryApplication.entity.ReturnedBooks;
import org.springframework.data.repository.CrudRepository;

public interface ReturnBooksRepository extends CrudRepository<ReturnedBooks,Long> {
}
