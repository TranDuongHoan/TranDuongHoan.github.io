package com.example.librarymanagementspring.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person implements InputInfo {
    String name;
    String address;
    String phone;


    @Override
    public void inputInfo() {
        System.out.println("Nhập tên: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập địa chỉ: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.println("Nhập số điện thoại: ");
        this.setPhone(new Scanner(System.in).nextLine());
    }
}