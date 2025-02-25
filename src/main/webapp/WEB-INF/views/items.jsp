<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.code.model.ItemInfo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Items</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; padding: 20px; background-color: #f5f5f5; }
        h1 { color: #333; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        .basket-button { display: flex; justify-content: flex-end; gap: 10px; margin-bottom: 20px; }
        .basket-button input { padding: 10px 15px; border: none; background: #007bff; color: white; font-size: 14px; cursor: pointer; border-radius: 5px; }
        .basket-button input:hover { background: #0056b3; }
        .items-container { display: flex; flex-wrap: wrap; gap: 20px; }
        .item { background: #fff; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); width: calc(50% - 20px); }
        .item h3 { margin: 0 0 10px; }
        .item p { margin: 0 0 10px; }
        .item a { text-decoration: none; color: #007bff; font-weight: bold; }
        .item a:hover { text-decoration: underline; }
        .no-items { font-size: 18px; color: #777; }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome, <%= request.getAttribute("username") %>!</h1>

    <form action="/home" method="post">
        <div class="basket-button">
            <input type="submit" name="action" value="View Cart">
            <input type="submit" name="action" value="Empty Cart">
            <input type="submit" name="action" value="Recommended for You">
            <input type="submit" name="action" value="Logout">
        </div>

        <h2>Select Items:</h2>

        <div class="items-container">
            <%
                List<ItemInfo> availableItems = (List<ItemInfo>) request.getAttribute("availableItems");
                if (availableItems == null || availableItems.isEmpty()) {
            %>
            <p class="no-items">No items available in the stock.</p>
            <%
            } else {
                for (ItemInfo item : availableItems) {
            %>
            <div class="item">
                <h3><%= item.getName() %></h3>
                <p>Price: $<%= item.getPrice() %></p>
                <a href="/item/details?itemid=<%= item.getId() %>">View Details</a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </form>
</div>

</body>
</html>
