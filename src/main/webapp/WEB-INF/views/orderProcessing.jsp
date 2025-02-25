<%@ page import="java.util.List" %>
<%@ page import="com.code.model.OrderInfo" %>
<%@ page import="com.code.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Warehouse Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f8f9fa;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #007bff;
            text-align: center;
        }

        .welcome {
            text-align: center;
            font-size: 18px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e9ecef;
        }

        .action-btn {
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: white;
            text-decoration: none;
        }

        .edit-btn {
            background-color: #ffc107;
        }

        .edit-btn:hover {
            background-color: #e0a800;
        }

        .logout-btn {
            float: right;
            padding: 8px 12px;
            background-color: #dc3545;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: white;
            text-decoration: none;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">
    <form action="/orders" method="post">
        <button type="submit" name="action" value="Logout" class="logout-btn">Logout</button>
    </form>

    <h1>Warehouse Staff Dashboard</h1>

    <% User user = (User) session.getAttribute("user"); %>
    <p class="welcome">Welcome, <%= user.getUsername() %>!</p>

    <%
        List<OrderInfo> orderInfos = (List<OrderInfo>) request.getAttribute("orders");
        if (orderInfos != null && !orderInfos.isEmpty()) {
    %>

    <h2>All Orders</h2>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Customer Name</th>
            <th>Item</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Ordered</th>
            <th>Order Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (OrderInfo order : orderInfos) { %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getUsername() %></td>
            <td><%= order.getItemName() %></td>
            <td>$<%= String.format("%.2f", order.getPrice()) %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getOrderQuantity() %></td>
            <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getOrderDate()) %></td>
            <td>
                <form action="/orders" method="post" style="display: inline;">
                    <input type="hidden" name="orderID" value="<%= order.getId() %>">
                    <button type="submit" name="action" value="EditOrder" class="action-btn edit-btn">Edit</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <% } else { %>
    <p style="text-align: center; font-size: 16px; color: #555;">No orders available in stock.</p>
    <% } %>

</div>

</body>
</html>
