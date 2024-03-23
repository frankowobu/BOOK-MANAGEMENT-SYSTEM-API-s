package com.example.libraryApplication.dto.borrowedBookDto;

import lombok.Data;

@Data
public class ValidateBorrowedBook {
    private long borrowedBookId;
    private String action;
}
