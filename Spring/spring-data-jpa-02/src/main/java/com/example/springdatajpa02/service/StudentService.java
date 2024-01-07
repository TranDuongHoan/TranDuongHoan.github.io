package com.example.springdatajpa02.service;

import com.example.springdatajpa02.entity.Student;
import com.example.springdatajpa02.exception.StudentNotFoundException;
import com.example.springdatajpa02.model.request.SearchStudentRequest;
import com.example.springdatajpa02.model.request.StudentCreationRequest;
import com.example.springdatajpa02.model.response.StudentDataResponse;
import com.example.springdatajpa02.model.response.StudentResponse;
import com.example.springdatajpa02.repository.StudentJpaRepository;
import com.example.springdatajpa02.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJpaRepository studentJpaRepository;

    private final StudentRepository studentRepository;

    public StudentResponse searchStudent(SearchStudentRequest request) {
//        Pageable pageable = Pageable.ofSize(request.getPageSize()).withPage(request.getCurrentPage());
//        Page<Student> studentPage = studentJpaRepository.findAll(pageable);
//        List<Student> studentResponses = studentPage.getContent();
//        int totalPages = studentPage.getTotalPages();
//        long totalElements = studentPage.getTotalElements();



//        studentRepository.searchStudent(request);

//        List<StudentDataResponse> data = studentResponses.stream()
//                .map(student -> StudentDataResponse.builder()
//                                .id(student.getId())
//                                .name(student.getName())
//                                .address(student.getAddress())
//                                .phone(student.getPhone())
//                                .className(student.getClassName())
//                                .build()
//                )
//                .collect(Collectors.toList());

//        List<StudentDataResponse> data = studentRepository.searchStudent(request);
//        Long totalElements = studentRepository.countStudent(request);

        List<StudentDataResponse> data = studentRepository.searchStudent(request);
        Long totalElements = 0L;

        double totalPageTemp = (double) totalElements / request.getPageSize();
        if(totalPageTemp > (double) totalElements / request.getPageSize()){
            totalPageTemp++;
        }

        return StudentResponse.builder()
                .students(data)
                .totalElement(totalElements)
                .totalPage(Long.valueOf((totalElements / request.getPageSize())).intValue() + 1)
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();
    }

    public void createStudent(StudentCreationRequest request) {
        val student = Student.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .className(request.getClassName())
                .build();
        studentJpaRepository.save(student);
    }


    public void delete(Long id) {
        studentJpaRepository.deleteById(id);
    }

    public StudentDataResponse getStudentsDetails(Long id) throws StudentNotFoundException {
        return studentJpaRepository.findById(id)
                .map(student -> StudentDataResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build()
                )
                .orElseThrow(() ->
                        new StudentNotFoundException("Student with id " + id + " could not be found")
                );
    }

    @Transactional
    public void updateStudent(Long id, StudentCreationRequest request) throws StudentNotFoundException {
//        var studentOptional = studentJpaRepository.findById(id);
//        if (studentOptional.isEmpty()) {
//            throw new StudentNotFoundException("Student with id " + id + " could not be found");
//        }
//        Student student = studentOptional.get();
//        student.setName(request.getName());
//        student.setPhone(request.getPhone());
//        student.setAddress(request.getAddress());
//        student.setClassName(request.getClassName());
//
//        studentJpaRepository.save(student);
        studentJpaRepository.updateNameOfStudent(request.getName(), id);
    }

}

