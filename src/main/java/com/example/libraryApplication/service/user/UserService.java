package com.example.libraryApplication.service.user;

import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.dto.usersdto.UserSignUp;
import com.example.libraryApplication.pojo.Users;

import java.util.List;

public interface UserService {
   void createUsers(UserSignUp userSignUp);
   Users getUser(String email);
   void loginUser(UserSignIn userSignIn);
}
