package com.example.libraryApplication.service.books;

import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.pojo.BookStatus;
import com.example.libraryApplication.pojo.Books;

import java.awt.print.Book;
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
    List<AuthorBooksDto> getAllBooksOfAuthor(Long authorId);
}
