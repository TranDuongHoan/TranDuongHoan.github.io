package com.example.springmvc01.entity;

import com.example.springmvc01.statics.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    private String name;
    private String author;
    private int publishedYear;
    private List<BookCategory> categories;
    private String description;

}
