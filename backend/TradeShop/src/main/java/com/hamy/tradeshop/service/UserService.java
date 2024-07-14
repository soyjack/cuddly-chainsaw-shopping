package com.hamy.tradeshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hamy.tradeshop.model.User;
import com.hamy.tradeshop.repository.UserRepository;

/**
 * The UserService class provides business logic for user operations.
 * It interacts with the UserRepository to perform CRUD operations on users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users.
     * 
     * @return a list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param id the ID of the user.
     * @return an Optional containing the user if found, or empty if not found.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Saves a user.
     * 
     * @param user the user to save.
     * @return the saved user.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param id the ID of the user to delete.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Synchronizes a user by updating the existing user or creating a new user.
     * 
     * @param user the user to synchronize.
     * @return the synchronized user.
     */
    public User syncUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Update existing user
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } else {
            // Create new user
            return userRepository.save(user);
        }
    }
}
