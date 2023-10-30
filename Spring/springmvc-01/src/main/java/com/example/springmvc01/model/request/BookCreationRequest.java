package com.example.springmvc01.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookCreationRequest {

    private String name;
    private String author;
    private int publishedYear;
    private String description;

}
