package com.example.libraryApplication.exception;

public class BooksNotFoundException extends RuntimeException{
    public BooksNotFoundException(Long id) {
        super("The book with id '" + id + "' does not exist in our records");
    }
}
