package com.example.libraryApplication.service.returnbook;

import com.example.libraryApplication.dto.returnBookDto.ReturnBookDto;
import com.example.libraryApplication.pojo.ReturnedBooks;

import java.util.List;

public interface ReturnBookService {
    void addBookReturn(ReturnBookDto returnBookDto);
    ReturnBookDto convertToReturnBookDto(ReturnedBooks returnedBooks);
    List<ReturnBookDto> getAllBookReturn();
}
