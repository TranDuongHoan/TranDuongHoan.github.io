package com.example.librarymanagementspring.logic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuLogic {

    BookLogic bookLogic;
    ReaderLogic readerLogic;


    public void menu() {
        while (true){
            showMenuContent();
            int choice = functionsChoice();
            switch (choice) {
                case 1:
                    bookLogic.inputNewBook();
                    break;
                case 2:
                    bookLogic.showBook();
                    break;
                case 3:
                    readerLogic.inputNewReader();
                    break;
                case 4:
                    readerLogic.showReader();
                    break;
                case 5:

                case 6:

                case 7:

                case 8:

                case 9:

                case 10:
                    return;
            }
        }
    }

    private int functionsChoice() {
        int choice;
        do{
            choice = new Scanner(System.in).nextInt();
            if (choice>=1 && choice<=10){
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        return choice;
    }
    private void showMenuContent() {
        System.out.println("------------PHẦN MỀM QUẢN LÝ MƯỢN SÁCH THƯ VIỆN-------------");
        System.out.println("1. Thêm mới đầu sách.");
        System.out.println("2. In danh sách các đầu sách.");
        System.out.println("3. Thêm mới người đọc.");
        System.out.println("4. In danh sách bạn đọc đã có.");
        System.out.println("5. Lập bảng quản lý mượn sách.");
        System.out.println("6. In bảng bảng quản lý.");
        System.out.println("7. Sắp xếp bảng quản lý theo tên bạn đọc.");
        System.out.println("8. Tìm kiếm danh sách mượn sách theo bạn đọc.");
        System.out.println("9. Hiển thị danh sách mượn sách theo bạn đọc.");
        System.out.println("10. THOÁT.");
        System.out.println("MỜI BẠN CHỌN: ");
    }


}


