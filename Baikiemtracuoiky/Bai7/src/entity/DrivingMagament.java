package entity;

import java.util.Arrays;

public class DrivingMagament {
    private Drive drive;
    private DrivingMagamentDetail[] details;
    private double totalDistance;

    public DrivingMagament(Drive drive, DrivingMagamentDetail[] details, double totalDistance) {
        this.drive = drive;
        this.details = details;
        this.totalDistance = totalDistance;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public DrivingMagamentDetail[] getDetails() {
        return details;
    }

    public void setDetails(DrivingMagamentDetail[] details) {
        this.details = details;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "DrivingMagament{" +
                "drive=" + drive +
                ", details=" + Arrays.toString(details) +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
