package com.example.foodorder.repository;


import com.example.foodorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String s);

    Optional<User> findByUsername(String name);

    Optional<User> findByEmail(String email);
}