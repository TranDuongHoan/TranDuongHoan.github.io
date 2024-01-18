package com.example.foodorder.model.request;

import com.example.foodorder.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    @NotBlank
    @Size(max = 50)
    String username;

    @NotBlank
    String password;

    @NotBlank
    String phone;

    @NotBlank
    Role role;

}
