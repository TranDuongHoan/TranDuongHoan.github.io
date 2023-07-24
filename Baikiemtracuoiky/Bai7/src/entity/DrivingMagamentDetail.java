package entity;

public class DrivingMagamentDetail {
    private BusLine busLine;
    private int lineNumber;

    @Override
    public String toString() {
        return "DrivingMagamentDetail{" +
                "busLine=" + busLine +
                ", lineNumber=" + lineNumber +
                '}';
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public DrivingMagamentDetail(BusLine busLine, int lineNumber) {
        this.busLine = busLine;
        this.lineNumber = lineNumber;
    }

    public BusLine getBusLine() {
        return busLine;
    }

    public void setBusLine(BusLine busLine) {
        this.busLine = busLine;
    }


}
