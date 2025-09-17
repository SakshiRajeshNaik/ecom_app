package com.example.customer.service.controller;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import com.example.customer.service.model.Customer;
import com.example.customer.service.repository.CustomerRepository;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public String deletecustoomer(@PathVariable String id) {
    	//customerRepository.deleteById(id);
    	
    	Optional<Customer> findproduct=customerRepository.findById(id);
    	if(findproduct.isEmpty()) {
    		log.error("Failed to delete product "+id);
    		return "Failed to delete product";
    	}
    	customerRepository.deleteById(id);
    	log.info("Product Deleted "+id);
    	return "Product Deleted";
    }
}