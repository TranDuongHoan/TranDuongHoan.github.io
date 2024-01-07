package com.example.springdatajpa02.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDataResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String className;
    private Long totalRecords;

}
