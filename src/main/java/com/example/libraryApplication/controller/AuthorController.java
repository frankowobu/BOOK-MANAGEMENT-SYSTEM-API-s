package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.service.author.AuthorServiceImpl;
import com.example.libraryApplication.service.books.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;
    @Autowired
    BookServiceImpl bookService;
    @PostMapping
    public ResponseEntity<HttpStatus> addAuthor(@RequestBody AuthorDto authorDto){
        authorService.addAuthor(authorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable(name = "id" ) Long authorId){
        return new ResponseEntity<>(authorService.getAuthor(authorId),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable(name = "id") Long authorId){
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody AuthorDto author){
        return new ResponseEntity<>(authorService.updateAuthor(author),HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthor(),HttpStatus.OK);
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<List<AuthorBooksDto>> getAllBookOfAuthor(@PathVariable(name = "id")Long authorId){
        return new ResponseEntity<>(bookService.getAllBooksOfAuthor(authorId),HttpStatus.ACCEPTED);
    }
}
