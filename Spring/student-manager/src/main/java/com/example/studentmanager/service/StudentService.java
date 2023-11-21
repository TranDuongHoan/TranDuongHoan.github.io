package com.example.studentmanager.service;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.model.response.StudentDetailResponse;
import com.example.studentmanager.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public List<Student> deleteStudent(int id) throws StudentNotFoundException {
        return studentRepository.delete(id);
    }

    public List<Student> createStudent(StudentCreationRequest studentCreationRequest) {
        Student student = Student.builder()
                .id(studentRepository.AUTO_ID++)
                .name(studentCreationRequest.getName())
                .address(studentCreationRequest.getAddress())
                .phone(studentCreationRequest.getPhone())
                .nameClass(studentCreationRequest.getNameClass())
                .build();
        return studentRepository.createStudent(student);
    }

    public StudentDetailResponse findById(int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        return StudentDetailResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .phone(student.getPhone())
                .nameClass(student.getNameClass())
                .build();
    }

    public List<Student> updateStudent(StudentUpdateRequest student) throws StudentNotFoundException {
        return studentRepository.updateStudent(student);
    }
}
