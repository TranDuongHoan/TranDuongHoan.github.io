package com.example.busmanagement.service;

import com.example.busmanagement.enity.Driver;
import com.example.busmanagement.exception.DriverNotFoundException;
import com.example.busmanagement.model.request.DriverCreationRequest;
import com.example.busmanagement.model.request.DriverUpdateRequest;
import com.example.busmanagement.model.response.DriverDetailResponse;
import com.example.busmanagement.repository.DriverRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getAll() {
        return driverRepository.getAll();
    }

    public List<Driver> deleteDriver(int id) throws DriverNotFoundException {
        driverRepository.delete(id);
        return null;
    }

    public List<Driver> createDriver(DriverCreationRequest driverCreationRequest) {
        Driver driver = Driver.builder()
                .id(DriverRepository.AUTO_ID++)
                .name(driverCreationRequest.getName())
                .address(driverCreationRequest.getAddress())
                .phone(driverCreationRequest.getPhone())
                .levels(driverCreationRequest.getLevels())
                .build();
        return driverRepository.createDriver(driver);
    }

    public DriverDetailResponse findById(int id) throws DriverNotFoundException {
        Driver driver = driverRepository.findById(id);
        return DriverDetailResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .address(driver.getAddress())
                .phone(driver.getPhone())
                .levels(driver.getLevels())
                .build();
    }

    public void updateDriver(DriverUpdateRequest driver) throws DriverNotFoundException {
        driverRepository.updateDriver(driver);
    }
}
