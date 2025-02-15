<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Admin</title>
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

        input[type="text"], input[type="password"], select, button {
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
    </style>
</head>
<body>
<div class="form-container">
    <h1>Add New Admin</h1>

    <form action="AdminServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="newUsername" placeholder="Enter admin username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter admin password" required>


        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="Admin">Admin</option>
        </select>


        <button type="submit" name="action" value="SubmitAdmin">Add Admin</button>
    </form>
</div>
</body>
</html>
