package com.example.foodorder.entity;


import com.example.foodorder.statics.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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
    Seller seller;

    @Column(name = "user_name")
    String username;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Column(name = "birthday")
    LocalDateTime birthday;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();


    
}
