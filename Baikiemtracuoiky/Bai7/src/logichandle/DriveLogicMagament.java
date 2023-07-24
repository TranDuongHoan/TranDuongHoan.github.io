package logichandle;

import entity.Drive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriveLogicMagament {
    private List<Drive> drives = new ArrayList<>();

    public DriveLogicMagament(List<Drive> drives) {
        this.drives = drives;
    }

    public DriveLogicMagament() {

    }

    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }



    public void inputNewDriver() {
        System.out.println("Bạn muốn thêm mới bao nhiêu lái xe: ");
        int drivesNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < drivesNumber; i++) {
            System.out.println("Nhập thông tin cho lái xe thứ " + (i + 1));
            Drive drive = new Drive();
            drive.inputInfo();
            drives.add(drive);

        }
    }
    public void showDriver(){
        for (int i = 0; i < drives.size(); i++) {
            if (drives.get(i) != null){
                System.out.println(drives.get(i));
            }
        }
    }
    public  boolean driverIsNotEmpty() {
        drives.isEmpty();
    }
}
