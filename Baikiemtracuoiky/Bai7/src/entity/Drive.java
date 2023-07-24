package entity;

import stactics.DriveLevel;

import java.util.Scanner;

public class Drive extends Person{
    private static int AUTO_ID = 10000;
    private int id;
    private String name;
    private String address;
    private int phone;
    private DriveLevel level;

    public Drive() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getPhone() {
        return phone;
    }

    @Override
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public DriveLevel getLevel() {
        return level;
    }

    public void setLevel(DriveLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }


    @Override
    public void inputInfo(){
        super.inputInfo();
        System.out.println("Nhập trình độ: ");
        System.out.println("1. Loại A");
        System.out.println("2. Loại B");
        System.out.println("3. Loại C");
        System.out.println("4. Loại D");
        System.out.println("5. Loại E");
        System.out.println("6. Loại F");
        System.out.println("MỜI BẠN CHỌN: ");
        int levelChoice;
        do{
            levelChoice = new Scanner(System.in).nextInt();
            if (levelChoice>=1 && levelChoice<=6){
                break;
            }
            System.out.println("Nhập sai, mời bạn nhập lại:");
        }
        while (true);
        switch (levelChoice) {
            case 1:
                this.setLevel(DriveLevel.LOAI_A);
                break;
            case 2:
                this.setLevel(DriveLevel.LOAI_B);
                break;
            case 3:
                this.setLevel(DriveLevel.LOAI_C);
                break;
            case 4:
                this.setLevel(DriveLevel.LOAI_D);
                break;
            case 5:
                this.setLevel(DriveLevel.LOAI_E);
                break;
            case 6:
                this.setLevel(DriveLevel.LOAI_F);
                break;
        }
    }
}
