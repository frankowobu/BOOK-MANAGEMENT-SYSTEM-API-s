package com.example.libraryApplication.service.user;

import com.example.libraryApplication.dto.usersdto.UserSignIn;
import com.example.libraryApplication.dto.usersdto.UserSignUp;
import com.example.libraryApplication.exception.UserFoundException;
import com.example.libraryApplication.pojo.Role;
import com.example.libraryApplication.pojo.Users;
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
    public void createUsers(UserSignUp userSignUp) {
        Optional<Users> optionalUsers = userRepository.findByEmail(userSignUp.getEmail());
        if (!optionalUsers.isPresent()){
            Users createUsers = new Users();
            createUsers.setFirstName(userSignUp.getFirstName());
            createUsers.setLastName(userSignUp.getLastName());
            createUsers.setEmail(userSignUp.getEmail());
            createUsers.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
            createUsers.setRole(Role.LIBRARIAN);
            userRepository.save(createUsers);
        }
        else throw new UserFoundException(userSignUp.getEmail());
    }

    @Override
    public Users getUser(String email) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (optionalUsers.isPresent()){
            return optionalUsers.get();
        }
        else throw new UserFoundException(email);
    }

//    @Override
//    public void loginUser(UserSignIn userSignIn) {
//
//    }
}
