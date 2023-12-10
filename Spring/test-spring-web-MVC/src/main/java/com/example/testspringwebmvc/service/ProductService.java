package com.example.testspringwebmvc.service;

import com.example.testspringwebmvc.entity.Product;
import com.example.testspringwebmvc.exception.ProductNotFoundException;
import com.example.testspringwebmvc.model.request.ProductCreationRequest;
import com.example.testspringwebmvc.model.response.ProductResponse;
import com.example.testspringwebmvc.repository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;

    public List<ProductResponse> getProduct() {
        List<Product> products = productJpaRepository.findAll();

        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .image(product.getImage())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public ProductResponse getProductDetails(Long id) throws ProductNotFoundException {

        return productJpaRepository.findById(id)
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .image(product.getImage())
                        .build()
                )
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with id " + id + " could not be found")
                );
    }

    public void createProduct(ProductCreationRequest productCreationRequest) {
        Product product = Product.builder()
                .name(productCreationRequest.getName())
                .price(productCreationRequest.getPrice())
                .description(productCreationRequest.getDescription())
                .image(productCreationRequest.getImage())
                .build();
        productJpaRepository.save(product);
    }

    public void updateProduct(Long id, ProductCreationRequest request) throws ProductNotFoundException {

        Optional<Product> productOptional =  productJpaRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " could not be found");
        }
        Product product = productOptional.get();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        productJpaRepository.save(product);
    }


}
