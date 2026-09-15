package com.app.ecomerce_api.controller;

import com.app.ecomerce_api.dto.CartItemRequest;
import com.app.ecomerce_api.model.CartItem;
import com.app.ecomerce_api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, @RequestBody CartItemRequest request) {
        if (cartService.addToCart(userId, request)) {;
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.badRequest().body("Product Out of Stock or User not found or Product not found");
        }
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> removeFromCart(@RequestHeader("X-User-ID") String userId, @PathVariable Long productId) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}
