package com.example.springmvc01.model.request;

import lombok.Data;

@Data
public class BorrowBookCreationRequest {

    private int readerId;
    private int bookId;
    private int quantity;

}
