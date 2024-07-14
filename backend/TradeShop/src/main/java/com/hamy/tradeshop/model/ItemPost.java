package com.hamy.tradeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * The ItemPost entity represents an item for sale in the TradeShop application.
 * It contains details about the item and references the seller (User).
 */
@Entity
public class ItemPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageName;
    private String itemName;
    private String itemDescription;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonIgnoreProperties("itemsForSale") // Ignore itemsForSale to prevent recursion
    private User seller;

    // Default constructor
    public ItemPost() {}

    // Getters and Setters

    /**
     * Gets the ID of the item post.
     * 
     * @return the ID of the item post.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the item post.
     * 
     * @param id the ID of the item post.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the image name of the item.
     * 
     * @return the image name of the item.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the image name of the item.
     * 
     * @param imageName the image name of the item.
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets the name of the item.
     * 
     * @return the name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the item.
     * 
     * @param itemName the name of the item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the description of the item.
     * 
     * @return the description of the item.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the description of the item.
     * 
     * @param itemDescription the description of the item.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Gets the price of the item.
     * 
     * @return the price of the item.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     * 
     * @param price the price of the item.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the seller of the item.
     * 
     * @return the seller of the item.
     */
    public User getSeller() {
        return seller;
    }

    /**
     * Sets the seller of the item.
     * 
     * @param seller the seller of the item.
     */
    public void setSeller(User seller) {
        this.seller = seller;
    }
}
