package com.example.springmvc01.model.request;

import com.example.springmvc01.statics.ReaderLevel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ReaderCreationRequest {

    private String name;
    private String address;
    private int phone;
    private List<ReaderLevel> levels;

}
