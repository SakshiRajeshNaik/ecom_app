package com.example.customer.service.controller;

import com.example.customer.service.model.Customer;
import com.example.customer.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by id
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    // Add a new customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Update an existing customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhone(customerDetails.getPhone());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    // Delete customer by id
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerRepository.deleteById(id);
    }
}
