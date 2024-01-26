package com.example.foodorder.repository;


import com.example.foodorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String s);

    Optional<User> findByUsername(String name);

    User findByEmail(String email);
}