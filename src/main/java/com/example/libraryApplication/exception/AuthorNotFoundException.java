package com.example.libraryApplication.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("The author with id '" + id + "' does not exist in our records");
    }
    public AuthorNotFoundException(String authorName){
        super(authorName + " exits already in our records");
    }
}
