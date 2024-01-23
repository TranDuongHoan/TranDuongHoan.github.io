package com.example.foodorder.service;

import com.example.foodorder.repository.MailSendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSendingService {
    private final MailSendingRepository mailSendingRepository;


}
