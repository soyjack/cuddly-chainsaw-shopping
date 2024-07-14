package com.hamysecurity.model;

/**
 * The AuthenticationResponse class represents the response object for user authentication.
 * It contains the JWT token generated upon successful authentication.
 */
public class AuthenticationResponse {

    // The JWT token
    private final String jwt;

    /**
     * Constructor to initialize the AuthenticationResponse with the given JWT token.
     * 
     * @param jwt the JWT token generated upon successful authentication.
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Gets the JWT token.
     * 
     * @return the JWT token.
     */
    public String getJwt() {
        return jwt;
    }
}
