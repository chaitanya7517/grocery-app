package com.example.order_server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/product/test")
    String test();

    @GetMapping("/product/{id}")
    Object getProductById(@PathVariable Long id);
}
