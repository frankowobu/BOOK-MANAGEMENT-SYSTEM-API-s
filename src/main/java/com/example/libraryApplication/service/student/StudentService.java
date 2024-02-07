package com.example.libraryApplication.service.student;

import com.example.libraryApplication.dto.studentDto.StudentDto;
import com.example.libraryApplication.pojo.Student;

import java.util.List;

public interface StudentService {
    void createStudent(StudentDto studentDto);
    Student getStudent(Long studentId);
    Student updateStudent(StudentDto studentDto);
    StudentDto convertStudentToStudentDto(Student student);
    void deleteStudent(Long studentId);
    List<StudentDto> getAllStudent();
}
