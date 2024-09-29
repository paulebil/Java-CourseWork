
package com.okta.mongodb.AgricultureEnterpriseApp.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;
import com.okta.mongodb.AgricultureEnterpriseApp.dao.ProductDao;

@Service
public class ProductService {
    
    @Autowired
    ProductDao productDao;

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
}
