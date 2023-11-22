package com.example.studentmanagement.statics;

public enum SubjectType {

    DAI_CUONG("Đại cương"),
    CO_SO("Cơ sở"),
    CHUYEN_NGHANH("Chuyên ngành");
    private String value;
    SubjectType(String value){
        this.value = value;
    }
}
