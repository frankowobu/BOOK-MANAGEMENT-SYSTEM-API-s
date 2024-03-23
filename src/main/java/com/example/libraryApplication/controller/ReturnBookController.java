package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.returnBookDto.ReturnBookDto;
import com.example.libraryApplication.service.returnbook.ReturnBookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returned")
@AllArgsConstructor
public class ReturnBookController {
    ReturnBookServiceImpl returnBookService;
    @PostMapping
    public ResponseEntity<HttpStatus> addBookReturn(@RequestBody ReturnBookDto returnBookDto){
        returnBookService.addBookReturn(returnBookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ReturnBookDto>> getAllReturnBooks(){
        return new ResponseEntity<>(returnBookService.getAllBookReturn(),HttpStatus.ACCEPTED);
    }

}
