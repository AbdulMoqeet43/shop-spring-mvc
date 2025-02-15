package com.code.repository;

import com.code.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Integer> {

    /**
     * Finds all shopping cart items for a given user.
     *
     * @param userId The user's ID.
     * @return A list of ShoppingCart items.
     */
    List<ShoppingCart> findByUserId(int userId);

    /**
     * Finds a shopping cart item for a specific user and item.
     *
     * @param userId The user's ID.
     * @param itemId The item's ID.
     * @return An Optional containing the ShoppingCart item if found.
     */
    Optional<ShoppingCart> findByUserIdAndItemId(int userId, int itemId);

    /**
     * Deletes all shopping cart items for a given user.
     *
     * @param userId The user's ID.
     */
    void deleteByUserId(int userId);

    /**
     * Checks if an item already exists in the user's shopping cart.
     *
     * @param userId The user's ID.
     * @param itemId The item's ID.
     * @return True if the item exists, otherwise false.
     */
    boolean existsByUserIdAndItemId(int userId, int itemId);
}
