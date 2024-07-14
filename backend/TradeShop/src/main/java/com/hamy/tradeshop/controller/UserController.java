package com.hamy.tradeshop.controller;

import com.hamy.tradeshop.model.User;
import com.hamy.tradeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The UserController class handles HTTP requests related to user operations.
 * It provides endpoints for user registration and synchronization.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user and synchronizes with TradeShop.
     * 
     * @param user the user to register.
     * @return a response entity with a success message.
     */
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully and synchronized with TradeShop");
    }

    /**
     * Synchronizes user information with TradeShop.
     * 
     * @param user the user to synchronize.
     * @return a response entity with the synchronized user.
     */
    @PostMapping("/sync")
    public ResponseEntity<User> syncUser(@RequestBody User user) {
        User syncedUser = userService.syncUser(user);
        return ResponseEntity.ok(syncedUser);
    }
}
