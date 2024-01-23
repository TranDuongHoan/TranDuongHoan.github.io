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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;

    private String name;

    private Set<Role> roles;

    private String password;

    private String address;

    private String phone;

    private LocalDateTime birthday;

    private String avatar;

    private String email;

    private LocalDateTime createdDateTime;

    private String createdBy;

    private LocalDateTime lastModifiedDateTime;

    private String lastModifiedBy;


}
