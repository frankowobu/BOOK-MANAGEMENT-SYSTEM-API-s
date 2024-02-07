package com.example.libraryApplication.exception;

public class UserFoundException extends RuntimeException{
    public UserFoundException(String email ) {
        super(email + " exist in our records");
    }
}
