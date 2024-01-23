package com.example.foodorder.model.request;


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
    String name;

    @NotBlank
    String password;

    @NotBlank
    String phone;

    @NotBlank
    @Size(max = 50)
    String email;

    String role;




}
