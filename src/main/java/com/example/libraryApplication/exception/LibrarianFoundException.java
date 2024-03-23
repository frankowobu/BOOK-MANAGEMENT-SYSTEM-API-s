package com.example.libraryApplication.exception;

public class LibrarianFoundException extends RuntimeException{
    public LibrarianFoundException(String email ) {
        super(email + " exist in our records");
    }
}
