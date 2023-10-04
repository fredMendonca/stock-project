package com.api.stockproject.controllers;

import com.api.stockproject.models.User;
import com.api.stockproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a user
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        logger.info("STARTING USER CREATION...");
        return userService.createUser(user);
    }

    // Read all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Read a user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    // Update a user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        logger.info("STARTING USER UPDATE...");
        return userService.updateUser(userId, user);
    }


    // Delete a user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        logger.info("STARTING USER DELETION...");
        userService.deleteUser(userId);
    }
}