package com.app.ecomerce_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;
}
