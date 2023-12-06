package com.example.studentmanager.controller;

import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.exception.SubjectNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.SubjectCreationRequest;
import com.example.studentmanager.model.response.StudentResponse;
import com.example.studentmanager.model.response.SubjectResponse;
import com.example.studentmanager.service.StudentService;
import com.example.studentmanager.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/subjects")

public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public String getSubjects(Model model) {
        List<SubjectResponse> subjects = subjectService.getSubject();
        model.addAttribute("dsMonHoc", subjects);
        return "students";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentDetails(@PathVariable Long id) throws SubjectNotFoundException {
        SubjectResponse subject = subjectService.getSubjectDetails(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<?> getSubjects(@RequestBody SubjectCreationRequest request) {
        subjectService.createSubject(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectCreationRequest request) throws SubjectNotFoundException {
        subjectService.updateSubject(id, request);
        return ResponseEntity.ok(null);
    }
}
