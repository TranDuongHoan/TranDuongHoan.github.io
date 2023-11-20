package com.example.busmanagement.repository;


import ch.qos.logback.core.util.FileUtil;
import com.example.busmanagement.enity.Driver;
import com.example.busmanagement.exception.DriverNotFoundException;

import com.example.busmanagement.model.request.DriverUpdateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DriverRepository {

    private static final String DRIVER_DATA_FILE_NAME = "drivers";

    public static int AUTO_ID = 1000;

    private final FileUtil<Driver> fileUtil;


    public List<Driver> getAll() {
        return fileUtil.readDataFromFile(DRIVER_DATA_FILE_NAME, Driver[].class);
    }

    public List<Driver> delete(int id) throws DriverNotFoundException {
        List<Driver> drivers = getAll();
        if (CollectionUtils.isEmpty(drivers)) {
            throw new DriverNotFoundException("Drivers not found");
        }
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == id) {
                drivers.remove(i);
                fileUtil.writeDataToFile(DRIVER_DATA_FILE_NAME, drivers);
                return drivers;
            }
        }
        return null;
    }

    public List<Driver> createDriver(Driver driver) {
        List<Driver> books = getAll();
        if (CollectionUtils.isEmpty(drivers)) {
            drivers = new ArrayList<>();
        }
        drivers.add(driver);
        fileUtil.writeDataToFile(DRIVER_DATA_FILE_NAME, drivers);
        return drivers;
    }

    public Driver findById(int id) throws DriverNotFoundException {
        List<Driver> drivers = getAll();
        if (drivers == null || drivers.isEmpty()) {
            throw new DriverNotFoundException("Drivers not found");
        }
        return drivers.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Driver> updateDriver(DriverUpdateRequest driver) throws DriverNotFoundException {
        List<Driver> drivers = getAll();
        if (CollectionUtils.isEmpty(drivers)) {
            throw new DriverNotFoundException("Drivers not found");
        }

        Optional<Driver> bookCanUpdate = drivers.stream().filter(b -> b.getId() == driver.getId()).findFirst();
        if (bookCanUpdate.isEmpty()) {
            throw new DriverNotFoundException("Drivers not found");
        }

        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == driver.getId()) {
                drivers.get(i).setName(driver.getName());
                drivers.get(i).setAddress(driver.getAddress());
                drivers.get(i).setPhone(driver.getPhone());                
                drivers.get(i).setLevels(driver.getLevels());
                fileUtil.writeDataToFile(DRIVER_DATA_FILE_NAME, drivers);
                return drivers;
            }
        }
        return null;
    }


}
