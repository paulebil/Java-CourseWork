// package com.okta.mongodb.AgricultureEnterpriseApp.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// //import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// //import org.springframework.web.bind.annotation.RestController;

// import com.okta.mongodb.AgricultureEnterpriseApp.service.ProductService;
// import com.okta.mongodb.AgricultureEnterpriseApp.model.Product;

// import java.util.List;

// @Controller
// @RequestMapping("product")
// public class AgricultureAppController {

//     @Autowired
//     ProductService productService;

//     // @SuppressWarnings("unchecked")
//     // @GetMapping("allProducts")
//     // public ResponseEntity <List<Product>> getAllProducts(){
//     //     return (ResponseEntity<List<Product>>) productService.getAllProducts();
//     // }

//     // @GetMapping("products")
//     // public String getAllProducts(Model model) {
//     //     List<Product> products = productService.getAllProducts(); // Assuming this returns a List<Product>
//     //     model.addAttribute("products", products); // Add products to the model
//     //     return "products"; // Return the view name, which will resolve to /WEB-INF/views/products.jsp
//     // }
    

//     @GetMapping("products")
//     public ResponseEntity<List<Product>> getAllProducts(){
//         List<Product> products = productService.getAllProducts(); // Assuming this returns a List<Product>
//         return ResponseEntity.ok(products); // Wrap the list in a ResponseEntity
//     }

//     @GetMapping("category/{category}")
//     public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
//         return productService.getProductByCategory(category);
//     }

//     @PostMapping("add")
//     public ResponseEntity<String>addProduct(@RequestBody Product product){
//         return productService.addProduct(product);
//     }
// }



package com.okta.mongodb.AgricultureEnterpriseApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Import Model
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
public class AgricultureAppController {

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
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
