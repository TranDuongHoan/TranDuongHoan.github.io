package com.example.librarymanagementspring.entity;

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
public class Reader extends Person {
    int id;
    ReaderLevel level;


    @Override
    public void inputInfo() {
        super.inputInfo();
        System.out.println("Nhập loại bạn đọc: ");
        System.out.println("1. Sinh viên");
        System.out.println("2. Học viên cao học");
        System.out.println("3. Giảng viên");
        System.out.println("MỜI BẠN CHỌN: ");
        int levelChoice;
        do {
            levelChoice = new Scanner(System.in).nextInt();
            if (levelChoice >= 1 && levelChoice <= 3) {
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        switch (levelChoice) {
            case 1:
                this.setLevel(ReaderLevel.SV);
                break;
            case 2:
                this.setLevel(ReaderLevel.HVCH);
                break;
            case 3:
                this.setLevel(ReaderLevel.GV);
                break;
        }
    }
}
