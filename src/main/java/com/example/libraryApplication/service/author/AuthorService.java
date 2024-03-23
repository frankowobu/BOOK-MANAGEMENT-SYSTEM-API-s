package com.example.libraryApplication.service.author;



import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.entity.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorDto authorDto);
    Author getAuthor(Long authorId);
    Author findAuthor(String authorName);
    AuthorDto convertAuthorToAuthorDto(Author author);
    Author updateAuthor(AuthorDto authorDto);
    void deleteAuthor(Long authorId);
    List<AuthorDto> getAllAuthor();

}
