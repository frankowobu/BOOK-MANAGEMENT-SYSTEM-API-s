package com.example.libraryApplication.controller;

import com.example.libraryApplication.AuthorizationService;
import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.dto.authorDto.SearchAuthorDto;
import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.entity.Author;
import com.example.libraryApplication.service.author.AuthorServiceImpl;
import com.example.libraryApplication.service.books.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;
    @Autowired
    BookServiceImpl bookService;
    @PostMapping("/create")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDto authorDto) {
        try {
            authorService.addAuthor(authorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Author's profile created");
        } catch (RuntimeException e) {
            String errorMessage = "Only librarians can create authors.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
        }
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
    @GetMapping("/books")
    public ResponseEntity<List<AuthorBooksDto>> getAllBookOfAuthor(@RequestBody SearchAuthorDto authorInfo){
        return new ResponseEntity<>(bookService.getAllBooksOfAuthor(authorInfo),HttpStatus.ACCEPTED);
    }
}
