package com.example.libraryApplication.service.student;

import com.example.libraryApplication.dto.usersdto.StudentDto;
import com.example.libraryApplication.entity.Student;

import java.util.List;

public interface StudentService {
    void createStudent(StudentDto studentDto);
    Student getStudent(Long studentId);
    String updateStudent(StudentDto studentDto);
    StudentDto convertStudentToStudentDto(Student student);
    String deleteStudent(Long studentId);
    List<StudentDto> getAllStudent();
}
