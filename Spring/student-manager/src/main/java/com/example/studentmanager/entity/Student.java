package com.example.studentmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private int id;
    private String name;
    private String address;
    private int phone;
    private String nameClass;
}
