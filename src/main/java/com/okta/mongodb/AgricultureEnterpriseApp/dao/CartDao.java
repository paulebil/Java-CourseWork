package com.okta.mongodb.AgricultureEnterpriseApp.dao;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

    // Optional: Find a cart by its ID
    @SuppressWarnings("null")
    Optional<Cart> findById(Integer cartId);
    
    
}