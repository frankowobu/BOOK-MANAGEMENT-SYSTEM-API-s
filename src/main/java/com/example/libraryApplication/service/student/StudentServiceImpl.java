package com.example.libraryApplication.service.student;

import com.example.libraryApplication.dto.usersdto.StudentDto;
import com.example.libraryApplication.entity.Role;
import com.example.libraryApplication.entity.Users;
import com.example.libraryApplication.exception.StudentNotFoundException;
import com.example.libraryApplication.entity.Student;
import com.example.libraryApplication.repository.StudentRepository;
import com.example.libraryApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    StudentRepository studentRepository;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createStudent(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if (!optionalStudent.isPresent()) {
            Student createStudent = new Student();
            createStudent.setName(studentDto.getName());
            createStudent.setEmail(studentDto.getEmail());
            createStudent.setDepartment(studentDto.getDepartment());
            createStudent.setGender(studentDto.getGender());
            createStudent.setPassword(passwordEncoder.encode(studentDto.getPassword()));
            createStudent.setRole(Role.STUDENT);

            Users user = new Users();
            user.setEmail(studentDto.getEmail());
            user.setPassword(passwordEncoder.encode(studentDto.getPassword()));
            user.setRole(Role.STUDENT);
            user.setStudent(createStudent);
            userRepository.save(user);

            //save to student db
            createStudent.setUser(user);
            studentRepository.save(createStudent);
        } else {
            throw new StudentNotFoundException(studentDto.getEmail());
        }
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
    public String updateStudent(StudentDto studentDto) {
        Student updateStudent = getStudent(studentDto.getId());
        updateStudent.setName(studentDto.getName());
        updateStudent.setEmail(studentDto.getEmail());
        updateStudent.setDepartment(studentDto.getDepartment());
        updateStudent.setGender(studentDto.getGender());

        Users updateUser = updateStudent.getUser();
        updateUser.setEmail(updateStudent.getEmail());

        studentRepository.save(updateStudent);
        return "student information updated";
    }

    @Override
    public StudentDto convertStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDepartment(student.getDepartment());
        studentDto.setGender(student.getGender());
        return studentDto;
    }
    @Override
    public String deleteStudent(Long studentId) {
        Student student = getStudent(studentId);
        studentRepository.deleteById(student.getId());
        return student.getName()+ " deleted successfully";
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
