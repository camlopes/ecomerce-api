package com.app.ecomerce_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal subtotal;
}
