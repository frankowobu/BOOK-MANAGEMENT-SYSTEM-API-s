package com.example.libraryApplication.dto.borrowedBookDto;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
public class BorrowedBooksDto {
    private Long id;
    private Date borrowedDate;
    private Long returnDays;
    private Date expectedReturnDate;
    private Long studentId;
    private Long booksId;

}
