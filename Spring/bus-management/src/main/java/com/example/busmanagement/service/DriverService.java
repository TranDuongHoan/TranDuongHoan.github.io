package com.example.busmanagement.service;

import com.example.busmanagement.enity.Driver;
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

    public void deleteDriver(int id) {
        driverRepository.delete(id);
    }

    public void createDriver(DriverCreationRequest driverCreationRequest) {
       driverRepository.createDriver(driverCreationRequest);
    }

    public DriverDetailResponse findById(int id) {
        Driver driver = driverRepository.findById(id);
        return DriverDetailResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .address(driver.getAddress())
                .phone(driver.getPhone())
                .levels(driver.getLevels())
                .build();
    }

    public void updateDriver(DriverUpdateRequest driver){
        driverRepository.updateDriver(driver);
    }
}
