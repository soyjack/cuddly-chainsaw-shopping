package com.hamy.tradeshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hamy.tradeshop.model.ItemPost;
import com.hamy.tradeshop.model.User;
import com.hamy.tradeshop.repository.ItemPostRepository;
import com.hamy.tradeshop.repository.UserRepository;

/**
 * The ItemPostService class provides business logic for item post operations.
 * It interacts with the ItemPostRepository and UserRepository to perform CRUD operations on item posts.
 */
@Service
public class ItemPostService {

    @Autowired
    private ItemPostRepository itemPostRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all item posts.
     * 
     * @return a list of all item posts.
     */
    public List<ItemPost> getAllItemPosts() {
        return (List<ItemPost>) itemPostRepository.findAll();
    }

    /**
     * Retrieves an item post by its ID.
     * 
     * @param id the ID of the item post.
     * @return an Optional containing the item post if found, or empty if not found.
     */
    public Optional<ItemPost> getItemPostById(Long id) {
        return itemPostRepository.findById(id);
    }

    /**
     * Saves an item post.
     * 
     * @param itemPost the item post to save.
     * @return the saved item post.
     */
    public ItemPost saveItemPost(ItemPost itemPost) {
        return itemPostRepository.save(itemPost);
    }

    /**
     * Deletes an item post by its ID.
     * 
     * @param id the ID of the item post to delete.
     */
    public void deleteItemPost(Long id) {
        itemPostRepository.deleteById(id);
    }

    /**
     * Retrieves item posts by the seller's ID.
     * 
     * @param sellerId the ID of the seller.
     * @return a list of item posts by the specified seller.
     */
    public List<ItemPost> getItemPostsBySellerId(Long sellerId) {
        return itemPostRepository.findBySellerId(sellerId);
    }

    /**
     * Creates a new item post.
     * 
     * @param itemPostDetails the details of the item post to create.
     * @return the created item post.
     */
    public ItemPost createItemPost(ItemPost itemPostDetails) {
        Long userId = itemPostDetails.getSeller().getId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            itemPostDetails.setSeller(userOptional.get());
            return itemPostRepository.save(itemPostDetails);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
