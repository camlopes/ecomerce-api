package com.app.ecomerce_api.controller;

import com.app.ecomerce_api.dto.ProductRequest;
import com.app.ecomerce_api.dto.ProductResponse;
import com.app.ecomerce_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),
                                                    HttpStatus.CREATED);
    }
}
