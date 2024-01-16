package com.example.libraryApplication.service;

import com.example.libraryApplication.exception.AuthorNotFoundException;
import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.repository.AuthorRepository;
import com.example.libraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AuthorServiceImpl implements AuthorService{
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthor(Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            return author.get();
        }
        else throw new AuthorNotFoundException(authorId);
    }

    @Override
    public Author updateAuthor(Author author, Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()){
            Author updatedAuthor = optionalAuthor.get();
            updatedAuthor.setFirstName(author.getFirstName());
            updatedAuthor.setLastName(author.getLastName());
            return authorRepository.save(updatedAuthor);
        }
        else
            throw new AuthorNotFoundException(authorId);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<Author> getAllAuthor() {
        return (List<Author>) authorRepository.findAll();
    }

}
