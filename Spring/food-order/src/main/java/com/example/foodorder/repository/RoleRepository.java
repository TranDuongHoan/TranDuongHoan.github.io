package com.example.foodorder.repository;

import com.example.foodorder.entity.Role;
import com.example.foodorder.statics.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {



    Optional<Role> findByName(Roles user);
}
