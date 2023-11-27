package com.example.studentmanager.service;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.model.response.StudentResponse;
import com.example.studentmanager.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getStudent() {
        List<Student> students = studentRepository.getStudent();
//        List<StudentResponse> result = new ArrayList<>();

        return students.stream()
                .map(student -> StudentResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build()
                )
                .collect(Collectors.toList());

//        for (int i = 0; i < students.size(); i++){
//            Student student = students.get(i);
//            StudentResponse studentResponse = StudentResponse.builder()
//                    .id(student.getId())
//                    .name(student.getName())
//                    .address(student.getAddress())
//                    .phone(student.getPhone())
//                    .className(student.getClassName())
//                    .build();
//                    result.add(studentDetailResponse);
//        }
//        return result;
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

    public StudentResponse findById(int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        return StudentResponse.builder()
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