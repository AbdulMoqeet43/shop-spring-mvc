package com.code.repository;

import com.code.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i")
    List<Item> fetchAllItems();

    @Modifying
    @Query("UPDATE Item i SET i.quantity = i.quantity + ?2 WHERE i.id = ?1")
    void increaseItem(int itemId, int numberOfIncrease);

    @Modifying
    @Query("UPDATE Item i SET i.quantity = i.quantity - ?2 WHERE i.id = ?1")
    void decreaseItem(int itemId, int numberOfDecrease);

    @Modifying
    @Query("INSERT INTO Category (name) VALUES (?1)")
    void addCategory(String categoryName);
}
