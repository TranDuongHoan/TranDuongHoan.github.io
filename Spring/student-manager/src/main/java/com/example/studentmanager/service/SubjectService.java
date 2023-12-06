package com.example.studentmanager.service;


import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.exception.SubjectNotFoundException;
import com.example.studentmanager.model.request.StudentCreationRequest;
import com.example.studentmanager.model.request.SubjectCreationRequest;
import com.example.studentmanager.model.response.SubjectResponse;
import com.example.studentmanager.repository.SubjectJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class SubjectService {
    private final SubjectJpaRepository subjectJpaRepository;

    public List<SubjectResponse> getSubject() {
        List<Subject> subjects = subjectJpaRepository.findAll();

        return subjects.stream()
                .map(subject -> SubjectResponse.builder()
                        .id(subject.getId())
                        .name(subject.getName())
                        .credit(subject.getCredit())
                        .subjectType(subject.getSubjectType())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public void createSubject(@Valid SubjectCreationRequest subjectCreationRequest) {
        Subject subject = Subject.builder()
                .name(subjectCreationRequest.getName())
                .credit(subjectCreationRequest.getCredit())
                .subjectType(subjectCreationRequest.getSubjectType())
                .build();
        subjectJpaRepository.save(subject);
    }

    public SubjectResponse getSubjectDetails(Long id) throws SubjectNotFoundException {
        return subjectJpaRepository.findById(id)
                .map(subject -> SubjectResponse.builder()
                        .id(subject.getId())
                        .name(subject.getName())
                        .credit(subject.getCredit())
                        .subjectType(subject.getSubjectType())
                        .build()
                )
                .orElseThrow(() ->
                        new SubjectNotFoundException("Subject with id " + id + " could not be found")
                );
    }

    public void delete(Long id) {
        subjectJpaRepository.deleteById(id);
    }

    public void updateSubject(Long id, SubjectCreationRequest request) throws SubjectNotFoundException {

        Optional<Subject> subjectOptional =  subjectJpaRepository.findById(id);
        if (subjectOptional.isEmpty()){
            throw new SubjectNotFoundException("Subject with id " + id + " could not be found");
        }
        Subject subject= subjectOptional.get();
        subject.setName(request.getName());
        subject.setCredit(request.getCredit());
        subject.setSubjectType(request.getSubjectType());

        subjectJpaRepository.save(subject);

    }
}



