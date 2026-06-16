package com.app.ecomerce_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer stockQuantity;
    @NotBlank
    private String category;
    @NotBlank
    private String imageUrl;
}
