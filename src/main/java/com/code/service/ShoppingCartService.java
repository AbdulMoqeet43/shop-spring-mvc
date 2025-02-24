package com.code.service;

import com.code.model.*;
import com.code.repository.ItemsRepo;
import com.code.repository.ShoppingCartRepo;
import com.code.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;
    @Autowired
    private UsersRepo userRepository;

    @Autowired
    private ItemsRepo itemRepository;
    /**
     * Adds items to the shopping cart.
     * If the item already exists, updates the quantity.
     *
     * @param userId   The user's ID
     * @param itemId   The item's ID
     * @param quantity The quantity of the item
     */
    public void addItemsToShoppingCart(int userId, int itemId, int quantity) {
        validateInputs(userId, itemId, quantity);

        Optional<ShoppingCart> existingCartItem = shoppingCartRepo.findByUserIdAndItemId(userId, itemId);

        if (existingCartItem.isPresent()) {
            // Update quantity if item exists
            ShoppingCart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            shoppingCartRepo.save(cartItem);
        } else {
            // Add new item if not present
            ShoppingCart newCartItem = new ShoppingCart();
            // Fetch user and item from DB
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));
            newCartItem.setUser(user);
            newCartItem.setItem(item);
            newCartItem.setQuantity(quantity);
            shoppingCartRepo.save(newCartItem);
        }
    }

    /**
     * Deletes all items from a user's shopping cart.
     *
     * @param userId The user's ID
     */
    public void deleteAllItemsFromCart(int userId) {
        validateUserId(userId);
        shoppingCartRepo.deleteByUserId(userId);
    }

    /**
     * Fetches all items in a user's shopping cart.
     *
     * @param userId The user's ID
     * @return A list of ShoppingCartInfo objects containing item details
     */
    public List<ShoppingCartInfo> fetchAllItemsInShoppingCart(int userId) {
        validateUserId(userId);

        return shoppingCartRepo.findByUserId(userId)
                .stream()
                .map(cart -> new ShoppingCartInfo(
                        cart.getUser().getId(),
                        cart.getItem().getId(),
                        cart.getQuantity(),
                        cart.getName(),
                        cart.getPrice()))
                .collect(Collectors.toList());
    }

    // Helper methods for validation
    private void validateInputs(int userId, int itemId, int quantity) {
        if (userId <= 0) throw new IllegalArgumentException("User ID must be greater than zero.");
        if (itemId <= 0) throw new IllegalArgumentException("Item ID must be greater than zero.");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero.");
    }

    private void validateUserId(int userId) {
        if (userId <= 0) throw new IllegalArgumentException("User ID must be greater than zero.");
    }

    public List<ShoppingCart> getCartItems(int userId) {
        List<ShoppingCart> itemList = shoppingCartRepo.findByUserId(userId);
        return itemList;
    }

    public void addToCart(int userId, int itemId, int quantity) {
        
    }

    public void emptyCart(int userId) {
    }
}
