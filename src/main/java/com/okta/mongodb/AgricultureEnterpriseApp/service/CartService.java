
package com.okta.mongodb.AgricultureEnterpriseApp.service;

import com.okta.mongodb.AgricultureEnterpriseApp.dao.CartDao;
import com.okta.mongodb.AgricultureEnterpriseApp.model.Cart;
import com.okta.mongodb.AgricultureEnterpriseApp.model.CartItem;
import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    // Add product to cart
    public ResponseEntity<String> addProductToCart(String cartIdStr, Product product) {
        Integer cartId = parseCartId(cartIdStr); // Convert String to Integer
        Cart cart = cartDao.findById(cartId).orElse(new Cart());
        
        // Check if the item already exists in the cart
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);
        
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1); // Increase quantity if exists
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(1);
            cart.getItems().add(newCartItem); // Add new item to the cart
        }
        
        cartDao.save(cart);
        return new ResponseEntity<>("Product added to cart", HttpStatus.OK);
    }

    // Get all products in the cart
    public ResponseEntity<List<CartItem>> getProductsInCart(String cartIdStr) {
        Integer cartId = parseCartId(cartIdStr); // Convert String to Integer
        Cart cart = cartDao.findById(cartId).orElse(null);
        
        if (cart != null && cart.getItems() != null) {  // Ensure items are not null
            return new ResponseEntity<>(cart.getItems(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    // Clear the cart
    public ResponseEntity<String> clearCart(String cartIdStr) {
        Integer cartId = parseCartId(cartIdStr); // Convert String to Integer
        Cart cart = cartDao.findById(cartId).orElse(null);
        
        if (cart != null) {
            cart.getItems().clear();
            cartDao.save(cart);
            return new ResponseEntity<>("Cart cleared", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
        }
    }

    // Helper method to parse cartId
    private Integer parseCartId(String cartIdStr) {
        if (cartIdStr == null || cartIdStr.isEmpty()) {
            throw new IllegalArgumentException("cartId must not be null or empty");
        }
        
        try {
            return Integer.valueOf(cartIdStr); // Convert String to Integer
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid cartId format: " + cartIdStr, e);
        }
    }
}
