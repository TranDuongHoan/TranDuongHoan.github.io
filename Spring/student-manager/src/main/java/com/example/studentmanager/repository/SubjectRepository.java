package com.example.studentmanager.repository;


import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SubjectRepository {
    private static final String SUBJECT_DATA_FILE_NAME = "data/subjects.json";

    public static int AUTO_ID = 11;

    private final FileUtil<Subject> fileUtil;

    public List<Subject> getSubject() {
        return fileUtil.readDataFromFile(SUBJECT_DATA_FILE_NAME, Subject[].class);
    }


    public List<Subject> createSubject(Subject subject) {
        List<Subject> subjects = getSubject();
        if (CollectionUtils.isEmpty(subjects)) {
            subjects   = new ArrayList<>();
        }
        subjects  .add(subject);
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects );
        return subjects ;
    }

    public void save(List<Subject> result) {
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, result );

    }


}
