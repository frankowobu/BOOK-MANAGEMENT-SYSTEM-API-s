package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.borrowedBookDto.BorrowedBooksDto;
import com.example.libraryApplication.dto.borrowedBookDto.ValidateBorrowedBook;
import com.example.libraryApplication.service.borrowedbook.BorrowedBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowed")
public class BorrowedBooksController {
    @Autowired
    BorrowedBookServiceImpl borrowedBookService;
    @PostMapping
    public ResponseEntity<HttpStatus> createBorrowBooks(@RequestBody BorrowedBooksDto borrowedBooksDto){
        borrowedBookService.createBorrowBooks(borrowedBooksDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BorrowedBooksDto>> getAllBorrowedBooks(){
        return new ResponseEntity<>(borrowedBookService.getBorrowBooks(),HttpStatus.ACCEPTED);
    }
    @PutMapping("/validate")
    public ResponseEntity<HttpStatus> validateBorrowedBooks(@RequestBody ValidateBorrowedBook borrowedBook){
        borrowedBookService.validateBorrowedBookStatus(borrowedBook);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
