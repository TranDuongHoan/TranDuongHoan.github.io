package com.example.springmvc01.model.request;

import com.example.springmvc01.statics.BookCategory;
import lombok.Data;

import java.util.List;

@Data

public class BookUpdateRequest {
    private int id;
    private String name;
    private String author;
    private int publishedYear;
    private String description;
    private List<BookCategory> categories;

}
