package com.example.customer.service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.customer.service.model.Review;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    // Find all reviews by a customer
    List<Review> findByCustomerId(String customerId);

    // Find all reviews for a specific order
    List<Review> findByOrderId(String orderId);

    // Find reviews by rating
    List<Review> findByRating(int rating);
}