package com.example.busmanagement.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverManagement {
    private Driver driver;
    private BusLine busline;
    private int numberDrives;
    private LocalDate assignmentTime;
}
