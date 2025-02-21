package com.example.order_server.services;

import com.example.order_server.clients.ProductServiceClient;
import com.example.order_server.entities.Order;
import com.example.order_server.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ProductServiceClient productServiceClient;

    public boolean saveOrder(Order order) {
        List<Long> productIds = order.getProductIds();
        for(Long productId : productIds) {
            var product = productServiceClient.getProductById(productId);
            if(product == null) {
                return false;
            }
        }
        orderRepo.save(order);
        return true;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

}
