package com.example.foodorder.repository;


import com.example.foodorder.entity.MailSending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailSendingRepository extends JpaRepository<MailSending, Long> {
}
