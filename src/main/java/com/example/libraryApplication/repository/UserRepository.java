package com.example.libraryApplication.repository;

import com.example.libraryApplication.pojo.UserLogin;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<UserLogin,Long> {

}
