package com.example.studentmanager.repository;


import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SubjectRepository {
    private static final String STUDENT_DATA_FILE_NAME = "data/students.json";

    public static int AUTO_ID = 11;

    private final FileUtil<Student> fileUtil;

    public List<Student> getStudent() {
        return fileUtil.readDataFromFile(STUDENT_DATA_FILE_NAME, Student[].class);
    }

    public List<Student> delete(int id) throws StudentNotFoundException {
        List<Student> students = getStudent();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, students);
                return students;
            }
        }
        return null;
    }
}
