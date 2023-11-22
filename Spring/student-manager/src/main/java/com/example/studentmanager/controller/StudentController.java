package com.example.studentmanager.controller;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.model.response.StudentDetailResponse;
import com.example.studentmanager.service.StudentService;
import lombok.AllArgsConstructor;
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
    public String getStudents(Model model) {
        List<StudentDetailResponse> students = studentService.getStudent();
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) throws StudentNotFoundException {
        List<Student> students = studentService.deleteStudent(id);
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    @GetMapping("/create-student")
    public String forwardToStudentCreation(Model model) {
        model.addAttribute("sinhVienThemMoi", new StudentCreationRequest());
        return "student-creation";
    }

    @PostMapping("/create-student")
    public String createStudent(@ModelAttribute("sinhVienThemMoi") @Valid StudentCreationRequest student, Errors errors) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "student-creation";
        }
        List<Student> students = studentService.createStudent(student);
//            model.addAttribute("dsSach", books);
        return "redirect:/index";

    }

    @GetMapping("/update-student/{student-id}")
    public String forwardToStudentUpdate(Model model, @PathVariable("student-id") int id) throws StudentNotFoundException {
        StudentDetailResponse student = studentService.findById(id);
        model.addAttribute("sinhVienMoiCapNhat", student);
        return "student-update";
    }

    @PostMapping("/update-student")
    public String updateBook(@ModelAttribute("ssinhVienMoiCapNhat") @Valid StudentUpdateRequest student, Errors errors) throws StudentNotFoundException {
        if (null != errors && errors.getErrorCount() > 0) {
            return "student-update";
        }
        List<Student> students = studentService.updateStudent(student);
//        model.addAttribute("dsSach", books);
        return "redirect:/index";
    }
}
