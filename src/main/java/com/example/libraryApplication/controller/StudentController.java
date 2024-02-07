package com.example.libraryApplication.controller;

import com.example.libraryApplication.dto.studentDto.StudentDto;
import com.example.libraryApplication.pojo.Student;
import com.example.libraryApplication.service.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @PostMapping
    public ResponseEntity<HttpStatus> addAuthor(@RequestBody StudentDto studentDto){
        studentService.createStudent(studentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id" ) Long studentId){
        return new ResponseEntity<>(studentService.getStudent(studentId),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable(name = "id") Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateStudent(studentDto),HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return new ResponseEntity<>(studentService.getAllStudent(),HttpStatus.OK);
    }
}
