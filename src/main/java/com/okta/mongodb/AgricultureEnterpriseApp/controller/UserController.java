package com.okta.mongodb.AgricultureEnterpriseApp.controller;


import com.okta.mongodb.AgricultureEnterpriseApp.model.User;
import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;
import com.okta.mongodb.AgricultureEnterpriseApp.service.UserService;
import com.okta.mongodb.AgricultureEnterpriseApp.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    // Register user
    @SuppressWarnings("null")
    @PostMapping("register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody User user) {
        if (userService.checkUserExists(user.getUsername()).getBody()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is taken. Please choose a different one.");
        }
        user.setRole("ROLE_NORMAL");
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    // User login
    @GetMapping("login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.checkLogin(username, password).getBody();
        if (user != null) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // //Get user profile
    // @GetMapping("profile")
    // public ResponseEntity<User> getUserProfile() {
    //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
    //     User user = userService.getUserByUsername(username).getBody();
    //     return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }

    // Get all products
    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }
    
}
