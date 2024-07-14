package com.hamysecurity.controller;

import com.hamysecurity.dto.SignInRequest;
import com.hamysecurity.dto.SignUpRequest;
import com.hamysecurity.model.AuthenticationResponse;
import com.hamysecurity.service.UserService;
import com.hamysecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * The AuthController class handles authentication requests such as sign-in and sign-up.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**
     * Handles sign-in requests.
     * 
     * @param signInRequest the sign-in request containing username and password.
     * @return the authentication response containing the JWT token.
     * @throws Exception if the authentication fails.
     */
    @PostMapping("/signin")
    public AuthenticationResponse createAuthenticationToken(@RequestBody SignInRequest signInRequest) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Load user details and generate JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequest.getUsername());
        final Long userId = userService.getUserIdByUsername(signInRequest.getUsername()); // Get user ID

        final String jwt = jwtUtil.generateToken(userDetails, userId); // Pass user ID to generateToken

        return new AuthenticationResponse(jwt);
    }

    /**
     * Handles sign-up requests.
     * 
     * @param signUpRequest the sign-up request containing user details.
     */
    @PostMapping("/signup")
    public void signup(@RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
    }
}
