package com.example.libraryApplication.dto.booksDto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private Long pages;
    private String summary;
    private Long authorId;
}
