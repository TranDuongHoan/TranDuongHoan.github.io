package entity;

import java.util.Scanner;

public class BusLine implements InputInfo{
    private static int AUTO_ID = 100;
    private int id;
    private double distance;
    private int station;
    private int totalLine;

    public int getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(int totalLine) {
        this.totalLine = totalLine;
    }

    public BusLine() {
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "id=" + id +
                ", distance=" + distance +
                ", station=" + station +
                '}';
    }

    @Override
    public void inputInfo(){
        System.out.println("Mã tuyến: ");
        this.setId(new Scanner(System.in).nextInt());
        System.out.println("Khoảng cách: ");
        this.setDistance(new Scanner(System.in).nextDouble());
        System.out.println("Trạm dừng: ");
        this.setStation(new Scanner(System.in).nextInt());

    }
}
