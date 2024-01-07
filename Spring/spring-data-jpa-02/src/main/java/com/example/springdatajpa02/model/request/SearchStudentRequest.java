package com.example.springdatajpa02.model.request;

import lombok.Data;

@Data
public class SearchStudentRequest {

    private String name;

    private String address;

    private int currentPage = 0;

    private int pageSize = 10;

}
