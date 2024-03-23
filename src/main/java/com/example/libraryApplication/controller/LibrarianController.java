package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.usersdto.LibrarianDto;
import com.example.libraryApplication.entity.Librarian;
import com.example.libraryApplication.service.librarian.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
@AllArgsConstructor
public class LibrarianController {
    LibrarianService librarianService;

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarian(@PathVariable(name = "id") Long librarianId){
        return new ResponseEntity<>(librarianService.getLibrarian(librarianId),HttpStatus.ACCEPTED);
    }
    @PutMapping
    public ResponseEntity<String> updateLibrarian(@RequestBody LibrarianDto librarianDto){
        librarianService.updateLibrarian(librarianDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibrarian(@PathVariable(name = "id") Long librarianId){
        librarianService.deleteLibrarian(librarianId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<LibrarianDto>> getLibrarian(){
        return new ResponseEntity<>(librarianService.getAllLibrarian(),HttpStatus.ACCEPTED);
    }
}
