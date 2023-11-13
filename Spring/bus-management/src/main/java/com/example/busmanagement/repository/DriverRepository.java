package com.example.busmanagement.repository;


import com.example.busmanagement.enity.Driver;
import com.example.busmanagement.model.request.DriverCreationRequest;
import com.example.busmanagement.model.request.DriverUpdateRequest;
import com.example.busmanagement.statics.DriverLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverRepository {

    private static final List<Driver> drivers = new ArrayList<>();

    private static int AUTO_ID = 1000;

    static {
        for (int i = 0; i < 5; i++) {
            Driver driver = Driver.builder()
                    .id(AUTO_ID++)
                    .name("Nguyễn Văn" + i)
                    .address("Trần Văn"  + i)
                    .levels(Arrays.asList(DriverLevel.A, DriverLevel.B, DriverLevel.C, DriverLevel.D, DriverLevel.E, DriverLevel.F))
                    .phone(i)
                    .build();

            drivers.add(driver);
        }
    }

    public List<Driver> getAll(){
        return drivers;
    }


    public void createDriver(DriverCreationRequest driverCreationRequest) {
        Driver driver = Driver.builder()
                .id(AUTO_ID++)
                .name(driverCreationRequest.getName())
                .address(driverCreationRequest.getAddress())
                .phone(driverCreationRequest.getPhone())
                .levels(driverCreationRequest.getLevels())
                .build();
        drivers.add(driver);
    }

    public void delete(int id) {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == id) {
                drivers.remove(i);
                return;
            }
        }
    }

    public Driver findById(int id) {
        for (int i = 0; i < drivers.size(); i++){
            if (drivers.get(i).getId() == id){
                return drivers.get(i);
            }
        }
        return null;
    }

    public void updateDriver(DriverUpdateRequest driver) {
        for (int i = 0; i < drivers.size(); i++){
            if (drivers.get(i).getId() == driver.getId()){
                drivers.get(i).setName(driver.getName());
                drivers.get(i).setAddress(driver.getAddress());
                drivers.get(i).setPhone(driver.getPhone());
                drivers.get(i).setLevels(driver.getLevels());
                break;
            }
        }
    }
}
