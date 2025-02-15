package com.code.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shoppingcart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int quantity;
    private String name;
    private double price;
}
