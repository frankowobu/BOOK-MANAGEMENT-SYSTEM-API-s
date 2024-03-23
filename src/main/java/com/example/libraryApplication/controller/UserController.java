package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.usersdto.LibrarianDto;
import com.example.libraryApplication.dto.usersdto.StudentDto;
//import com.example.libraryApplication.service.librarian.LibrarianServiceImpl;
import com.example.libraryApplication.service.librarian.LibrarianServiceImpl;
import com.example.libraryApplication.service.student.StudentServiceImpl;
import com.example.libraryApplication.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserServiceImpl userService;
    LibrarianServiceImpl librarianService;
    StudentServiceImpl studentService;
    @PostMapping("/signup/librarian")
    public ResponseEntity<HttpStatus> signUpLibrarian(@RequestBody LibrarianDto librarianDto){
        librarianService.createLibrarian(librarianDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/signup/student")
    public ResponseEntity<HttpStatus> signUpStudent(@RequestBody StudentDto studentDto){
        studentService.createStudent(studentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
