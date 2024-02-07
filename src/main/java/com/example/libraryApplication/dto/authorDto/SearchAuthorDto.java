package com.example.libraryApplication.dto.authorDto;

import lombok.Data;

@Data
public class SearchAuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long bookId;
}
