<%@ page import="java.util.List" %>
<%@ page import="UI.Info.ItemInfo" %>
<%@ page import="BO.User" %>
<%@ page import="BO.UserRoles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>

        .basket-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        label {
            font-size: 1.5em;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }

        h1, h2 {
            color: #007bff; /* Use the same blue as Admin Dashboard */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        button {
            padding: 5px 10px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            color: white;
        }

        button[name="action"][value="updateItem"] {
            background-color: #28a745;
        }

        button[name="action"][value="deleteItem"] {
            background-color: #dc3545;
        }

        button:hover {
            opacity: 0.9;
        }

        input[type="number"] {
            width: 102px;
            padding: 5px;
            margin-right: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="basket-button">
    <form action="manageInventoryServlet" method="post">
        <input type="submit" name="action" value="Logout">
    </form>
</div>

<%
    // Validate the logged-in user
    User user = (User) session.getAttribute("user");
%>
<h1>Warehouse Staff Dashboard</h1>
<p>Welcome, <%= user.getUsername() %>!</p>

<%
    List<ItemInfo> items = (List<ItemInfo>) request.getAttribute("items");
    if (items != null && !items.isEmpty()) {
%>

<h2>All Items</h2>
<h2>Manage Categories</h2>
<form action="manageInventoryServlet" method="post">
    <input type="hidden" name="categoryID" value="">
    <input type="text" name="categoryName" placeholder="Enter Category Name" required>
    <button type="submit" name="action" value="addCategory" style="background-color: #007bff; color: white;">Add Category</button>
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Stock</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (ItemInfo item : items) { %>
    <tr id="row_<%= item.getId() %>">
        <td><%= item.getId() %></td>
        <td>
            <span id="name_<%= item.getId() %>"><%= item.getName() %></span>
            <input type="text" id="edit_name_<%= item.getId() %>" value="<%= item.getName() %>" style="display:none;">
        </td>
        <td>
            <span id="price_<%= item.getId() %>">$<%= item.getPrice() %></span>
            <input type="number" id="edit_price_<%= item.getId() %>" value="<%= item.getPrice() %>" step="0.01" style="display:none;">
        </td>
        <td>
            <span id="desc_<%= item.getId() %>"><%= item.getDescription() %></span>
            <input type="text" id="edit_desc_<%= item.getId() %>" value="<%= item.getDescription() %>" style="display:none;">
        </td>
        <td>
            <span id="qty_<%= item.getId() %>"><%= item.getQuantity() %></span>
            <input type="number" id="edit_qty_<%= item.getId() %>" value="<%= item.getQuantity() %>" style="display:none;">
        </td>
        <td>
            <button onclick="toggleEditMode(<%= item.getId() %>)" id="editBtn_<%= item.getId() %>" style="background-color: #007bff;">Edit</button>
            <form action="manageInventoryServlet" method="post" style="display: inline;">
                <input type="hidden" name="itemID" value="<%= item.getId() %>">
                <input type="hidden" name="name" id="form_name_<%= item.getId() %>">
                <input type="hidden" name="price" id="form_price_<%= item.getId() %>">
                <input type="hidden" name="description" id="form_desc_<%= item.getId() %>">
                <input type="hidden" name="quantity" id="form_qty_<%= item.getId() %>">
                <button type="submit" name="action" value="updateItem" id="saveBtn_<%= item.getId() %>" style="background-color: #28a745; display:none;">Save</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<script>
    function toggleEditMode(itemId) {
        let editBtn = document.getElementById("editBtn_" + itemId);
        let saveBtn = document.getElementById("saveBtn_" + itemId);

        let nameSpan = document.getElementById("name_" + itemId);
        let nameInput = document.getElementById("edit_name_" + itemId);
        let priceSpan = document.getElementById("price_" + itemId);
        let priceInput = document.getElementById("edit_price_" + itemId);
        let descSpan = document.getElementById("desc_" + itemId);
        let descInput = document.getElementById("edit_desc_" + itemId);
        let qtySpan = document.getElementById("qty_" + itemId);
        let qtyInput = document.getElementById("edit_qty_" + itemId);

        if (editBtn.innerText === "Edit") {
            // Switch to edit mode
            nameSpan.style.display = "none";
            nameInput.style.display = "inline";
            priceSpan.style.display = "none";
            priceInput.style.display = "inline";
            descSpan.style.display = "none";
            descInput.style.display = "inline";
            qtySpan.style.display = "none";
            qtyInput.style.display = "inline";

            saveBtn.style.display = "inline";
            editBtn.innerText = "Cancel";
        } else {
            // Switch to view mode
            nameSpan.style.display = "inline";
            nameInput.style.display = "none";
            priceSpan.style.display = "inline";
            priceInput.style.display = "none";
            descSpan.style.display = "inline";
            descInput.style.display = "none";
            qtySpan.style.display = "inline";
            qtyInput.style.display = "none";

            saveBtn.style.display = "none";
            editBtn.innerText = "Edit";
        }
    }

    function prepareFormData(itemId) {
        document.getElementById("form_name_" + itemId).value = document.getElementById("edit_name_" + itemId).value;
        document.getElementById("form_price_" + itemId).value = document.getElementById("edit_price_" + itemId).value;
        document.getElementById("form_desc_" + itemId).value = document.getElementById("edit_desc_" + itemId).value;
        document.getElementById("form_qty_" + itemId).value = document.getElementById("edit_qty_" + itemId).value;
    }

    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', function(event) {
            let itemId = this.querySelector('input[name="itemID"]').value;
            prepareFormData(itemId);
        });
    });
</script>

<% } else { %>
<p>No items available in the inventory.</p>
<% } %>

<div class="actions">

    <!-- Form to Add New Item -->
    <form action="manageInventoryServlet" method="post" style="display: inline;">
        <button type="submit" name="action" value="AddNewItem" style="background-color: #007bff; color: white;">Add New Item</button>
    </form>

</div>

</body>
</html>
