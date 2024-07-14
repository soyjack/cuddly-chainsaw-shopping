package com.hamysecurity.service;

import com.hamysecurity.dto.SignUpRequest;
import com.hamysecurity.model.User;
import com.hamysecurity.repository.UserRepository;
import com.hamysecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The UserServiceImpl class implements the UserService interface to provide user-related services
 * such as registration, updating, deleting, and synchronizing users with TradeShop.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Gets the user ID by username.
     * 
     * @param username the username of the user.
     * @return the user ID.
     */
    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getId();
    }

    /**
     * Registers a new user.
     * 
     * @param signUpRequest the sign-up request containing user details.
     */
    @Override
    public void signup(SignUpRequest signUpRequest) {
        // Save user in mySecurity database
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());

        userRepository.save(user);

        // Load UserDetails for the new user
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        // Generate JWT token for the new user
        String token = jwtUtil.generateToken(userDetails, user.getId());

        // Synchronize with TradeShop
        synchronizeWithTradeShop(user, token);
    }

    /**
     * Updates a user's details.
     * 
     * @param id the ID of the user to update.
     * @param userDetails the new details of the user.
     * @return the updated user or null if not found.
     */
    @Override
    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setEmail(userDetails.getEmail());
            User updatedUser = userRepository.save(user);

            // Load UserDetails for the updated user
            UserDetails userDetailsFromDb = userDetailsService.loadUserByUsername(user.getUsername());

            // Generate JWT token for the updated user
            String token = jwtUtil.generateToken(userDetailsFromDb, user.getId());

            // Synchronize with TradeShop
            synchronizeWithTradeShop(updatedUser, token);

            return updatedUser;
        } else {
            return null;
        }
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param id the ID of the user to delete.
     * @return true if the user was deleted, false otherwise.
     */
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Synchronizes the user data with the TradeShop application.
     * 
     * @param user the user to synchronize.
     * @param token the JWT token for authentication.
     */
    private void synchronizeWithTradeShop(User user, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String tradeShopUrl = "http://localhost:8080/api/users/sync";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Content-Type", "application/json");

            HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
            logger.info("Sending request to TradeShop: {}", requestEntity);
            restTemplate.postForEntity(tradeShopUrl, requestEntity, String.class);
            logger.info("User synchronized with TradeShop successfully.");
        } catch (Exception e) {
            // Handle the error (e.g., log it)
            logger.error("Failed to synchronize user with TradeShop.", e);
        }
    }

    /**
     * Gets a user by their ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return the user or null if not found.
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
