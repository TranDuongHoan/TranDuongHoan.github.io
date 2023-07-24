package logichandle;

import entity.BusLine;
import entity.Drive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusLineLogicMagament {
    private List<BusLine> buslines = new ArrayList<>();

    public BusLineLogicMagament(List<BusLine> buslines) {
        this.buslines = buslines;
    }

    public BusLineLogicMagament() {

    }

    public List<BusLine> getBuslines() {
        return buslines;
    }

    public void setBuslines(List<BusLine> buslines) {
        this.buslines = buslines;
    }


    public void inputNewBusLine() {
        System.out.println("Bạn muốn thêm mới bao nhiêu tuyến xe: ");
        int buslinesNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < buslinesNumber; i++) {
            System.out.println("Nhập thông tin cho tuyến xe thứ " + (i + 1));
            BusLine busline = new BusLine();
            busline.inputInfo();
            buslines.add(busline);
        }
    }
    public void showBusLine(){
        for (int i = 0; i < buslines.size(); i++) {
            if (buslines.get(i) != null){
                System.out.println(buslines.get(i));
            }
        }
    }
    public  boolean buslineIsNotEmpty() {
        buslines.isEmpty();
    }
}
