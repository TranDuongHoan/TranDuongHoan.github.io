package com.example.foodorder.entity;

import com.example.foodorder.statics.Roles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    Roles name;

}