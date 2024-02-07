package com.example.libraryApplication.service.books;

import com.example.libraryApplication.dto.booksDto.AuthorBooksDto;
import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.exception.BooksNotFoundException;
import com.example.libraryApplication.pojo.Author;
import com.example.libraryApplication.pojo.BookStatus;
import com.example.libraryApplication.pojo.Books;
import com.example.libraryApplication.repository.BookRepository;
import com.example.libraryApplication.service.author.AuthorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    AuthorServiceImpl authorService;
    BookRepository bookRepository;
    @Override
    public void createBook(BookDto bookDto) {
        Author author = authorService.getAuthor(bookDto.getAuthorId());
        Books createBooks = new Books();
        createBooks.setTitle(bookDto.getTitle());
        createBooks.setSummary(bookDto.getSummary());
        createBooks.setPages(bookDto.getPages());
        createBooks.setAuthor(author);
        createBooks.setBookStatus(BookStatus.AVAILABLE);
        bookRepository.save(createBooks);
    }

    @Override
    public Books getBook(Long bookId) {
        Optional<Books> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()){
            return bookOptional.get();
        }
        else
            throw new BooksNotFoundException(bookId);
    }

    @Override
    public Books updateBook(BookDto bookDto) {
        Author author = authorService.getAuthor(bookDto.getAuthorId());
        Books updateBook = getBook(bookDto.getId());
        updateBook.setTitle(bookDto.getTitle());
        updateBook.setSummary(bookDto.getSummary());
        updateBook.setPages(bookDto.getPages());
        updateBook.setAuthor(author);

        return bookRepository.save(updateBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public BookDto changeBookToBookDto(Books book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setSummary(book.getSummary());
        bookDto.setPages(book.getPages());
        bookDto.setAuthorId(bookDto.getAuthorId());
        return bookDto;
    }

    @Override
    public AuthorBooksDto changeBookToAuthorBookDto(Books books) {
        AuthorBooksDto authorBooksDto = new AuthorBooksDto();
        authorBooksDto.setId(books.getId());
        authorBooksDto.setTitle(books.getTitle());
        authorBooksDto.setPages(books.getPages());
        authorBooksDto.setSummary(books.getSummary());
        return authorBooksDto;
    }
    @Override
    public void updateBookStatus(Long bookId, BookStatus bookStatus) {
        Books book = getBook(bookId);
        book.setBookStatus(bookStatus);
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> getAllBook() {
        List<Books> booksList = (List<Books>) bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Books item : booksList){
            bookDtoList.add(changeBookToBookDto(item));
        }
        return bookDtoList;
    }

    @Override
    public List<AuthorBooksDto> getAllBooksOfAuthor(Long authorId) {
        List<Books> booksList = bookRepository.findByAuthorId(authorId);
        List<AuthorBooksDto> authorBooksDtoList = new ArrayList<>();
        for (Books item : booksList){
            authorBooksDtoList.add(changeBookToAuthorBookDto(item));
        }
        return authorBooksDtoList;
    }


}
