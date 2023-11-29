package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.exception.StudentNotFoundException;
import com.example.studentmanager.model.request.StudentUpdateRequest;
import com.example.studentmanager.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private static final String STUDENT_DATA_FILE_NAME = "data/students.json";

    public static int AUTO_ID = 11;

    private final FileUtil<Student> fileUtil;

    public List<Student> getStudent() {
        return fileUtil.readDataFromFile(STUDENT_DATA_FILE_NAME, Student[].class);
    }



    public List<Student> createStudent(Student student) {
        List<Student> students = getStudent();
        if (CollectionUtils.isEmpty(students )) {
            students  = new ArrayList<>();
        }
        students .add(student);
        fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, students );
        return students ;
    }

    public void save(List<Student> result) {
        fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, result );

    }

}
