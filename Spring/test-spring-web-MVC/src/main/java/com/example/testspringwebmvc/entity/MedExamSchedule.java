package com.example.testspringwebmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_examination_schedule")

public class MedExamSchedule {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameOrdered;

    @Column
    private String phoneOrdered;

    @Column
    private String emailOrdered;

    @Column
    private String contentOrdered;

    @Column
    private String status;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "approval_time")
    private LocalDateTime approvalTime;

    @Column(name = "rejection_time")
    private LocalDateTime rejectionTime;


}
