package com.code.service;

import com.code.model.OrderInfo;
import com.code.model.Order;
import com.code.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageOrdersService {

    @Autowired
    private OrdersRepo orderRepository;

    public List<OrderInfo> fetchAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> new OrderInfo(
                order.getId(),
                order.getUser().getId(),
                order.getUser().getUsername(),
                order.getItem().getId(),
                order.getItem().getName(),
                order.getItem().getPrice(),
                order.getItem().getDescription(),
                order.getQuantity(),
//                order.getOrderQuantity(),
//                order.getCartQuantity(),
                0, 0,
                order.getOrderDate()
        )).collect(Collectors.toList());
    }
}
