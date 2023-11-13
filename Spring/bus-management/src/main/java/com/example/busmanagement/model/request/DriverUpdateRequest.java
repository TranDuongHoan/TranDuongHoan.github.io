package com.example.busmanagement.model.request;

import com.example.busmanagement.statics.DriverLevel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class DriverUpdateRequest {
    private int id;
    private String name;
    private String address;
    private int phone;
    private List<DriverLevel> levels;

}
