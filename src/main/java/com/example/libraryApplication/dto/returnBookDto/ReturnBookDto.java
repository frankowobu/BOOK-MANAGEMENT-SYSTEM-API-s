package com.example.libraryApplication.dto.returnBookDto;
;
import lombok.Data;

import java.util.Date;

@Data
public class ReturnBookDto {
    private Long id;
    private Long studentId;
    private Long bookId;
    private Date dateReturned;
}
