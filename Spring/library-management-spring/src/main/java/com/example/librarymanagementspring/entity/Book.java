package com.example.librarymanagementspring.entity;

import com.example.librarymanagementspring.stactics.BookSpecialization;
import com.example.librarymanagementspring.stactics.ReaderLevel;
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
public class Book implements InputInfo {
    int id;
    String name;
    String author;
    int publishedYear;
    BookSpecialization bookSpecialization;
    int totalBook;


    @Override
    public void inputInfo() {
        System.out.println("Mã đầu sách: ");
        this.setId(new Scanner(System.in).nextInt());
        System.out.println("Tên sách: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Tác giả: ");
        this.setAuthor(new Scanner(System.in).nextLine());
        System.out.println("Năm xuất bản: ");
        this.setPublishedYear(new Scanner(System.in).nextInt());
        System.out.println("Nhập chuyên ngành: ");
        System.out.println("1. Khoa học tự nhiên");
        System.out.println("2. Văn học - Nghệ thuật");
        System.out.println("3. Điện tử viễn thông");
        System.out.println("4. Công nghệ thông tin");
        System.out.println("MỜI BẠN CHỌN: ");
        int levelChoice;
        do {
            levelChoice = new Scanner(System.in).nextInt();
            if (levelChoice >= 1 && levelChoice <= 4) {
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        switch (levelChoice) {
            case 1:
                this.setBookSpecialization(BookSpecialization.KHTN);
                break;
            case 2:
                this.setBookSpecialization(BookSpecialization.VHNT);
                break;
            case 3:
                this.setBookSpecialization(BookSpecialization.DTVT);
                break;
            case 4:
                this.setBookSpecialization(BookSpecialization.CNTT);
                break;
        }
    }

}