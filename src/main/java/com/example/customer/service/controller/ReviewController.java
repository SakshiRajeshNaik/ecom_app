package com.example.customer.service.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.customer.service.repository.ReviewRepository;
import com.example.customer.service.model.Review;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewRepository reviewRepository;

    // Create a new review
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        log.info("Creating review for orderId: {}", review.getOrderId());
        return reviewRepository.save(review);
    }

    // Get all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        log.info("Fetching all reviews");
        return reviewRepository.findAll();
    }

    // Get reviews by customerId
    @GetMapping("/customer/{customerId}")
    public List<Review> getReviewsByCustomerId(@PathVariable String customerId) {
        log.info("Fetching reviews by customerId: {}", customerId);
        return reviewRepository.findByCustomerId(customerId);
    }

    // Get reviews by orderId
    @GetMapping("/order/{orderId}")
    public List<Review> getReviewsByOrderId(@PathVariable String orderId) {
        log.info("Fetching reviews for orderId: {}", orderId);
        return reviewRepository.findByOrderId(orderId);
    }

    // Update a review by ID
    @PutMapping("/{id}")
    public String updateReview(@PathVariable String id, @RequestBody Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);

        if (existingReview.isEmpty()) {
            log.error("Failed to update review {}", id);
            return "Review not found: " + id;
        }

        Review review = existingReview.get();
        review.setRating(updatedReview.getRating());
        review.setComment(updatedReview.getComment());

        reviewRepository.save(review);

        log.info("Review updated: {}", id);
        return "Review updated: " + id;
    }

    // Delete a review by ID
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable String id) {
        Optional<Review> foundReview = reviewRepository.findById(id);

        if (foundReview.isEmpty()) {
            log.error("Failed to delete review {}", id);
            return "Failed to delete review: " + id;
        }

        reviewRepository.deleteById(id);
        log.info("Review deleted: {}", id);
        return "Review deleted: " + id;
    }
}
