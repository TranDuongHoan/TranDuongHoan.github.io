//package com.example.studentmanager.service;
//
//
//import com.example.studentmanager.entity.Subject;
//import com.example.studentmanager.model.request.SubjectCreationRequest;
//import com.example.studentmanager.model.response.SubjectResponse;
//import com.example.studentmanager.repository.SubjectRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//
//public class SubjectService {
//    private final SubjectRepository subjectRepository;
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
//    }
//
//    public List<Subject> createSubject(@Valid SubjectCreationRequest subjectCreationRequest) {
//        Subject subject = Subject.builder()
//                .id(subjectRepository.AUTO_ID++)
//                .name(subjectCreationRequest.getName())
//                .credit(subjectCreationRequest.getCredit())
//                .subjectType(subjectCreationRequest.getSubjectType())
//                .build();
//        return subjectRepository.createSubject(subject);
//    }
//
//    public SubjectResponse getSubjectDetails(Integer id) {
//        List<Subject> subjects = subjectRepository.getSubject();
//
//        return subjects.stream().filter(s -> s.getId() == id)
//                .findFirst()
//                .map(subject -> SubjectResponse.builder()
//                        .id(subject.getId())
//                        .name(subject.getName())
//                        .credit(subject.getCredit())
//                        .subjectType(subject.getSubjectType())
//                        .build())
//                .get();
//    }
//
//    public void delete(Integer id) {
//        List<Subject> subjects = subjectRepository.getSubject();
//        subjects.removeIf(s -> s.getId() == id);
//        subjectRepository.save(subjects);
//    }
//}
//
//
//
