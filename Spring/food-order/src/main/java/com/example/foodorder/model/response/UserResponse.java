package com.example.foodorder.model.response;

import com.example.foodorder.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    Long id;

    String name;

    Set<Role> roles;

    String password;

    String address;

    String phone;

    LocalDateTime birthday;

    String avatar;

    String email;

    LocalDateTime createdDateTime;

    String createdBy;

    LocalDateTime lastModifiedDateTime;

    String lastModifiedBy;


}
