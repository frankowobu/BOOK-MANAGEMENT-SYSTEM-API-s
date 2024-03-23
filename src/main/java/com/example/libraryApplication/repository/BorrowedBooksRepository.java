package com.example.libraryApplication.repository;

import com.example.libraryApplication.entity.BorrowedBooks;
import org.springframework.data.repository.CrudRepository;

public interface BorrowedBooksRepository extends CrudRepository<BorrowedBooks,Long> {
}
