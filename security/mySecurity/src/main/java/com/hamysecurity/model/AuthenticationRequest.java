package com.hamysecurity.model;

/**
 * The AuthenticationRequest class represents the request object for user authentication.
 * It contains the username and password provided by the user during login.
 */
public class AuthenticationRequest {

    private String username;
    private String password;

    /**
     * Default constructor for JSON Parsing.
     * It is required for frameworks that use reflection to create instances of this class.
     */
    public AuthenticationRequest() {}

    /**
     * Parameterized constructor to initialize the AuthenticationRequest with the given username and password.
     * 
     * @param username the username provided by the user.
     * @param password the password provided by the user.
     */
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     * 
     * @return the username provided by the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password provided by the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
