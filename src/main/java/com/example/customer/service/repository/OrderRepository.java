package com.example.customer.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.customer.service.model.Order;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId); // Get all orders for a specific user
    List<Order> findByStatus(String status); // Get orders by status
}