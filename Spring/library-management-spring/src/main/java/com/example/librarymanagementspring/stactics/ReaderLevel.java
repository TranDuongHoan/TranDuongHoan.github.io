package com.example.librarymanagementspring.stactics;

public enum ReaderLevel {
    SV("sinh viên"),
    HVCH("học viên cao học"),
    GV("giáo viên");

    public String value;

    ReaderLevel(String value) {
        this.value = value;
    }
}
