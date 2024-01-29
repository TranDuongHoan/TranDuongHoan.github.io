package com.example.foodorder.model.request;

import com.example.foodorder.statics.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank(message = "Username is required")
    @Length(max = 100, message = "Username must be less than 100 characters")
    String name;

    @NotBlank(message = "Password is required")
    String password;

    @Length(max = 255, message = "Address must be less than 255 characters")
    String address;

    @Pattern(regexp = "^0[0-9]{9}", message = "Phone must be 10 characters, start with zero")
    String phone;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    LocalDateTime birthday;

    @NotEmpty(message = "Avatar is required")
    String avatar;

    @NotBlank(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Email must be email format")
    @Length(max = 255, message = "Email must be less than 255 characters")
    String email;

    UserStatus userStatus;

}
