package com.test.controller;

import com.test.model.request.OrderRequest;
import com.test.service.EmailService;
import com.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity<?> saveOrder(@PathVariable Long id, @RequestBody OrderRequest request) throws MessagingException {
        orderService.saveOrder(request, id);
        orderService.sendOrderMail(request);
        return ResponseEntity.ok(null);
    }
}
