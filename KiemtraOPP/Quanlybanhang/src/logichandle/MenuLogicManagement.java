package logichandle;

import java.util.Scanner;

public class MenuLogicManagement {
    private final SalerLogicManagement salerLogicManagement = new SalerLogicManagement();
    private final ItemLogicManagement itemslogicmanagement = new ItemLogicManagement();

    public void menu() {
        while (true){
            showMenuContent();
            int choice = functionsChoice();
            switch (choice) {
                case 1:
                    itemslogicmanagement.inputNewItem();
                    break;
                case 2:
                    itemslogicmanagement.showItem();
                    break;
                case 3:
                    salerLogicManagement.inputNewSaler();
                    break;
                case 4:
                    salerLogicManagement.showSaler();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    return;
            }
        }

    }

    private int functionsChoice() {
        int choice;
        do{
            choice = new Scanner(System.in).nextInt();
            if (choice>=1 && choice<=9){
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        return choice;
    }

    private void showMenuContent() {
        System.out.println("======================QUẢN LÝ BÁN HÀNG==========================");
        System.out.println("1. Nhập danh sách mặt hàng mới.");
        System.out.println("2. In danh sách mặt hàng đã có.");
        System.out.println("3. Nhập danh sách nhân viên mới.");
        System.out.println("4. In danh sách nhân viên đã có.");
        System.out.println("5. Lập bảng danh sách bán hàng cho từng nhân viên.");
        System.out.println("6. In danh sách bán hàng cho từng nhân viên");
        System.out.println("7. Sắp xếp danh sách bán hàng theo tên nhân viên");
        System.out.println("8. Sắp xếp danh sách bán hàng theo nhóm mặt hàng");
        System.out.println("9. Lập bảng kê doanh thu cho mỗi nhân viên");
        System.out.println("10. Thoát");
    }
}
