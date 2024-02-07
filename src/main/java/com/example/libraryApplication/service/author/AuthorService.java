package com.example.libraryApplication.service.author;



import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.dto.authorDto.SearchAuthorDto;
import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.pojo.Books;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    void addAuthor(AuthorDto authorDto);
    Author getAuthor(Long authorId);
    Author findAuthor(String authorName);
    AuthorDto convertAuthorToAuthorDto(Author author);
    Author updateAuthor(AuthorDto authorDto);
    void deleteAuthor(Long authorId);
    List<AuthorDto> getAllAuthor();

}
