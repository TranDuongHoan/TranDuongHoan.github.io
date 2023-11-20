package com.example.springmvc01.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookBorrowed {

    private Reader reader;
    private Book book;
    private int quantity;
    private LocalDate borrowedDate;

}
