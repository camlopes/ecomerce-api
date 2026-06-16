package com.app.ecomerce_api.service;

import com.app.ecomerce_api.dto.ProductRequest;
import com.app.ecomerce_api.dto.ProductResponse;
import com.app.ecomerce_api.model.Product;
import com.app.ecomerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product(productRequest);
        Product savedProduct = productRepository.save(product);
        return new ProductResponse(savedProduct);
    }
}
