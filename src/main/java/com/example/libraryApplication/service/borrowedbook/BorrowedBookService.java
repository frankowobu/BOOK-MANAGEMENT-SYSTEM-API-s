package com.example.libraryApplication.service.borrowedbook;

import com.example.libraryApplication.dto.borrowedBookDto.BorrowedBooksDto;
import com.example.libraryApplication.dto.borrowedBookDto.ValidateBorrowedBook;
import com.example.libraryApplication.entity.BorrowedBooks;

import java.util.List;

public interface BorrowedBookService {
    void createBorrowBooks(BorrowedBooksDto borrowedBooksDto);
    void validateBorrowedBookStatus(ValidateBorrowedBook borrowedBook);
    BorrowedBooks getBorrowedBooksById(Long borrowedBooksId);
    BorrowedBooksDto changeBorrowBooksToBorrowBooksDto(BorrowedBooks borrowedBooks);
    List<BorrowedBooksDto> getBorrowBooks();
}
