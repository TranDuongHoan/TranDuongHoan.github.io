package com.example.studentmanager.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentUpdateRequest {

    @NotNull(message = "ID bắt buộc phải nhập")
    @Min(value = 1)
    private Long id;

    @NotBlank(message = "Tên sinh viên bắt buộc nhập")
    @Length(max = 100, message = "Tên sinh viên không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    @Length(max = 100, message = "Địa chỉ không được vượt quá 100 ký tự")
    private String address;

    @NotNull(message = "Số điện thoại bắt buộc phải nhập")
    @Min(value = 10)
    private String phone;

    @NotBlank(message = "Tên lớp bắt buộc nhập")
    @Length(max = 100, message = "Tên lớp không được vượt quá 100 ký tự")
    private String className;
}
