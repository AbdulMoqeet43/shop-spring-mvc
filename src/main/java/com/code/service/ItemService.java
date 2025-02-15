package com.code.service;

import com.code.model.Item;
import com.code.model.ItemInfo;
import com.code.repository.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemsRepo itemRepository;

    /**
     * Get one item by ID and map it to DTO 'ItemInfo'.
     *
     * @param itemId the ID of the item
     * @return an ItemInfo object
     */
    public ItemInfo getOneItemById(int itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        return mapToItemInfo(item);
    }

    /**
     * Fetch all available items.
     *
     * @return a List of ItemInfo
     */
    public List<ItemInfo> getAllItems() {
        return itemRepository.findAll().stream()
                .map(this::mapToItemInfo)
                .collect(Collectors.toList());
    }

    /**
     * Decrease the quantity of an item.
     *
     * @param itemId   the ID of the item
     * @param quantity the quantity to decrease
     */
    public void decreaseNumberOfItem(int itemId, int quantity) {
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        if (item.getQuantity() < quantity) {
            throw new IllegalStateException("Insufficient stock for item ID: " + itemId);
        }

        item.setQuantity(item.getQuantity() - quantity);
        itemRepository.save(item);
    }

    /**
     * Convert an Item entity to an ItemInfo DTO.
     *
     * @param item the Item entity
     * @return the ItemInfo DTO
     */
    private ItemInfo mapToItemInfo(Item item) {
        return new ItemInfo(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getQuantity(),
                item.getDescription()
        );
    }
}
