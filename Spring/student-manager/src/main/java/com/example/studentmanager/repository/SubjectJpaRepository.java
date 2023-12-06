package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {



}
