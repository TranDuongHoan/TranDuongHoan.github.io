package com.example.springmvc01.model.response;

import com.example.springmvc01.statics.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BookDetailResponse {

    private int id;
    private String name;
    private String author;
    private int publishedYear;
    private String description;
    private List<BookCategory> categories;

}
