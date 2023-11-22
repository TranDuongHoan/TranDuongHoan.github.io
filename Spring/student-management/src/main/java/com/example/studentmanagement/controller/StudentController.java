package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/students") // base path

public class StudentController {

    @GetMapping
    public String getStudents(Model model){
        List<Student> students = getStudents();
        return "students";
    }



}
