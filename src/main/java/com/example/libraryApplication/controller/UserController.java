package com.example.libraryApplication.controller;


import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.dto.usersdto.UserSignUp;
import com.example.libraryApplication.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserSignUp userSignUp){
        userService.createUsers(userSignUp);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> signInUser(@RequestBody UserSignIn userSignIn){
        userService.loginUser(userSignIn);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
