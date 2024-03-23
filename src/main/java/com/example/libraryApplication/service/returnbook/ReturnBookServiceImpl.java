package com.example.libraryApplication.service.returnbook;

import com.example.libraryApplication.dto.returnBookDto.ReturnBookDto;
import com.example.libraryApplication.entity.*;
import com.example.libraryApplication.repository.ReturnBooksRepository;
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

@AllArgsConstructor
@Service
public class ReturnBookServiceImpl implements ReturnBookService{
    ReturnBooksRepository returnBooksRepository;
    StudentServiceImpl studentService;
    BookServiceImpl bookService;
    UserServiceImpl userService;
    @Override
    public void addBookReturn(ReturnBookDto returnBookDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        Users studentInfo = userService.getUserByEmail(user);

        Books books = bookService.getBook(returnBookDto.getBookId());

        ReturnedBooks createReturnBooks = new ReturnedBooks();
        createReturnBooks.setStudent(studentInfo.getStudent());
        createReturnBooks.setBooks(books);
        createReturnBooks.setDateReturned(new Date());
        returnBooksRepository.save(createReturnBooks);
        bookService.updateBookStatus(returnBookDto.getBookId(), BookStatus.AVAILABLE);
    }

    @Override
    public ReturnBookDto convertToReturnBookDto(ReturnedBooks returnedBooks) {
        ReturnBookDto returnBookDto = new ReturnBookDto();
        returnBookDto.setId(returnedBooks.getId());
        returnBookDto.setStudentId(returnedBooks.getStudent().getId());
        returnBookDto.setBookId(returnedBooks.getBooks().getId());
        returnBookDto.setDateReturned(returnedBooks.getDateReturned());
        return returnBookDto;
    }

    @Override
    public List<ReturnBookDto> getAllBookReturn() {
        List<ReturnedBooks> returnedBooksList = (List<ReturnedBooks>) returnBooksRepository.findAll();
        List<ReturnBookDto> returnBookDtoList = new ArrayList<>();
        for (ReturnedBooks returnedBooks : returnedBooksList){
            returnBookDtoList.add(convertToReturnBookDto(returnedBooks));
        }
        return returnBookDtoList;
    }


}
