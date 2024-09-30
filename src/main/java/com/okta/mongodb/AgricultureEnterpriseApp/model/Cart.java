package com.okta.mongodb.AgricultureEnterpriseApp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; // Use Integer or Long depending on your needs
    private String userId; // User associated with the cart

    @ElementCollection // Use this if CartItem is not an entity
    private List<CartItem> items; // List of items in the cart
}
