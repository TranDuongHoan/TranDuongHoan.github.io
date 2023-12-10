package com.example.testspringwebmvc.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductCreationRequest {

    @NotBlank(message = "Tên sản phẩm bắt buộc nhập")
    @Length(max = 255, message = "Tên sản phẩm không được vượt quá 255 ký tự")
    private String name;

    @NotBlank(message = "Đơn giá bắt buộc nhập")
    @Min(value = 0)
    private Long price;

    @Length(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
    private String description;

    @NotBlank(message = "Hình ảnh bắt buộc phải hiển thị")
    private String image;

}
