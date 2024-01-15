package com.example.foodorder.repository;


import com.example.foodorder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


//    User isEmpty(User admin);

    User findByPhone(String s);

    User findByEmail(String s);
}