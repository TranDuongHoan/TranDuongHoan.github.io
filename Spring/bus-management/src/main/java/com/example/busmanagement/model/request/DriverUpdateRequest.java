package com.example.busmanagement.model.request;

import com.example.busmanagement.statics.DriverLevel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class DriverUpdateRequest {
    @NotNull(message = "ID bắt buộc phải nhập")
    @Min(value = 1)
    private Integer id;

    @NotBlank(message = "Tên sách bắt buộc nhập")
    @Length(max = 100, message = "Tên sách không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Tên tác giả bắt buộc nhập")
    @Length(max = 100, message = "Tên tác giả không được vượt quá 100 ký tự")
    private String address;

    @NotNull(message = "ID bắt buộc phải nhập")
    @Min(value = 10)
    private int phone;

    @NotEmpty(message = "Thể loại sách bắt buộc nhập")
    @Size(max = 10, message = "Thể loại không được vượt quá 10")
    private List<DriverLevel> levels;

}
