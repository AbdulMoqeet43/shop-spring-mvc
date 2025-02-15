package com.code.repository;

import com.code.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepo extends JpaRepository<Item, Integer> {

    /**
     * Fetch a single item by ID.
     */
    Optional<Item> findById(Integer id);

    /**
     * Fetch all available items (quantity > 0).
     */
    List<Item> findByQuantityGreaterThan(int quantity);

    /**
     * Decrease quantity of an item if enough stock is available.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Item i SET i.quantity = i.quantity - ?2 WHERE i.id = ?1 AND i.quantity >= ?2")
    int decreaseQuantityIfAvailable(Integer id, int quantity);
}
