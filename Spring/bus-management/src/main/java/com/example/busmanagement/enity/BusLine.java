package com.example.busmanagement.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusLine {

    private int id;
    private long distance;
    private int numberStation;

}
