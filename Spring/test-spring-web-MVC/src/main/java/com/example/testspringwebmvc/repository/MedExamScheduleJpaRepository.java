package com.example.testspringwebmvc.repository;

import com.example.testspringwebmvc.entity.MedExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedExamScheduleJpaRepository extends JpaRepository<MedExamSchedule, Long> {


}
