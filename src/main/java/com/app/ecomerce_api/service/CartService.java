package com.app.ecomerce_api.service;

import com.app.ecomerce_api.dto.CartItemRequest;
import com.app.ecomerce_api.model.CartItem;
import com.app.ecomerce_api.model.Product;
import com.app.ecomerce_api.model.User;
import com.app.ecomerce_api.repository.CartItemRepository;
import com.app.ecomerce_api.repository.ProductRepository;
import com.app.ecomerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public boolean addToCart(String userId, CartItemRequest request) {
        // Check for product existence
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty())
            return false;

        // Check for product quantity
        Product product = productOpt.get();
        if (product.getStockQuantity() < request.getQuantity())
            return false;

        // Check for user existence
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty())
            return false;

        User user = userOpt.get();

        // Update existing cart item or create a new one
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem != null) {
            // Update the quantity and price of the existing cart item
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, Long productId) {
        // Check for user existence
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty())
            return false;

        User user = userOpt.get();

        // Check for product existence
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty())
            return false;

        Product product = productOpt.get();

        // Find the cart item
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (cartItem == null)
            return false;

        // Delete the cart item
        cartItemRepository.delete(cartItem);
        return true;
    }

    public List<CartItem> getCart(String userId) {
        return userRepository.findById(Long.valueOf(userId))
                .map(cartItemRepository::findByUser)
                .orElseGet(List::of);
    }
}
