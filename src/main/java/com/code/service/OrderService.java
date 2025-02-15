package com.code.service;

import com.code.model.Item;
import com.code.model.Order;
import com.code.model.User;
import com.code.repository.ItemsRepo;
import com.code.repository.OrdersRepo;
import com.code.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepo orderRepository;

    @Autowired
    private UsersRepo userRepository;

    @Autowired
    private ItemsRepo itemRepository;

    /**
     * Processes an order with given user ID, item ID, and quantity.
     *
     * @param userId   ID of the user placing the order.
     * @param itemId   ID of the item being ordered.
     * @param quantity Number of items being ordered.
     */
    public void processOrder(int userId, int itemId, int quantity) {
        validateOrderInputs(userId, itemId, quantity);

        // Fetch user and item from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        // Create order
        Order order = new Order();
        order.setUser(user);  // Set user entity, not ID
        order.setItem(item);  // Set item entity, not ID
        order.setQuantity(quantity);
        order.setOrderDate(Timestamp.from(Instant.now()));

        orderRepository.save(order);
    }

    /**
     * Cancels an order by order ID.
     *
     * @param orderId ID of the order to be cancelled.
     * @return true if cancellation is successful.
     */
    public boolean cancelOrder(int orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be greater than zero.");
        }

        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
        }

        orderRepository.deleteById(orderId);
        return true;
    }

    /**
     * Validates order inputs before processing.
     *
     * @param userId   User ID.
     * @param itemId   Item ID.
     * @param quantity Order quantity.
     */
    private void validateOrderInputs(int userId, int itemId, int quantity) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero.");
        }
        if (itemId <= 0) {
            throw new IllegalArgumentException("Item ID must be greater than zero.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }
}
