package com.example.libraryApplication.service.borrowedbook;

import com.example.libraryApplication.dto.borrowedBookDto.BorrowedBooksDto;
import com.example.libraryApplication.dto.borrowedBookDto.ValidateBorrowedBook;
import com.example.libraryApplication.entity.*;
import com.example.libraryApplication.repository.BorrowedBooksRepository;
import com.example.libraryApplication.service.books.BookServiceImpl;
import com.example.libraryApplication.service.student.StudentServiceImpl;
import com.example.libraryApplication.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService{
    BorrowedBooksRepository borrowedBooksRepository;
    StudentServiceImpl studentService;
    BookServiceImpl bookService;
    UserServiceImpl userService;
    @Override
    public void createBorrowBooks(BorrowedBooksDto borrowedBooksDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        Users studentInfo = userService.getUserByEmail(user);

        Books books = bookService.getBook(borrowedBooksDto.getBooksId());

        BorrowedBooks createBorrowedBooks = new BorrowedBooks();
        createBorrowedBooks.setBooks(books);
        createBorrowedBooks.setStudent(studentInfo.getStudent());
        createBorrowedBooks.setBorrowedDate(new Date());
        createBorrowedBooks.setReturnDays(borrowedBooksDto.getReturnDays());
        createBorrowedBooks.setExpectedReturnDate(createBorrowedBooks.getexpectedReturnDate());
        createBorrowedBooks.setBorrowedBooksStatus(BorrowedBooksStatus.PENDING);
        borrowedBooksRepository.save(createBorrowedBooks);
    }

    @Override
    public void validateBorrowedBookStatus(ValidateBorrowedBook validateBorrowedBook) {
        BorrowedBooks borrowedBooks = getBorrowedBooksById(validateBorrowedBook.getBorrowedBookId());
        String action = validateBorrowedBook.getAction();
        if ("approved".equalsIgnoreCase(action)) {
            borrowedBooks.setBorrowedBooksStatus(BorrowedBooksStatus.APPROVED);
            bookService.updateBookStatus(borrowedBooks.getBooks().getId(),BookStatus.BORROWED);
        }
        else if ("denied".equalsIgnoreCase(action)) {
            borrowedBooks.setBorrowedBooksStatus(BorrowedBooksStatus.DENIED);
        }
        else {
            throw new IllegalArgumentException("Invalid action provided. Action must be either 'approved' or 'denied'.");
        }
        borrowedBooksRepository.save(borrowedBooks);
    }
    @Override
    public BorrowedBooks getBorrowedBooksById(Long borrowedBooksId) {
        Optional<BorrowedBooks> optionalBorrowedBooks = borrowedBooksRepository.findById(borrowedBooksId);
        if (optionalBorrowedBooks.isPresent()) {
            BorrowedBooks borrowedBooks = optionalBorrowedBooks.get();
            if (BorrowedBooksStatus.PENDING.equals(borrowedBooks.getBorrowedBooksStatus())) {
                return borrowedBooks;
            } else {
                throw new IllegalArgumentException("The borrowed book with ID " + borrowedBooksId + " is not in a pending status.");
            }
        } else {
            throw new IllegalArgumentException("The borrowed book with ID " + borrowedBooksId + " is not present in our records.");
        }
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
