<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Warehouse Inventory</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        h2 {
            background-color: #007bff;
            color: white;
            padding: 1rem;
            margin: 0;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        form {
            display: inline-block;
            margin: 5px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 5px;
            transition: 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        input[type="text"], input[type="number"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 5px 0;
        }
        .add-item-form {
            margin-top: 20px;
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
        }
    </style>
</head>
<body>

<h2>Warehouse Inventory</h2>

<div class="container">
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="item" items="${inventory}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>$${item.price}</td>
                <td>${item.quantity}</td>
                <td>
                    <form action="increase" method="post">
                        <input type="hidden" name="itemID" value="${item.id}">
                        <input type="number" name="quantity" min="1" required>
                        <button type="submit">Increase</button>
                    </form>
                    <form action="decrease" method="post">
                        <input type="hidden" name="itemID" value="${item.id}">
                        <input type="number" name="quantity" min="1" required>
                        <button type="submit" style="background-color: #ff9800;">Decrease</button>
                    </form>
                    <form action="delete" method="post">
                        <input type="hidden" name="itemID" value="${item.id}">
                        <button type="submit" style="background-color: #dc3545;">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="add-item-form">
        <h3>Add New Item</h3>
        <form action="add" method="post">
            <input type="text" name="itemName" placeholder="Item Name" required>
            <input type="text" name="description" placeholder="Description" required>
            <input type="number" name="price" step="0.01" placeholder="Price" required>
            <input type="number" name="quantity" min="1" placeholder="Quantity" required>
            <button type="submit">Add Item</button>
        </form>
    </div>
</div>

</body>
</html>
