package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User extends BaseEntity{

    @OneToOne
    @JoinTable(name = "seller")
    private Seller seller;

    @Column
    String name;

    @Column
    String address;

    @Column
    String phone;

    @Column
    LocalDateTime birthday;

    @Column
    String avatar;

    @Column
    String email;

    @Column
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    
}
