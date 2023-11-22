package com.example.studentmanager.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailResponse {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String className;

}
