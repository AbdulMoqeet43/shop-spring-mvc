//package com.code.model;
//
//import UI.Info.ItemInfo;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ManageInventory {
//
//
//
//    public static List<ItemInfo> fetchAllInventory() throws SQLException {
//
//        List<Item> allItems = DB_Inventory.fetchAllItems(DBManager.getConnection());
//        List<ItemInfo> itemInfos = new ArrayList<>();
//
//        for (Item item : allItems) {
//            itemInfos.add(new ItemInfo(
//                    item.getId(),
//                    item.getName(),
//                    item.getPrice(),
//                    item.getQuantity(),
//                    item.getDescription()
//            ));
//
//        }
//        return itemInfos;
//    }
//
//    public static void updateItem(int itemId, String name, String description, double price, int quantity) throws SQLException {
//        DB_Inventory.updateItem(itemId, name, description, price, quantity);
//    }
//
//    public static void increaseItem(int itemId, int numberOfIncrease) throws SQLException {
//
//        if (numberOfIncrease <= 0) {
//            throw new IllegalArgumentException("Number of items to increase must be greater than zero.");
//        }
//        if (itemId <= 0) {
//            throw new IllegalArgumentException("Item ID must be greater than zero.");
//        }
//
//
//        DB_Inventory.increaseItem(DBManager.getConnection(),itemId, numberOfIncrease);
//
//    }
//
//
//    public static void deleteItem(int itemId) throws SQLException {
//        if (itemId <= 0) {
//            throw new IllegalArgumentException("Item ID must be greater than zero.");
//        }
//        DB_Inventory.deleteItem(DBManager.getConnection(),itemId);
//
//    }
//
//
//    public static void decreaseItem(int itemId, int numberOfDecrease) throws SQLException {
//        if (numberOfDecrease <= 0) {
//            throw new IllegalArgumentException("Number of items to decrease must be greater than zero.");
//        }
//        if (itemId <= 0) {
//            throw new IllegalArgumentException("Item ID must be greater than zero.");
//        }
//        DB.DB_Inventory.decreaseItem(DBManager.getConnection(), itemId, numberOfDecrease);
//    }
//
//
//
//    public static void addItem(String itemName,String description,double price, int quantity) throws SQLException {
//       if(itemName.isEmpty()) {
//           throw new IllegalArgumentException("ItemName cannot be null or empty.");
//       }
//       if(description.isEmpty()) {
//           throw new IllegalArgumentException("Description cannot be null or empty.");
//       }
//       if (price <= 0) {
//           throw new IllegalArgumentException("Price must be greater than zero.");
//       }
//       if (quantity <= 0) {
//           throw new IllegalArgumentException("Quantity must be greater than zero.");
//       }
//        DB.DB_Inventory.addItem(DBManager.getConnection(), itemName,description,price,quantity);
//    }
//
//    public static void addCategory(String categoryName) throws SQLException {
//        DB_Inventory.addCategory(DBManager.getConnection(), categoryName);
//    }
//}
