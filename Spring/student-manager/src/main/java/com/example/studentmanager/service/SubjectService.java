//package com.example.studentmanager.service;
//
//import com.example.studentmanager.entity.Subject;
//import com.example.studentmanager.model.response.SubjectResponse;
//import com.example.studentmanager.repository.SubjectRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//
//public class SubjectService {
//    private final SubjectRepository subjectRepository;
//}
//
//    public List<SubjectResponse> getSubject() {
//        List<Subject> subjects = subjectRepository.getSubject();
////        List<StudentResponse> result = new ArrayList<>();
//
//        return subjects.stream()
//                .map(subject -> SubjectResponse.builder()
//                        .id(subject.getId())
//                        .name(subject.getName())
//                        .credit(subject.getCredit())
//                        .subjectType(subject.getSubjectType())
//                        .build()
//                )
//                .collect(Collectors.toList());
//}
