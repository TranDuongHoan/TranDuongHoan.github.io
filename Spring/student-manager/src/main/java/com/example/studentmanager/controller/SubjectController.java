//package com.example.studentmanager.controller;
//
//import com.example.studentmanager.model.request.StudentCreationRequest;
//import com.example.studentmanager.model.request.SubjectCreationRequest;
//import com.example.studentmanager.model.response.StudentResponse;
//import com.example.studentmanager.model.response.SubjectResponse;
//import com.example.studentmanager.service.StudentService;
//import com.example.studentmanager.service.SubjectService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@AllArgsConstructor
//@RequestMapping("/subjects")
//
//public class SubjectController {
//    private final SubjectService subjectService;
//
//    @GetMapping
//    public String getSubjects(Model model) {
//        List<SubjectResponse> subjects = subjectService.getSubject();
//        model.addAttribute("dsMonHoc", subjects);
//        return "students";
//    }
//
//    @PostMapping
//    public ResponseEntity<?> getSubjects(@RequestBody SubjectCreationRequest request) {
//        subjectService.createSubject(request);
//        return ResponseEntity.ok(null);
//    }
//}
