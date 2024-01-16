package com.example.libraryApplication.controller;

import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.service.AuthorServiceImpl;
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
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author author1 = authorService.addAuthor(author);
        return new ResponseEntity<>(author1, HttpStatus.CREATED);
    }
    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId){
        return new ResponseEntity<>(authorService.getAuthor(authorId),HttpStatus.OK);
    }
    @DeleteMapping("delete/{authorId}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("update/{authorId}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,@PathVariable Long authorId){
        return new ResponseEntity<>(authorService.updateAuthor(author,authorId),HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthor(),HttpStatus.OK);
    }
}
