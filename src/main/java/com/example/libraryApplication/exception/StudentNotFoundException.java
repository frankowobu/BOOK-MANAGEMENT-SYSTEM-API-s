package com.example.libraryApplication.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("student with id " + id +" does not exit in our records");
    }
    public StudentNotFoundException(String email){
        super(email + " exit in our records");
    }
}
