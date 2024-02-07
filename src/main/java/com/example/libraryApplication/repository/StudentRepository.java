package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.Student;
import com.example.libraryApplication.pojo.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Optional<Student> findByEmail(String email);
}
