<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.code.model.ItemInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #333;
        }

        .basket-button {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-bottom: 20px;
        }

        .basket-button input {
            padding: 10px 15px;
            border: none;
            background: #007bff;
            color: white;
            font-size: 14px;
            cursor: pointer;
            border-radius: 5px;
        }

        .basket-button input:hover {
            background: #0056b3;
        }

        .item-details {
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            background: #fff;
        }

        .item-details p {
            font-size: 16px;
            margin: 8px 0;
        }

        .item-details span {
            font-weight: bold;
            color: red;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin-top: 20px;
        }

        label {
            font-size: 16px;
            font-weight: bold;
        }

        input[type="number"] {
            padding: 8px;
            width: 100%;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            padding: 10px 15px;
            border: none;
            background: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background: #218838;
        }

        .no-details {
            font-size: 18px;
            color: #777;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome, <%= (session != null) ? (String) session.getAttribute("username") : "Guest" %>!</h1>

    <%
        ItemInfo itemInfo = (ItemInfo) request.getAttribute("itemsDetails");
        if (itemInfo == null) {
    %>
    <p class="no-details">No details available for the selected product.</p>
    <%
    } else {
    %>
    <div class="item-details">
        <h2><%= itemInfo.getName() %></h2>
        <p><strong>Price:</strong> $<%= itemInfo.getPrice() %></p>
        <p><strong>Quantity available in stock:</strong>
            <% if (itemInfo.getQuantity() == 0) { %>
            <span>Not available</span>
            <% } else { %>
            <%= itemInfo.getQuantity() %>
            <% } %>
        </p>
        <p><strong>Description:</strong> <%= itemInfo.getDescription() %></p>
    </div>

    <!-- Add to Cart Form -->
    <form action="ItemsDetailsServlet" method="post">
        <input type="hidden" name="itemid" value="<%= itemInfo.getId() %>">
        <label for="quantity">Enter quantity:</label>
        <input type="number" id="quantity" name="quantity" min="1" max="<%= itemInfo.getQuantity() %>" required>
        <button type="submit" name="action" value="Add to Cart">Add to Cart</button>
    </form>
    <%
        }
    %>
</div>

</body>
</html>