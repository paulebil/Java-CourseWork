package com.okta.mongodb.AgricultureEnterpriseApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okta.mongodb.AgricultureEnterpriseApp.service.ProductService;
import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    // Change the method to return a String for view rendering
    @GetMapping("products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts(); // Assuming this returns a List<Product>
        model.addAttribute("products", products); // Add products to the model
        return "products"; // Return the view name, which will resolve to /WEB-INF/views/products.jsp
    }

  
    @GetMapping("category/{category}")
    public String getProductsByCategory(@PathVariable String category, Model model) {
        ResponseEntity<List<Product>> response = productService.getProductByCategory(category);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<Product> products = response.getBody(); // Get the list of products from the response
            model.addAttribute("products", products);
            model.addAttribute("category", category);
            return "categoryProducts"; // Return the view name
        } else {
            // Handle the case where products are not found
            model.addAttribute("errorMessage", "No products found in this category");
            return "error"; // Return an error view
        }
    }

    @PostMapping("add")
    public String addProduct(@RequestBody Product product, Model model) {
        ResponseEntity<String> response = productService.addProduct(product);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return "redirect:/product/products"; // Redirect to the product list after adding
        } else {
            model.addAttribute("errorMessage", "Failed to add product");
            return "error"; // Return an error view
        }
    }

}
