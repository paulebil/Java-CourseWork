package com.okta.mongodb.AgricultureEnterpriseApp.controller;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;
import com.okta.mongodb.AgricultureEnterpriseApp.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get a product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseEntity<Product>> getProductById(@PathVariable int id) {
        ResponseEntity<Product> product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new product
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Update an existing product
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        // Implement update logic in ProductService
        // For example, you might have a method in ProductService like `updateProduct(id, product)`
        // Make sure to handle cases where the product ID doesn't exist
        return ResponseEntity.ok("Product updated successfully");
    }

    // Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
