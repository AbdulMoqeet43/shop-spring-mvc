<%@ page import="java.util.List" %>
<%@ page import="UI.Info.OrderInfo" %>
<%@ page import="BO.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }

        h1, h2 {
            color: #007bff;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .basket-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        button {
            padding: 5px 10px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            color: white;
        }

        button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="basket-button">
    <form action="OrderProcessingServlet" method="post">
        <input type="submit" name="action" value="Logout">
    </form>
</div>

<%
    User user = (User) session.getAttribute("user");

%>

<h1>Warehouse Staff Dashboard</h1>
<p>Welcome, <%= user.getUsername() %>!</p>

<%
    List<OrderInfo> orderInfos = (List<OrderInfo>) request.getAttribute("orders");
    if (orderInfos != null && !orderInfos.isEmpty()) {
%>

<h2>All Orders</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name of bought</th>
        <th>Item name</th>
        <th>Price</th>
        <th>Stock Quantity</th>
        <th>Order Quantity</th>
        <th>Date of bought</th>
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


            <form action="OrderProcessingServlet" method="post" style="display: inline;">
                <input type="hidden" name="orderID" value="<%= order.getId() %>">
                <button type="submit" name="action" value="Edit" style="background-color: #ffc107;">Edit</button>
            </form>



        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No orders available in the Stock</p>
<% } %>
</body>
</html>
