package com.example.customer.service.repository;

import com.example.customer.service.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Example custom query (optional)
    Customer findByEmail(String email);
}
