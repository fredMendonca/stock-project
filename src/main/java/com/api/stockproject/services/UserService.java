package com.api.stockproject.services;


import com.api.stockproject.models.User;
import com.api.stockproject.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a user
    public User createUser(User user) {
        logger.info("USER CREATED...");
        return userRepository.save(user);
    }

    // Read all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read a user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    // Update a user
    public User updateUser(Long userId, User updatedUser) {
        logger.info("USER UPDATED");
        User existingUser = getUserById(userId);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    // Delete a user
    public void deleteUser(Long userId) {
        logger.info("USER DELETED");
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
