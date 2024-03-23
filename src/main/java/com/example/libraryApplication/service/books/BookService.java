package com.example.libraryApplication.service.books;

import com.example.libraryApplication.dto.authorDto.SearchAuthorDto;
import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.entity.BookStatus;
import com.example.libraryApplication.entity.Books;

import java.util.List;

public interface BookService {
    void createBook(BookDto bookDto);
    Books getBook(Long bookId);
    Books updateBook(BookDto bookDto);
    void updateBookStatus(Long bookId, BookStatus bookStatus);
    void deleteBook(Long bookId);
    BookDto changeBookToBookDto(Books book);
    AuthorBooksDto changeBookToAuthorBookDto(Books books);
    List<BookDto> getAllBook();
    List<AuthorBooksDto> getAllBooksOfAuthor(SearchAuthorDto authorInfo);
}
