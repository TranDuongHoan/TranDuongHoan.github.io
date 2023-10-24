package com.example.librarymanagementspring.stactics;

public enum BookSpecialization {
    KHTN("Khoa học tự nhiên"),
    VHNT("Văn học - Nghệ thuật"),
    DTVT("Điện tử viễn thông"),
    CNTT("Công nghệ thông tin");

    public String value;

    BookSpecialization(String value) {
        this.value = value;
    }
}
