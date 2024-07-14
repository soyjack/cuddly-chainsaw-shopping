package com.hamysecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hamysecurity.dto.SignUpRequest;
import com.hamysecurity.model.User;
import com.hamysecurity.service.UserService;

/**
 * The UserController class handles user-related operations such as registration, retrieval, updating, and deletion.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * 
     * @param signUpRequest the sign-up request containing user details.
     * @return a ResponseEntity with a success message.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
        return ResponseEntity.ok("User registered successfully and synchronized with TradeShop");
    }

    /**
     * Retrieves a user by their ID.
     * 
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the user or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates a user's details.
     * 
     * @param id the ID of the user to update.
     * @param userDetails the new details of the user.
     * @return a ResponseEntity containing the updated user or a 404 status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param id the ID of the user to delete.
     * @return a ResponseEntity with no content status or a 404 status if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
