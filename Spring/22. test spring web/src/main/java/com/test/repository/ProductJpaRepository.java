package com.test.repository;

import com.test.entity.Appointment;
import com.test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
