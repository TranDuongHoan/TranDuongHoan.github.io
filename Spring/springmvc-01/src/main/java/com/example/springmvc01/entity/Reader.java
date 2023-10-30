package com.example.springmvc01.entity;

import com.example.springmvc01.statics.BookCategory;
import com.example.springmvc01.statics.ReaderLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Reader{
    private int id;
    private List<ReaderLevel> levels;
    private String name;
    private String address;
    private int phone;
}
