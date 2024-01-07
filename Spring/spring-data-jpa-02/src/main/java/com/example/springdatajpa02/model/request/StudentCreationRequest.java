package com.example.springdatajpa02.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentCreationRequest {

    @NotBlank(message = "Tên sinh viên bắt buộc nhập")
    private String name;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    private String address;

    @NotBlank(message = "Số điện thoại bắt buộc nhập")
    private String phone;

    @NotBlank(message = "Tên lớp bắt buộc nhập")
    private String className;

}
