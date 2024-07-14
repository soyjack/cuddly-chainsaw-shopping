package com.hamy.tradeshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hamy.tradeshop.model.ItemPost;
import com.hamy.tradeshop.service.ItemPostService;

/**
 * The ItemPostController class handles HTTP requests related to item posts.
 * It provides endpoints for creating, retrieving, updating, and deleting item posts.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/itemposts")
public class ItemPostController {

    @Autowired
    private ItemPostService itemPostService;

    /**
     * Retrieves all item posts.
     * 
     * @return a list of all item posts.
     */
    @GetMapping("/all")
    public List<ItemPost> getItemPosts() {
        return itemPostService.getAllItemPosts();
    }

    /**
     * Creates a new item post.
     * 
     * @param itemPost the item post to create.
     * @return a response entity with the created item post.
     */
    @PostMapping("/add")
    public ResponseEntity<ItemPost> createItemPost(@RequestBody ItemPost itemPost) {
        return ResponseEntity.ok(itemPostService.createItemPost(itemPost));
    }

    /**
     * Retrieves item posts by the seller's ID.
     * 
     * @param sellerId the ID of the seller.
     * @return a list of item posts by the specified seller.
     */
    @GetMapping("/user/{sellerId}")
    public List<ItemPost> getItemPostsBySellerId(@PathVariable Long sellerId) {
        return itemPostService.getItemPostsBySellerId(sellerId);
    }

    /**
     * Retrieves an item post by its ID.
     * 
     * @param id the ID of the item post.
     * @return a response entity with the item post if found, or a not found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemPost> getItemPostById(@PathVariable Long id) {
        Optional<ItemPost> itemPost = itemPostService.getItemPostById(id);
        if (itemPost.isPresent()) {
            return ResponseEntity.ok(itemPost.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an existing item post.
     * 
     * @param id the ID of the item post to update.
     * @param itemPostDetails the new details of the item post.
     * @return a response entity with the updated item post if found, or a not found status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemPost> updateItemPost(@PathVariable Long id, @RequestBody ItemPost itemPostDetails) {
        Optional<ItemPost> itemPostOptional = itemPostService.getItemPostById(id);
        if (itemPostOptional.isPresent()) {
            ItemPost itemPost = itemPostOptional.get();
            itemPost.setImageName(itemPostDetails.getImageName());
            itemPost.setItemName(itemPostDetails.getItemName());
            itemPost.setItemDescription(itemPostDetails.getItemDescription());
            itemPost.setSeller(itemPostDetails.getSeller());
            itemPost.setPrice(itemPostDetails.getPrice());
            return ResponseEntity.ok(itemPostService.saveItemPost(itemPost));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an item post by its ID.
     * 
     * @param id the ID of the item post to delete.
     * @return a response entity with no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPost(@PathVariable Long id) {
        itemPostService.deleteItemPost(id);
        return ResponseEntity.noContent().build();
    }
}
