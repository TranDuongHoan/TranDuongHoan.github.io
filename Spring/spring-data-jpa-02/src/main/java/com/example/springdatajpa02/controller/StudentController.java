package com.example.springdatajpa02.controller;

import com.example.springdatajpa02.exception.StudentNotFoundException;
import com.example.springdatajpa02.model.request.SearchStudentRequest;
import com.example.springdatajpa02.model.request.StudentCreationRequest;
import com.example.springdatajpa02.model.response.StudentDataResponse;
import com.example.springdatajpa02.model.response.StudentResponse;
import com.example.springdatajpa02.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students") // base path
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String searchStudent(Model model, SearchStudentRequest request) {
        StudentResponse studentResponse = studentService.searchStudent(request);
        model.addAttribute("dsSinhVien", studentResponse.getStudents());
        model.addAttribute("requestSearch", request);
        model.addAttribute("totalElement", studentResponse.getTotalElement());
        model.addAttribute("totalPage", studentResponse.getTotalPage());
        model.addAttribute("currentPage", studentResponse.getCurrentPage());
        model.addAttribute("pageSize", studentResponse.getPageSize());
        return "students";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentsDetails(@PathVariable Long id) throws StudentNotFoundException {
        StudentDataResponse student = studentService.getStudentsDetails(id);
        return ResponseEntity.ok(student);

    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentCreationRequest request) {
        studentService.createStudent(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentCreationRequest request) throws StudentNotFoundException {
        studentService.updateStudent(id, request);
        return ResponseEntity.ok(null);
    }


}
