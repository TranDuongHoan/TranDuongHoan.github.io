package com.example.foodorder.service;

import com.example.foodorder.repository.MailUrlJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailUrlService {
    private final MailUrlJpaRepository mailUrlJpaRepository;


}
