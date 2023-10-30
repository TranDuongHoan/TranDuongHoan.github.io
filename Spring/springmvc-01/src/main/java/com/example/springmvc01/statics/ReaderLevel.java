package com.example.springmvc01.statics;

public enum ReaderLevel {

    STUDENT("Sinh viên"),
    POSTGRADUATE("Học viên cao học"),
    TEACHER("Giáo viên");


    public String value;

    ReaderLevel(String value) {
        this.value = value;
    }
}
