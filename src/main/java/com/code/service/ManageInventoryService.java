package com.code.service;

import com.code.model.Item;
import com.code.model.ItemInfo;
import com.code.repository.InventoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManageInventoryService {

    @Autowired
    private InventoryRepo inventoryRepository;

    public List<ItemInfo> fetchAllInventory() {
        List<Item> allItems = inventoryRepository.fetchAllItems();
        return allItems.stream()
                .map(item -> new ItemInfo(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getQuantity(),
                        item.getDescription()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateItem(int itemId, String name, String description, double price, int quantity) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }

        Optional<Item> existingItem = inventoryRepository.findById(itemId);
        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setQuantity(quantity);
            inventoryRepository.save(item);
        } else {
            throw new IllegalArgumentException("Item with ID " + itemId + " not found.");
        }
    }

    @Transactional
    public void increaseItem(int itemId, int numberOfIncrease) {
        if (itemId <= 0 || numberOfIncrease <= 0) {
            throw new IllegalArgumentException("Item ID and increase amount must be greater than zero.");
        }
        inventoryRepository.increaseItem(itemId, numberOfIncrease);
    }

    @Transactional
    public void decreaseItem(int itemId, int numberOfDecrease) {
        if (itemId <= 0 || numberOfDecrease <= 0) {
            throw new IllegalArgumentException("Item ID and decrease amount must be greater than zero.");
        }
        inventoryRepository.decreaseItem(itemId, numberOfDecrease);
    }

    @Transactional
    public void deleteItem(int itemId) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }
        inventoryRepository.deleteById(itemId);
    }

    @Transactional
    public void addItem(String itemName, String description, double price, int quantity) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Item newItem = new Item();
        newItem.setName(itemName);
        newItem.setDescription(description);
        newItem.setPrice(price);
        newItem.setQuantity(quantity);
        inventoryRepository.save(newItem);
    }

    @Transactional
    public void addCategory(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }
//        inventoryRepository.addCategory(categoryName);
    }
}
