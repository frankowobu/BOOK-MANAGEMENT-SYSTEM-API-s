package com.example.libraryApplication.service.author;

import com.example.libraryApplication.dto.authorDto.AuthorDto;
import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.exception.AuthorNotFoundException;
import com.example.libraryApplication.pojo.Author;

import com.example.libraryApplication.pojo.Books;
import com.example.libraryApplication.repository.AuthorRepository;
import com.example.libraryApplication.repository.BookRepository;
import com.example.libraryApplication.service.books.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    AuthorRepository authorRepository;
    BookRepository bookRepository;

    @Override
    public void addAuthor(AuthorDto authorDto) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstName(authorDto.getFirstName());
        if (!optionalAuthor.isPresent()){
            Author createAuthor = new Author();
            createAuthor.setFirstName(authorDto.getFirstName());
            createAuthor.setLastName(authorDto.getLastName());
            authorRepository.save(createAuthor);
        }
        else throw new AuthorNotFoundException(authorDto.getFirstName());
    }

    @Override
    public Author getAuthor(Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()){
            return optionalAuthor.get();
        }
        else
            throw new AuthorNotFoundException(authorId);
    }

    @Override
    public Author findAuthor(String authorName) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstName(authorName);
        if (optionalAuthor.isPresent()){
            return optionalAuthor.get();
        }
        else throw new AuthorNotFoundException(authorName);
    }

    @Override
    public AuthorDto convertAuthorToAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setLastName(author.getLastName());
        authorDto.setFirstName(author.getFirstName());

        return authorDto;
    }

    @Override
    public Author updateAuthor(AuthorDto authorDto) {
        Author updateAuthor = getAuthor(authorDto.getId());
        updateAuthor.setLastName(authorDto.getLastName());
        updateAuthor.setFirstName(authorDto.getFirstName());
        return authorRepository.save(updateAuthor);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Author deleteAuthor = getAuthor(authorId);
        authorRepository.deleteById(deleteAuthor.getId());
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        List<Author> authorList = (List<Author>) authorRepository.findAll();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author item : authorList){
            authorDtoList.add(convertAuthorToAuthorDto(item));
        }
        return authorDtoList;
    }


}
