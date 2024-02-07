package com.example.libraryApplication.service.borrowedbook;

import com.example.libraryApplication.dto.booksDto.BookDto;
import com.example.libraryApplication.dto.borrowedBookDto.BorrowedBooksDto;
import com.example.libraryApplication.pojo.*;
import com.example.libraryApplication.repository.BorrowedBooksRepository;
import com.example.libraryApplication.service.books.BookServiceImpl;
import com.example.libraryApplication.service.student.StudentServiceImpl;
import com.example.libraryApplication.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService{
    BorrowedBooksRepository borrowedBooksRepository;
    StudentServiceImpl studentService;
    BookServiceImpl bookService;
    @Override
    public void createBorrowBooks(BorrowedBooksDto borrowedBooksDto) {
        Student student = studentService.getStudent(borrowedBooksDto.getStudentId());
        Books books = bookService.getBook(borrowedBooksDto.getBooksId());

        BorrowedBooks createBorrowedBooks = new BorrowedBooks();
        createBorrowedBooks.setBooks(books);
        createBorrowedBooks.setStudent(student);
        createBorrowedBooks.setBorrowedDate(new Date());
        createBorrowedBooks.setReturnDays(borrowedBooksDto.getReturnDays());
        createBorrowedBooks.setExpectedReturnDate(createBorrowedBooks.getexpectedReturnDate());
        borrowedBooksRepository.save(createBorrowedBooks);
        bookService.updateBookStatus(borrowedBooksDto.getBooksId(), BookStatus.BORROWED);

    }

    @Override
    public BorrowedBooksDto changeBorrowBooksToBorrowBooksDto(BorrowedBooks borrowedBooks) {
        BorrowedBooksDto borrowedBooksDto = new BorrowedBooksDto();
        borrowedBooksDto.setBooksId(borrowedBooks.getId());
        borrowedBooksDto.setBorrowedDate(borrowedBooks.getBorrowedDate());
        borrowedBooksDto.setReturnDays(borrowedBooksDto.getReturnDays());
        borrowedBooksDto.setExpectedReturnDate(borrowedBooks.getexpectedReturnDate());
        borrowedBooksDto.setStudentId(borrowedBooks.getStudent().getId());
        borrowedBooksDto.setBooksId(borrowedBooks.getBooks().getId());
        return borrowedBooksDto;
    }
    @Override
    public List<BorrowedBooksDto> getBorrowBooks() {
        List<BorrowedBooks> borrowedBooksList = (List<BorrowedBooks>) borrowedBooksRepository.findAll();
        List<BorrowedBooksDto> borrowedBooksDtoList = new ArrayList<>();
        for (BorrowedBooks item : borrowedBooksList){
            borrowedBooksDtoList.add(changeBorrowBooksToBorrowBooksDto(item));
        }
        return borrowedBooksDtoList;
    }
}
