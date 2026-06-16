package com.app.ecomerce_api.dto;

import com.app.ecomerce_api.model.Product;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private String imageUrl;
    private Boolean isActive;

    public ProductResponse(Product savedProduct) {
        this.id = savedProduct.getId();
        this.name = savedProduct.getName();
        this.description = savedProduct.getDescription();
        this.price = savedProduct.getPrice();
        this.stockQuantity = savedProduct.getStockQuantity();
        this.category = savedProduct.getCategory();
        this.imageUrl = savedProduct.getImageUrl();
        this.isActive = savedProduct.getIsActive();
    }
}
