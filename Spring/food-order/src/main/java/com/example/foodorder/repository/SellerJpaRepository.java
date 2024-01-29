package com.example.foodorder.repository;

import com.example.foodorder.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<Seller, Long> {
}
