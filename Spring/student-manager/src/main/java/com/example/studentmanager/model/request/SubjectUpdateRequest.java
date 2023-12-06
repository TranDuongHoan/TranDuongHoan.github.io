package com.example.studentmanager.model.request;

import com.example.studentmanager.statics.SubjectType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SubjectUpdateRequest {

    @NotNull(message = "ID bắt buộc phải nhập")
    @Min(value = 1)
    private Long id;

    @NotBlank(message = "Tên môn học bắt buộc nhập")
    @Length(max = 100, message = "Tên môn học không được vượt quá 100 ký tự")
    private String name;

    @NotNull(message = "Tín chỉ bắt buộc phải nhập")
    @Min(value = 7)
    private Integer credit;

    @NotBlank(message = "Ngành bắt buộc nhập")
    @Min(value = 1, message = "Ngành ít nhất là 1 trở lên")
    private SubjectType subjectType;
}
