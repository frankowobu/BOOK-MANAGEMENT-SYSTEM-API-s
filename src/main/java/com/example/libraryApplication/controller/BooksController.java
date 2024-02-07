package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.pojo.Books;
import com.example.libraryApplication.service.books.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    BookServiceImpl bookService;
    @PostMapping
    public ResponseEntity<HttpStatus> createBook(@RequestBody BookDto bookDto){
        bookService.createBook(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBooks(@PathVariable(name = "id") Long bookId){
        return new ResponseEntity<>(bookService.getBook(bookId),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBooks(@PathVariable(name = "id") Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Books> updateBooks(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.updateBook(bookDto),HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBook(),HttpStatus.OK);
    }

}
