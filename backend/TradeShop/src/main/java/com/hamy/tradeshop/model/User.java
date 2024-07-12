package com.hamy.tradeshop.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "seller")
    private List<ItemPost> itemsForSale;

    @OneToMany(mappedBy = "buyer")
    private List<ItemPost> itemsPurchased;

    public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ItemPost> getItemsForSale() {
		return itemsForSale;
	}

	public void setItemsForSale(List<ItemPost> itemsForSale) {
		this.itemsForSale = itemsForSale;
	}

	public List<ItemPost> getItemsPurchased() {
		return itemsPurchased;
	}

	public void setItemsPurchased(List<ItemPost> itemsPurchased) {
		this.itemsPurchased = itemsPurchased;
	}

    
}
