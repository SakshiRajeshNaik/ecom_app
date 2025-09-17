package com.example.customer.service.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews") // MongoDB collection name
public class Review {

    @Id
    private String id;
    private String customerId;   // Reference to Customer/User
    private String orderId;      // Reference to Order
    private int rating;          // e.g., 1–5 stars
    private String comment;      // Review text

    // Default constructor
    public Review() {}

    // Parameterized constructor
    public Review(String customerId, String orderId, int rating, String comment) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}