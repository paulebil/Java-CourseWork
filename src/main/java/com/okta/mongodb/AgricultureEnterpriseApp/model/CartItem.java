package com.okta.mongodb.AgricultureEnterpriseApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id; // Use Integer for auto-generated ID
    @ManyToOne // Defines the many-to-one relationship with Product
    private Product product; // Reference to the Product
    private int quantity; // Quantity of the product
}
