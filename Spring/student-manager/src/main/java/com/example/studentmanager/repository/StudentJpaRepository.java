package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {



}
