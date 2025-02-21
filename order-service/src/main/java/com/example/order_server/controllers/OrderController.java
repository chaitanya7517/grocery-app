package com.example.order_server.controllers;

import com.example.order_server.clients.ProductServiceClient;
import com.example.order_server.entities.Order;
import com.example.order_server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    ProductServiceClient productServiceClient;

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Boolean saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/order/test")
    public String getResponse() {
        return "Jay mahakal!!";
    }

    /* only for testing
        @GetMapping("/product/test")
        String getResponse() {
            return productServiceClient.test();
        }
     */
}
