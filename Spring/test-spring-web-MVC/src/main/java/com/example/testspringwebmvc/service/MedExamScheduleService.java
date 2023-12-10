package com.example.testspringwebmvc.service;

import com.example.testspringwebmvc.entity.MedExamSchedule;
import com.example.testspringwebmvc.model.response.MedExamScheduleResponse;
import com.example.testspringwebmvc.repository.MedExamScheduleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedExamScheduleService {

    private final MedExamScheduleJpaRepository medExamScheduleJpaRepository;

    public List<MedExamScheduleResponse> getMedExamSchedule() {
        List<MedExamSchedule> medexamschedules = medExamScheduleJpaRepository.findAll();

        return medexamschedules.stream()
                .map(medexamschedule -> MedExamScheduleResponse.builder()
                        .id(medexamschedule.getId())
                        .nameOrdered(medexamschedule.getNameOrdered())
                        .phoneOrdered(medexamschedule.getPhoneOrdered())
                        .emailOrdered(medexamschedule.getEmailOrdered())
                        .contentOrdered(medexamschedule.getContentOrdered())
                        .status(medexamschedule.getStatus())
                        .creationTime(medexamschedule.getCreationTime())
                        .approvalTime(medexamschedule.getApprovalTime())
                        .rejectionTime(medexamschedule.getRejectionTime())
                        .build()
                )
                .collect(Collectors.toList());
    }


}
