package com.hamy.tradeshop.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * The User entity represents a user in the TradeShop application.
 * It contains the user's basic information and a list of items they have for sale.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "seller")
    private List<ItemPost> itemsForSale;

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
     * @param id the ID of the user.
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
     * @param username the username of the user.
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
     * @param password the password of the user.
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
     * @param email the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of items the user has for sale.
     * 
     * @return the list of items for sale.
     */
    public List<ItemPost> getItemsForSale() {
        return itemsForSale;
    }

    /**
     * Sets the list of items the user has for sale.
     * 
     * @param itemsForSale the list of items for sale.
     */
    public void setItemsForSale(List<ItemPost> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }
}
