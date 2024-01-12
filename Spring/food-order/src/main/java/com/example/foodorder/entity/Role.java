package com.example.foodorder.entity;

import com.example.foodorder.statics.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.util.List;


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

    @ManyToMany(mappedBy = "user_role")
    private List<User> users;

}
