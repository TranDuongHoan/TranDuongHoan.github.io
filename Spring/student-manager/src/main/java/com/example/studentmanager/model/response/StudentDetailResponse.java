package com.example.studentmanager.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentDetailResponse {

    private int id;
    private String name;
    private String address;
    private int phone;
    private String nameClass;

}
