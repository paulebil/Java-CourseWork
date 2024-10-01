
package com.okta.mongodb.AgricultureEnterpriseApp.service;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;
import com.okta.mongodb.AgricultureEnterpriseApp.dao.ProductDao;
import org.springframework.data.domain.Pageable;


@Service
public class ProductService {
    
    @Autowired
    ProductDao productDao;

    public List<Product> getRandomProducts(String category, int numQ) {
        Pageable pageable = PageRequest.of(0, numQ); // Page 0, size numQ
        return productDao.findRandomProductsByCategory(category, pageable);
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        try {
            return productDao.findAll(); 
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ArrayList<>(); 
        }
    }

    // Retrieve products by category
    public ResponseEntity<List<Product>> getProductByCategory(String category) {
        try {
            return new ResponseEntity<>(productDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Add a new product
    public ResponseEntity<String> addProduct(Product product) {
        productDao.save(product); 
        return new ResponseEntity<>("success", HttpStatus.CREATED); 
    }

    
    // Retrieve a product by its ID
    @SuppressWarnings("null")
    public ResponseEntity<Product> getProductById(int id) {
        try {
            Product product = productDao.findById(id).orElse(null);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add this method to ProductService
    public ResponseEntity<String> updateProduct(int id, Product product) {
        if (!productDao.existsById(id)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        product.setId(id); // Assuming you have a setter for the id in the Product model
        productDao.save(product); // This will update the existing product
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    // Add this method for deleting a product
    public boolean deleteProduct(int id) {
        if (!productDao.existsById(id)) {
            return false;
        }
        productDao.deleteById(id);
        return true;
    }

}
