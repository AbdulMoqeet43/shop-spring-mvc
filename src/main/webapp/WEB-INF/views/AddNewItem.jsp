<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Item</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .form-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-container h1 {
            text-align: center;
        }

        input[type="text"], input[type="number"], textarea, button {
            width: 100%;
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        button[type="reset"] {
            background-color: #6c757d;
        }

        button[type="reset"]:hover {
            background-color: #5a6268;
        }

        textarea {
            resize: none;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Add New Item</h1>

    <form action="manageInventoryServlet" method="post">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" placeholder="Enter item name" required minlength="2" maxlength="100">

        <label for="itemPrice">Price:</label>
        <input type="number" id="itemPrice" name="price" placeholder="Enter item price" step="0.01" min="0.01" max="1000000" required>

        <label for="itemQuantity">Quantity:</label>
        <input type="number" id="itemQuantity" name="quantity" placeholder="Enter item quantity" min="1" max="10000" required>

        <label for="itemDesc">Description:</label>
        <textarea id="itemDesc" name="description" rows="4" placeholder="Enter item description" minlength="10" maxlength="500" required></textarea>

        <!-- Submit button for "SubmitItem" action -->
        <button type="submit" name="action" value="SubmitItem">Add Item</button>

        <button type="reset">Reset</button>
    </form>
</div>
</body>
</html>
