package com.example.libraryApplication.service.user;

import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.entity.Users;

public interface UserService {
   Users getUser(String email);
   void loginUser(UserSignIn userSignIn);
   Users getUserByEmail(String email);
}
