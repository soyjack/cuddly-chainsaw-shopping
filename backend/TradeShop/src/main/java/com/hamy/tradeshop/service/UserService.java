package com.hamy.tradeshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hamy.tradeshop.model.User;
import com.hamy.tradeshop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public User syncUser(User user) {
    	System.out.println("Got into sync user method");
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Update existing user
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } else {
            // Create new user
        	System.out.println("Creating new user");
            return userRepository.save(user);
        }
    }
}
