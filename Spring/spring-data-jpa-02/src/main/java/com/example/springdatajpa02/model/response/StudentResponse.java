package com.example.springdatajpa02.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {

    private List<StudentDataResponse> students;
    private Long totalElement;
    private int totalPage;
    private int currentPage;
    private int pageSize;

}
