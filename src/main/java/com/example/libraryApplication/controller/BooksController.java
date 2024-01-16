package com.example.libraryApplication.controller;

import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.pojo.Books;
import com.example.libraryApplication.service.BookServiceImpl;
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
    public ResponseEntity<Books> addBook(@RequestBody Books books){
        return new ResponseEntity<>(bookService.addBook(books), HttpStatus.CREATED);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Books> getBook(@PathVariable Long bookId){
        return new ResponseEntity<>(bookService.getBook(bookId),HttpStatus.OK);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Books>> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBook(),HttpStatus.OK);
    }
    @PostMapping("author/{authorId}")
    public ResponseEntity<Books> addBookByAuthorId(@RequestBody Books books, @PathVariable Long authorId){
        return new ResponseEntity<>(bookService.addBookByAuthorId(books,authorId),HttpStatus.CREATED);
    }
    @PutMapping("update/author/{authorId}")
    public ResponseEntity<Books> updateBookAuthorId(@RequestBody Author author, @PathVariable Long authorId){
        return new ResponseEntity<>(bookService.updateBookAuthorId(author,authorId),HttpStatus.OK);
    }
    @GetMapping("author/{authorId}")
    public ResponseEntity<List<Books>> getBookByAuthorId(@PathVariable Long authorId){
        return new ResponseEntity<>(bookService.getBookByAuthorId(authorId),HttpStatus.OK);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<List<Books>> getBookByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(bookService.getBookByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("author/{authorId}/user/{userId}")
    public ResponseEntity<List<Books>> getBookByUserIdAndAuthorId(@PathVariable Long authorId, @PathVariable Long userId){
        return new ResponseEntity<>(bookService.getBookByUserIdAndAuthorId(authorId,userId),HttpStatus.OK);
    }
    @DeleteMapping("delete/author/{authorId}")
    public ResponseEntity<HttpStatus> deleteBookAuthorId(@PathVariable Long authorId){
        bookService.deleteBookAuthorId(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("author/{userId}")
    public ResponseEntity<HttpStatus> deleteBookUerId(@PathVariable Long userId){
        bookService.deleteBookUerId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("author/{authorId}/user/{userId}")
    public ResponseEntity<HttpStatus> deleteBookByUserIdAndAuthorId(@PathVariable Long authorId, @PathVariable Long userId){
        bookService.deleteBookByUserIdAndAuthorId(authorId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
