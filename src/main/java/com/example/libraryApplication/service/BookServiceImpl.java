package com.example.libraryApplication.service;

import com.example.libraryApplication.exception.AuthorNotFoundException;
import com.example.libraryApplication.exception.BooksNotFoundException;
import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.pojo.Books;
import com.example.libraryApplication.repository.AuthorRepository;
import com.example.libraryApplication.repository.BookRepository;
import com.example.libraryApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookService;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Books addBook(Books books) {
        return bookService.save(books);
    }

    @Override
    public Books getBook(Long bookId) {
        Optional<Books> booksOptional = bookService.findById(bookId);
        if (booksOptional.isPresent()){
            return booksOptional.get();
        }
        else
            throw new BooksNotFoundException(bookId);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookService.deleteById(bookId);
    }

    @Override
    public List<Books> getAllBook() {
        return (List<Books>) bookService.findAll();
    }

    @Override
    public Books addBookByAuthorId(Books books, Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()){
            Author author = optionalAuthor.get();
            books.setAuthor(author);
            return bookService.save(books);
        }
        else
            throw new AuthorNotFoundException(authorId);
    }

    @Override
    public Books updateBookAuthorId(Author author, Long authorId) {
        Optional<Books> optionalBooks = bookService.findById(authorId);
        if (optionalBooks.isPresent()){
            Books books1 = optionalBooks.get();
            books1.setAuthor(author);
            return bookService.save(books1);
        }
        else throw new AuthorNotFoundException(authorId);
    }

    @Override
    public List<Books> getBookByAuthorId(Long authorId) {
        return bookService.findByAuthorId(authorId);
    }

    @Override
    public List<Books> getBookByUserId(Long userId) {
        return bookService.findByUserId(userId);
    }

    @Override
    public List<Books> getBookByUserIdAndAuthorId(Long authorId, Long userId) {
        return bookService.findByUserIdAndAuthorId(authorId,userId);
    }

    @Override
    public void deleteBookAuthorId(Long authorId) {
        bookService.deleteById(authorId);
    }

    @Override
    public void deleteBookUerId(Long userId) {
        bookService.deleteById(userId);
    }

    @Override
    public void deleteBookByUserIdAndAuthorId(Long authorId, Long userId) {
        bookService.deleteByAuthorIdAndUserId(authorId,userId);
    }
}
