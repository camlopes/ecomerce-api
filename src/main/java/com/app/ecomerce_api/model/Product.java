package com.app.ecomerce_api.model;

import com.app.ecomerce_api.dto.ProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "products_table")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private String imageUrl;
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product(@Valid ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
        this.stockQuantity = productRequest.getStockQuantity();
        this.category = productRequest.getCategory();
        this.imageUrl = productRequest.getImageUrl();
    }
}
