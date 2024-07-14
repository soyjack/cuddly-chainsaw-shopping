package com.hamy.tradeshop.controller;

import com.hamy.tradeshop.model.User;
import com.hamy.tradeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully and synchronized with TradeShop");
    }
    
    @PostMapping("/sync")
    public ResponseEntity<User> syncUser(@RequestBody User user) {
        User syncedUser = userService.syncUser(user);
        return ResponseEntity.ok(syncedUser);
    }
}
