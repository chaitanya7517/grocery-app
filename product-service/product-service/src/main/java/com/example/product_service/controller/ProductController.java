package com.example.product_service.controller;

import com.example.product_service.clients.UserClient;
import com.example.product_service.entities.Product;
import com.example.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserClient userClient;

    @GetMapping
    public ResponseEntity<?> getAllProduct(@RequestHeader("apiKey") String apiKey) {
        boolean isAuthenticated = userClient.authenticate(apiKey);
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied: unautharized");
        }
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Boolean saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/test")
    public String test() {
        return "Jay Mahakal!!";
    }
}
