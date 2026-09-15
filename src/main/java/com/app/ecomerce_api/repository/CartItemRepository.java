package com.app.ecomerce_api.repository;

import com.app.ecomerce_api.model.CartItem;
import com.app.ecomerce_api.model.Product;
import com.app.ecomerce_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
