package com.example.libraryApplication.service.user;

import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.entity.Users;
import com.example.libraryApplication.exception.UserFoundException;
import com.example.libraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public Users getUser(String email) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (optionalUsers.isPresent()){
            return optionalUsers.get();
        }
        else throw new UserFoundException(email);
    }
    @Override
    public void loginUser(UserSignIn userSignIn) {
        Optional<Users> optionalUsers = userRepository.findByEmail(userSignIn.getEmail());
        if (optionalUsers.isPresent()){
            Users saveUser = new Users();
            saveUser.setEmail(userSignIn.getEmail());
            saveUser.setPassword(passwordEncoder.encode(userSignIn.getPassword()));
            userRepository.save(saveUser);
        }
        else
            throw new UserFoundException(userSignIn.getEmail());
    }

    @Override
    public Users getUserByEmail(String email) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (optionalUsers.isPresent()){
            return optionalUsers.get();
        }
        else throw new UserFoundException(email);
    }

}
