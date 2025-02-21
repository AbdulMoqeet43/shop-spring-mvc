//package com.code.model;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class ManageOrders {
//
//    public static List<OrderInfo> fetchAllOrders() throws SQLException {
//
////        List<Order> orders = DB.DB_Orders.getAllOrders(DBManager.getConnection());
////        List<OrderInfo> orderInfos = new ArrayList<>();
//
//        if (!orders.isEmpty()) {
//
//
//            for (Order order : orders) {
//                orderInfos.add(new OrderInfo(
//                        order.getId(),
//                        order.getUserId(),
//                        order.getUsername(),
//                        order.getItemId(),
//                        order.getItemName(),
//                        order.getPrice(),
//                        order.getDescription(),
//                        order.getQuantity(),
//                        order.getOrderQuantity(),
//                        order.getCartQuantity(),
//                        order.getOrderDate()
//                ));
//
//            }
//        }
//
//        return orderInfos;
//    }
//}
