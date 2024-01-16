package com.example.libraryApplication.service;

import com.example.libraryApplication.exception.UserNotFoundException;
import com.example.libraryApplication.pojo.UserLogin;
import com.example.libraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserLogin addUser(UserLogin user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserLogin getUser(Long userId) {
        Optional<UserLogin> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        else throw new UserNotFoundException(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserLogin> getAllUser() {
        return (List<UserLogin>) userRepository.findAll();
    }
}
