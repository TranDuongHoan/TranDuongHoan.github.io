package com.example.foodorder.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank
    @Size(max = 100)
    String username;

    @NotBlank
    String password;

    @NotBlank
    String address;

    @NotBlank
    String phone;

    @NotBlank
    LocalDateTime birthday;

    @NotBlank
    String avatar;

    @NotBlank
    String email;

}
