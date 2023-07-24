package logichandle;

import entity.BusLine;
import entity.Drive;
import entity.DrivingMagament;
import entity.DrivingMagamentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrivingLogicMagament {

    private final List<DrivingLogicMagament> drivingMagaments = new ArrayList<>();
    private DriveLogicMagament driveLogicMagament;
    private BusLineLogicMagament busLineLogicMagament;

    public DrivingLogicMagament(DriveLogicMagament driveLogicMagament, BusLineLogicMagament busLineLogicMagament) {
        this.driveLogicMagament = driveLogicMagament;
        this.busLineLogicMagament = busLineLogicMagament;
    }

    public void showDriving() {
        for (int i = 0; i < drivingMagaments.size(); i++) {
            if (drivingMagaments.get(i) != null){
                System.out.println(drivingMagaments.get(i));
            }
        }
    }

    public void inputNewDriving() {
        if(!driveLogicMagament.driverIsNotEmpty() || !busLineLogicMagament.buslineIsNotEmpty()) {
            System.out.println("Không có dữ liệu lái xe và tuyến xe, vui lòng tạo đủ dư liệu trước khi phân công:");
            return;
        }
        System.out.println("Nhập số lái xe bạn muốn phân công:");
        int driveNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < driveNumber; i++) {
            System.out.println("Nhập thông tin cho lái xe thứ" + (i + 1) + "muốn phân công.");
            System.out.println("Nhập ID lái xe:");
            int driverId;
            Drive drive = null;
            do{
                driverId = new Scanner(System.in).nextInt();
                for (int j = 0; j < driveLogicMagament.getDrives().size(); j++) {
                    if (driveLogicMagament.getDrives().get(j).getId() == driverId) {
                        drive = driveLogicMagament.getDrives().get(j);
                        break;
                    }
                }
                if (drive != null) {
                    break;
                }
                System.out.println("Không tồn tại ID lái xe vừa nhập, xin vui lòng nhập lại: ");
            }while (true);

            System.out.println("Lái xe này lái bao nhiêu tuyến: ");
            int busNumber = new Scanner(System.in).nextInt();
            List<DrivingLogicMagament> details;
            int count = 0;
            int totalDistance = 0;
            for (int j = 0; j < busNumber; j++) {
                System.out.println("Tuyến xe thứ " + (j + 1) + "mà lái xe này muốn lái là tuyến nào: ");
                int buslineId;
                BusLine busLine = null;
                do{
                    buslineId = new Scanner(System.in).nextInt();
                    for (int k = 0; k < busLineLogicMagament.getBuslines().size(); k++) {
                        if (busLineLogicMagament.getBuslines().get(k).getId() == buslineId) {
                            busLine = busLineLogicMagament.getBuslines().get(k);
                            break;
                        }
                    }
                    if (busLine != null) {
                        break;
                    }
                    System.out.println("Không tồn tại tuyến xe có ID vừa nhập, xin vui lòng nhập lại: ");
                }while (true);

                System.out.println("Lái xe chạy tuyến xe này bao nhiêu lượt:");
                int lineNumber;
                do {
                    lineNumber = new Scanner(System.in).nextInt();
                    if (lineNumber < 1 || lineNumber > 15) {
                        System.out.println("Số lượt trong ngày phải là số dương và nhỏ hơn 15");
                        continue;
                    }
                    int temp = totalDistance;
                    temp+=lineNumber * busLine.getDistance();
                    if (temp > 15){
                        System.out.println("Nếu số lượt chạy quá 15 lần trong ngày, vui lòng nhập lại: ");
                        continue;
                    }
                    break;
                } while (true);
                DrivingMagamentDetail drivingMagamentDetail = new DrivingMagamentDetail(busLine, lineNumber);
                details[count] = drivingMagamentDetail;
                count++;
                totalDistance += busNumber * busLine.getDistance();
            }
            DrivingMagament drivingMagament = new DrivingMagament(drive, details);
            saveDriving(drivingMagament);
        }
    }

    public void saveDriving(DrivingMagament drivingMagament){
        for(int i=0; i < drivingMagaments.size(); i++){
            if(drivingMagaments.get(i) == null){
                drivingMagaments.get(i) = DrivingMagament;
                break;
            }
        }
    }
    private boolean isEmptyDriving(){
        drivingMagaments.isEmpty();
    }

    public void sortByDriveName() {
        if (isEmptyDriving()){
            System.out.println("Chưa có dự liệu phân công giảng dạy, vui lòng nhập dữ liệu trước khi sắp xếp");
            return;
        }

        for (int i = 0; i < drivingMagaments.size() -1; i++){
            for (int j = i+1; j < drivingMagaments.size(); j++){
                if(drivingMagaments.get(i).getDrive().getName().compareToIgnoreCase(drivingMagaments.get(j).getDrive().getName()) > 0){
                    DrivingMagament temp = drivingMagaments.get(i);
                    drivingMagaments.get(i) = drivingMagaments.get(j);
                    drivingMagaments.get(j) = temp;
                }
            }
        }
        showDriving();
    }

    public void sortByTotalLine() {
        if (isEmptyDriving()){
            System.out.println("Chưa có dữ liệu phân công, vui lòng nhập dữ liệu trước khi sắp xếp");
            return;
        }
        for(int i = 0; i< drivingMagaments.size(); i++){
            for (int j = 0; j < drivingMagaments.get(i).getDetails.size(); j++){
                for(int k = j + 1; k < drivingMagaments.get(i).getDetails().size(); k++){
                    if(drivingMagaments.get(i).getDetails()[j].getSubject().gettotalLine()){
                    }
                }
            }
        }
        showDriving();
    }
}
