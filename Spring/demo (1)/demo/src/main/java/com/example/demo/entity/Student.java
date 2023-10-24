package com.example.demo.entity;

import com.example.demo.stactics.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Student {

     int id;
     String name;
     LocalDate dob;
     Gender gender;
     float gpa;

}
