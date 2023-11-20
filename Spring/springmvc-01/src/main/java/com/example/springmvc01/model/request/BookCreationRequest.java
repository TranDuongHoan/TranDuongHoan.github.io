package com.example.springmvc01.model.request;

import com.example.springmvc01.statics.BookCategory;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BookCreationRequest {

    @NotBlank(message = "Tên sách bắt buộc nhập")
    @Length(max = 100, message = "Tên sách không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Tên tác giả bắt buộc nhập")
    @Length(max = 100, message = "Tên tác giả không được vượt quá 100 ký tự")
    private String author;

    @Min(value = 1990, message = "Thư viện của tao nói không với sách cũ")
    private int publishedYear;

    @Length(max = 1000, message = "Mô tả sách không được vượt quá 1000 ký tự")
    private String description;

    @NotEmpty(message = "Thể loại sách bắt buộc nhập")
    @Size(max = 10, message = "Thể loại không được vượt quá 10")
    private List<BookCategory> categories;

}
