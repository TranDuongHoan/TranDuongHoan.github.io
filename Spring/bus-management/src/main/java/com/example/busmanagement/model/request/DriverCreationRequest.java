package com.example.busmanagement.model.request;

import com.example.busmanagement.statics.DriverLevel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class DriverCreationRequest {
    @NotNull(message = "ID bắt buộc phải nhập")
    @Min(value = 1)
    private int id;

    @NotBlank(message = "Tên tài xế bắt buộc nhập")
    @Length(max = 100, message = "Tên tài xế không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Địa chỉ bắt buộc nhập")
    @Length(max = 100, message = "Địa chỉ không được vượt quá 100 ký tự")
    private String address;

    @NotNull(message = "Số điện thoại bắt buộc phải nhập")
    @Min(value = 10)
    private int phone;

    @NotEmpty(message = "Hạng xe bắt buộc nhập")
    @Size(max = 1, message = "Hạng xe không được vượt quá 1")
    private List<DriverLevel> levels;

}
