package com.example.customer.service.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.customer.service.repository.OrderRepository;
import com.example.customer.service.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    // Create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setCreatedAt(LocalDateTime.now()); // Auto-set order creation time
        log.info("Creating new order for userId: {}", order.getUserId());
        return orderRepository.save(order);
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        log.info("Fetching all orders");
        return orderRepository.findAll();
    }

    // Get all orders by userId
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable String userId) {
        log.info("Fetching orders for userId: {}", userId);
        return orderRepository.findByUserId(userId);
    }

    // Get single order by ID
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable String id) {
        log.info("Fetching order with ID: {}", id);
        return orderRepository.findById(id);
    }

    // Update an order
    @PutMapping("/{id}")
    public String updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isEmpty()) {
            log.error("Failed to update order {}", id);
            return "Order not found: " + id;
        }

        Order order = existingOrder.get();
        order.setItems(updatedOrder.getItems());
        order.setTotalAmount(updatedOrder.getTotalAmount());
        order.setStatus(updatedOrder.getStatus());

        orderRepository.save(order);

        log.info("Order updated: {}", id);
        return "Order updated: " + id;
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable String id) {
        Optional<Order> foundOrder = orderRepository.findById(id);

        if (foundOrder.isEmpty()) {
            log.error("Failed to delete order {}", id);
            return "Failed to delete order: " + id;
        }

        orderRepository.deleteById(id);
        log.info("Order deleted: {}", id);
        return "Order deleted: " + id;
    }
}