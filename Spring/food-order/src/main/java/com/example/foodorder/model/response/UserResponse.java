package com.example.foodorder.model.response;

import com.example.foodorder.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    Long id;

    String username;

    Set<Role> roles;

    String password;

    String address;

    String phone;

    LocalDateTime birthday;

    String avatar;

    String email;


}
