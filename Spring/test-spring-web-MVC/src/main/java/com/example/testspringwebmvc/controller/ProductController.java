package com.example.testspringwebmvc.controller;


import com.example.testspringwebmvc.exception.ProductNotFoundException;
import com.example.testspringwebmvc.model.request.ProductCreationRequest;
import com.example.testspringwebmvc.model.response.ProductResponse;
import com.example.testspringwebmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getProduct(Model model) {
        List<ProductResponse> products = productService.getProduct();
        model.addAttribute("dsSanPham", products);
        return "admin";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long id) throws ProductNotFoundException {
        ProductResponse product = productService.getProductDetails(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        productService.createProduct(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductCreationRequest request) throws ProductNotFoundException {
        productService.updateProduct(id, request);
        return ResponseEntity.ok(null);
    }




}
