package logichandle;

import java.util.Scanner;

public class MenuLogicMagament {
    private final DriveLogicMagament driveLogicManagement = new DriveLogicMagament();
    private final BusLineLogicMagament busLineLogicManagement = new BusLineLogicMagament();
    private final DrivingLogicMagament drivingLogicManagement = new DrivingLogicMagament(driveLogicManagement, busLineLogicManagement);

    public MenuLogicMagament() {
    }


    public void menu() {
        while (true){
            showMenuContent();
            int choice = functionsChoice();
            switch (choice) {
                case 1:
                    driveLogicManagement.inputNewDriver();
                    break;
                case 2:
                    driveLogicManagement.showDriver();
                    break;
                case 3:
                    busLineLogicManagement.inputNewBusLine();
                    break;
                case 4:
                    busLineLogicManagement.showBusLine();
                    break;
                case 5:
                    drivingLogicManagement.inputNewDriving();
                    break;
                case 6:
                    drivingLogicManagement.showDriving();
                    break;
                case 7:
                    showSortMenu();
                    break;
                case 8:

                    break;
                case 9:
                    return;
            }
        }
    }

    private void showSortMenu() {
        System.out.println("1. Sắp xếp theo Họ tên lái xe");
        System.out.println("2. Sắp xếp theo Số lượng tuyến đảm nhận trong ngày (giảm dần)");
        System.out.println("3. Quay lại menu tổng.");
        int choice;
        do{
            choice = new Scanner(System.in).nextInt();
            if (choice>=1 && choice<=3){
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        switch (choice) {
            case 1:
                drivingLogicManagement.sortByDriveName();
                break;
            case 2:
                drivingLogicManagement.sortByTotalLine();
                break;
            case 3:
                break;
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
        System.out.println("------------PHẦN MỀM QUẢN LÝ TRẢ LƯƠNG CHO GIẢNG VIÊN-------------");
        System.out.println("1. Thêm mới lái xe.");
        System.out.println("2. In danh sách lái xe.");
        System.out.println("3. Thêm mới tuyến xe.");
        System.out.println("4. In danh sách tuyến xe.");
        System.out.println("5. Nhập danh sách phân công cho mỗi lái xe.");
        System.out.println("6. In danh sách phân công.");
        System.out.println("7. Sắp xếp danh sách phân công.");
        System.out.println("8. Tính tổng khoảng cách chạy xe trong ngày.");
        System.out.println("9. THOÁT.");
        System.out.println("MỜI BẠN CHỌN: ");
    }
}
