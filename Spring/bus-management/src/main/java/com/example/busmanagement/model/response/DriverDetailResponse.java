package com.example.busmanagement.model.response;

import com.example.busmanagement.statics.DriverLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DriverDetailResponse {
    private int id;
    private String name;
    private String address;
    private int phone;
    private List<DriverLevel> levels;

}