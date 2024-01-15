package com.github.truongbb.basicauthentication.repository;

import com.github.truongbb.basicauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByEmail(String mail);
}
