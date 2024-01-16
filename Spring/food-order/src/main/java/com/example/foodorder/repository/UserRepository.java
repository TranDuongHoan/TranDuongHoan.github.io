package com.example.foodorder.repository;


import com.example.foodorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String s);

    User findByUsername(String username);
}