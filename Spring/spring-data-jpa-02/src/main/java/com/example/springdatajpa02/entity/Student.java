package com.example.springdatajpa02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

//    @Column
    // 1 - n
//    @OneToMany(mappedBy = "sinhVien", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Phone> phones;
    // N + 1 query
    @Column
    private String phone;

    @Column(name = "class_name")
    private String className;

    @Column
    private Float gpa;

}
