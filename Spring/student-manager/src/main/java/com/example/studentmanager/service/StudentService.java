package com.example.studentmanager.service;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.model.response.StudentDetailResponse;
import com.example.studentmanager.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDetailResponse> getStudent() {
        List<Student> students = studentRepository.getStudent();
        List<StudentDetailResponse> result = new ArrayList<>();
        for (int i = 0; i < students.size(); i++){
            Student student = students.get(i);
            StudentDetailResponse studentDetailResponse = StudentDetailResponse.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .address(student.getAddress())
                    .phone(student.getPhone())
                    .className(student.getClassName())
                    .build();
                    result.add(studentDetailResponse);
        }
        return result;
    }

    public List<Student> deleteStudent(int id) throws StudentNotFoundException {
        return studentRepository.delete(id);
    }

    public List<Student> createStudent(@Valid StudentCreationRequest studentCreationRequest) {
        Student student = Student.builder()
                .id(studentRepository.AUTO_ID++)
                .name(studentCreationRequest.getName())
                .address(studentCreationRequest.getAddress())
                .phone(studentCreationRequest.getPhone())
                .className(studentCreationRequest.getClassName())
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
                .className(student.getClassName())
                .build();
    }

    public List<Student> updateStudent(StudentUpdateRequest student) throws StudentNotFoundException {
        return studentRepository.updateStudent(student);
    }
}
