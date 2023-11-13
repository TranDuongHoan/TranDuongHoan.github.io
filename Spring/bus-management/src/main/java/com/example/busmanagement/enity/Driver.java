package com.example.busmanagement.enity;


import com.example.busmanagement.statics.DriverLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private int id;
    private String name;
    private String address;
    private int phone;
    private List<DriverLevel> levels;


}
