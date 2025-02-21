package com.example.product_service.services;

import com.example.product_service.entities.Product;
import com.example.product_service.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Boolean saveProduct(Product product) {
        try {
            List<Product> products = productRepo.findByName(product.getName());
            if(products.isEmpty()) {
                productRepo.save(product);
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
    }
}
