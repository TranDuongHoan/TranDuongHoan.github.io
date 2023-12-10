package com.example.testspringwebmvc.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedExamScheduleResponse {

    private Long id;
    private String nameOrdered;
    private String phoneOrdered;
    private String emailOrdered;
    private String contentOrdered;
    private String status;
    private LocalDateTime creationTime;
    private LocalDateTime approvalTime;
    private LocalDateTime rejectionTime;

}
