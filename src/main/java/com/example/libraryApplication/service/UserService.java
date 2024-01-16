package com.example.libraryApplication.service;

import com.example.libraryApplication.pojo.UserLogin;

import java.util.List;

public interface UserService {
    UserLogin addUser(UserLogin user);
    UserLogin getUser(Long userId);
    void deleteUser(Long userId);
    List<UserLogin> getAllUser();
}
