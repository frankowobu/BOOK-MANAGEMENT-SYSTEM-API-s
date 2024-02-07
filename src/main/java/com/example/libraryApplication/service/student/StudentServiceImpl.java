package com.example.libraryApplication.service.student;

import com.example.libraryApplication.dto.studentDto.StudentDto;
import com.example.libraryApplication.exception.StudentNotFoundException;
import com.example.libraryApplication.pojo.Student;
import com.example.libraryApplication.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    StudentRepository studentRepository;
    @Override
    public void createStudent(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if (!optionalStudent.isPresent()){
            Student createStudent = new Student();
            createStudent.setName(studentDto.getName());
            createStudent.setEmail(studentDto.getEmail());
            createStudent.setDepartment(studentDto.getDepartment());
            studentRepository.save(createStudent);
        }
        else throw new StudentNotFoundException(studentDto.getEmail());
    }

    @Override
    public Student getStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else throw new StudentNotFoundException(studentId);
    }
    @Override
    public Student updateStudent(StudentDto studentDto) {
        Student updateStudent = getStudent(studentDto.getId());
        updateStudent.setName(studentDto.getName());
        updateStudent.setEmail(studentDto.getEmail());
        updateStudent.setDepartment(studentDto.getDepartment());
        return studentRepository.save(updateStudent);
    }

    @Override
    public StudentDto convertStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDepartment(student.getDepartment());
        return studentDto;
    }
    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student item : studentList){
            studentDtoList.add(convertStudentToStudentDto(item));
        }
        return studentDtoList;
    }
}
