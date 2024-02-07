package com.example.libraryApplication.service.borrowedbook;

import com.example.libraryApplication.dto.borrowedBookDto.BorrowedBooksDto;
import com.example.libraryApplication.pojo.BorrowedBooks;

import java.util.List;

public interface BorrowedBookService {
    void createBorrowBooks(BorrowedBooksDto borrowedBooksDto);
    BorrowedBooksDto changeBorrowBooksToBorrowBooksDto(BorrowedBooks borrowedBooks);
    List<BorrowedBooksDto> getBorrowBooks();
}
