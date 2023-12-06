package com.example.studentmanager.controller;

//import com.example.studentmanager.entity.Student;
//import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.model.response.StudentResponse;
import com.example.studentmanager.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students") // base path
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String getStudent(Model model) {
        List<StudentResponse> students = studentService.getStudent();
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentDetails(@PathVariable Long id) throws StudentNotFoundException {
        StudentResponse student = studentService.getStudentDetails(id);
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
