package com.example.product_service.repos;

import com.example.product_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    List<Product> findByName(String name);

}
