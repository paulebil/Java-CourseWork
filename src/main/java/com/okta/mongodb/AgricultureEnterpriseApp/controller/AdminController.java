// package com.okta.mongodb.AgricultureEnterpriseApp.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;
// import com.okta.mongodb.AgricultureEnterpriseApp.model.User;
// import com.okta.mongodb.AgricultureEnterpriseApp.service.ProductService;
// import com.okta.mongodb.AgricultureEnterpriseApp.service.UserService;

// import java.util.List;

// @RestController
// @RequestMapping("/admin")
// public class AdminController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private ProductService productService;

//     // @GetMapping("/index")
//     // public ResponseEntity<String> index() {
//     //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
//     //     return ResponseEntity.ok("Welcome, " + username);
//     // }

//     @GetMapping("/products")
//     public ResponseEntity<List<Product>> getProducts() {
//         List<Product> products = productService.getAllProducts();
//         return ResponseEntity.ok(products);
//     }

//     @PostMapping("/products/add")
//     public ResponseEntity<String> addProduct(@RequestBody Product product) {
//         return productService.addProduct(product);
//     }

//     @GetMapping("/products/update/{id}")
//         public ResponseEntity<Product> updateProduct(@PathVariable int id) {
//         ResponseEntity<Product> responseEntity = productService.getProductById(id);
        
//         // Check if the product was found
//         if (responseEntity.getStatusCode() == HttpStatus.OK) {
//             return ResponseEntity.ok(responseEntity.getBody());
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }


//     @PostMapping("/products/update/{id}")
//     public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
//         product.setId(id); // Make sure to set the ID for the product
//         return productService.addProduct(product); // Assuming this will update if ID exists
//     }

//     @GetMapping("/products/delete/{id}")
//     public ResponseEntity<String> removeProduct(@PathVariable int id) {
//         productService.deleteProduct(id);
//         return ResponseEntity.ok("Product deleted successfully");
//     }

//     @GetMapping("/customers")
//     public ResponseEntity<List<User>> getCustomerDetail() {
//         List<User> users = userService.getUsers().getBody();
//         return ResponseEntity.ok(users);
//     }

//     // @GetMapping("/profileDisplay")
//     // public ResponseEntity<User> profileDisplay() {
//     //     String username = SecurityContextHolder.getContext().getAuthentication().getName();
//     //     User user = userService.getUserByUsername(username).getBody();
//     //     return ResponseEntity.ok(user);
//     // }

//     @PostMapping("/updateuser")
//     public ResponseEntity<String> updateUserProfile(@RequestBody User user) {
//         userService.addUser(user); // Assuming this will handle both adding and updating
//         return ResponseEntity.ok("User profile updated successfully");
//     }
// }

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
