package com.example.customer.service.controller;

import com.example.customer.service.model.Product;
import com.example.customer.service.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product.getName());
        return productRepository.save(product);
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        log.info("Fetching product with ID: {}", id);
        return productRepository.findById(id);
    }

    // Get products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        log.info("Fetching products in category: {}", category);
        return productRepository.findByCategory(category);
    }

    // Get products by tag
    @GetMapping("/tag/{tag}")
    public List<Product> getProductsByTag(@PathVariable String tag) {
        log.info("Fetching products with tag: {}", tag);
        return productRepository.findByTagsContaining(tag);
    }

    // Update product by ID
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            log.error("Product not found: {}", id);
            return "Product not found: " + id;
        }

        Product product = existingProduct.get();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setTags(updatedProduct.getTags()); // ✅ update tags also

        productRepository.save(product);
        log.info("Product updated: {}", id);
        return "Product updated: " + id;
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            log.error("Failed to delete product {}", id);
            return "Product not found: " + id;
        }

        productRepository.deleteById(id);
        log.info("Product deleted: {}", id);
        return "Product deleted: " + id;
    }
}