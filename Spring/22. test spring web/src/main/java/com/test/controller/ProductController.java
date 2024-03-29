package com.test.controller;

import com.google.gson.Gson;
import com.test.exception.ProductNotFoundException;
import com.test.model.request.ProductRequest;
import com.test.model.request.SearchProductRequest;
import com.test.model.response.ProductDetailResponse;
import com.test.model.response.ProductResponse;
import com.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;
    private final Gson gson;

    @GetMapping
    public String getProduct(Model model, SearchProductRequest request) {
        ProductResponse productResponse = productService.searchProduct(request);
        model.addAttribute("products", productResponse.getProducts());
        model.addAttribute("requestSearch", request);
        model.addAttribute("currentPage", productResponse.getCurrentPage());
        model.addAttribute("totalPage", productResponse.getTotalPage());
        model.addAttribute("totalElement", productResponse.getTotalElement());
        model.addAttribute("pageSize", productResponse.getPageSize());
        return "product/products";
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable Long id) throws ProductNotFoundException {
        ProductDetailResponse product = productService.getProductDetails(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestPart("productRequest") String productRequest,
                                    @RequestPart("image") MultipartFile image) {
        ProductRequest request = gson.fromJson(productRequest, ProductRequest.class);
        productService.create(request, image);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/products")
    public ResponseEntity<?> update(@RequestBody ProductRequest request) {
        productService.save(request);
        return ResponseEntity.ok(null);
    }

}
