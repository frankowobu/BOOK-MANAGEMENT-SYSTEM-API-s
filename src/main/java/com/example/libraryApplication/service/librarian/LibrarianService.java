package com.example.libraryApplication.service.librarian;

import com.example.libraryApplication.dto.usersdto.LibrarianDto;
import com.example.libraryApplication.dto.usersdto.StudentDto;
import com.example.libraryApplication.entity.Librarian;

import java.util.List;

public interface LibrarianService {
    void createLibrarian(LibrarianDto librarianDto);
    Librarian getLibrarian(Long librarianId);
    String updateLibrarian(LibrarianDto librarianDto);
    LibrarianDto convertLibrarianToLibrarianDto(Librarian librarian);
    String deleteLibrarian(Long librarianId);
    List<LibrarianDto> getAllLibrarian();
}
