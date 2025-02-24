package com.code.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderInfo {
    private int id;
    private int userId;
    private String username;
    private int itemId;
    private String itemName;

    private double price;

    private String description;
    private int quantity;
    private int orderQuantity;

    private int cartQuantity; // New field for shopping cart quantity

    private Timestamp orderDate;

public OrderInfo(int id,int userId, int itemId) {
    this.id = id;
    this.userId = userId;
    this.itemId = itemId;


}

    public OrderInfo(int id, int userId, String username, int itemId, String itemName, double price,
                     String description, int quantity, int orderQuantity, int cartQuantity, Timestamp orderDate) {

        this.id = id;
        this.userId = userId;
        this.username = username;
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.orderQuantity = orderQuantity;
        this.cartQuantity = cartQuantity; // Initialize the cart quantity
        this.orderDate = orderDate;
    }


    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", username='" + username + '\'' +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
