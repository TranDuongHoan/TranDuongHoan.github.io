package com.example.studentmanager.statics;

import lombok.Data;
import lombok.Getter;

@Getter
public enum SubjectType {
    DAI_CUONG("Đại cương"),
    CO_SO("Cơ sở"),
    CHUYEN_NGHANH("Chuyên ngành");
    public String value;

    SubjectType(String value) {
        this.value = value;
    }
}
