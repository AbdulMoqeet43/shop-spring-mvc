package com.code.repository;

import com.code.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Order, Integer> {

    // Fetch order by ID
    Optional<Order> findById(Integer orderId);

    // Delete an order by ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.id = :orderId")
    void deleteById(Integer orderId);

    // Fetch all orders with user and item details
    @Query("SELECT o FROM Order o JOIN FETCH o.item i JOIN FETCH o.user u LEFT JOIN FETCH u.shoppingCart sc")
    List<Order> getAllOrders();
}
