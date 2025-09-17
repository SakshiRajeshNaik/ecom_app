package com.example.customer.service.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.customer.service.repository.UserRepository;
import com.example.customer.service.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("Creating new user: {}", user.getName());
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // Update user by ID
    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            log.error("Failed to update user {}", id);
            return "User not found: " + id;
        }

        User user = existingUser.get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());

        userRepository.save(user);

        log.info("User updated: {}", id);
        return "User updated: " + id;
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        Optional<User> foundUser = userRepository.findById(id);

        if (foundUser.isEmpty()) {
            log.error("Failed to delete user {}", id);
            return "Failed to delete user: " + id;
        }

        userRepository.deleteById(id);
        log.info("User deleted: {}", id);
        return "User deleted: " + id;
    }
}