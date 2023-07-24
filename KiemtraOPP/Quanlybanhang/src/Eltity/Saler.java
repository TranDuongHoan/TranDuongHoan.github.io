package Eltity;

import java.util.Scanner;

public class Saler extends Person{
    private static int AUTO_id = 1000;
    private int id;

    public Saler() {
        this.id = AUTO_id;
        AUTO_id++;
    }
    private int localDate;

    public static int getAUTO_id() {
        return AUTO_id;
    }

    public static void setAUTO_id(int AUTO_id) {
        Saler.AUTO_id = AUTO_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocalDate() {
        return localDate;
    }

    public void setLocalDate(int localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Saler{" +
                "id=" + id +
                ", localDate='" + localDate + '\'' +
                '}';
    }

    @Override
    public void inputInfo() {
        System.out.println("Mã nhân viên: ");
        this.setId(new Scanner(System.in).nextInt());
        System.out.println("Ngày ký hợp đồng: ");
        this.setLocalDate(new Scanner(System.in).nextInt());
    }
}
