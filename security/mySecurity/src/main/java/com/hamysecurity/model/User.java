package com.hamysecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * The User class represents a user in the system.
 * It maps to the 'user' table in the database and contains user details such as username, password, and email.
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"itemsForSale"}) // Ignore itemsForSale to prevent recursion
public class User {

    // Primary key for the user entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username of the user, must be unique and not null
    @Column(unique = true, nullable = false)
    private String username;

    // Password of the user, must not be null
    @Column(nullable = false)
    private String password;

    // Email of the user, must be unique and not null
    @Column(unique = true, nullable = false)
    private String email;

    // Default constructor
    public User() {}

    // Getters and Setters

    /**
     * Gets the ID of the user.
     * 
     * @return the ID of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param id the ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the user.
     * 
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email the email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
