package com.example.foodorder.model.request;

import lombok.Data;

@Data
public class SearchSellerRequest {
    private String name;
    private int currentPage = 0;
    private int pageSize = 2;
}
