package com.example.customer.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.customer.service.model.Product;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Find products by category
    List<Product> findByCategory(String category);

    // Find products containing a specific tag
    List<Product> findByTagsContaining(String tag);

    // Find products by name
    List<Product> findByName(String name);
}