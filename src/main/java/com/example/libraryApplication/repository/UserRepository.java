package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
}
