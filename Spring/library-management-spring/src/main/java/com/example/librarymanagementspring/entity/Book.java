package com.example.librarymanagementspring.entity;

import com.example.librarymanagementspring.stactics.BookSpecialization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book implements InputInfo{
    int id;
    String name;
    String author;
    int publishedYear;
    BookSpecialization bookSpecialization;
}

    @Override
    public void inputInfo(){
        System.out.println("Mã đầu sách: ");
        this.setId(new Scanner(System.in).nextInt());
        System.out.println("Tên sách: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Tác giả: ");
        this.setAuthor(new Scanner(System.in).nextLine());
        System.out.println("Năm xuất bản: ");
        this.setPublishedYear(new Scanner(System.in).nextInt());
        System.out.println("Chuyên ngành: ");
        this.setBookSpecialization(new Scanner(System.in).nextLine());
    }