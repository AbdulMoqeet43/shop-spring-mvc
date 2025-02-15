<%@ page import="BO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="UI.Info.ItemInfo" %>
<%@ page import="BO.UserRoles" %>
<%@ page import="UI.Info.UserInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #eef2f7;
            color: #333;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            text-align: center;
            color: #007bff;
            font-weight: bold;
        }

        .welcome {
            text-align: center;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .logout-button {
            text-align: right;
        }

        .logout-button input {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
        }

        .logout-button input:hover {
            background-color: #c82333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .actions {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .actions form {
            display: inline;
        }

        button {
            padding: 8px 12px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            color: white;
            transition: 0.3s;
            font-size: 14px;
        }

        button[name="action"][value="UpdateUsername"] {
            background-color: #28a745;
        }

        button[name="action"][value="updateRole"] {
            background-color: #ffc107;
            color: black;
        }

        button[name="action"][value="deleteUser"] {
            background-color: #dc3545;
        }

        button[name="action"][value="AddAdmin"] {
            background-color: #007bff;
            margin-top: 20px;
        }

        button:hover {
            opacity: 0.8;
        }

        input[type="text"], select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .add-admin-container {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="logout-button">
        <form action="AdminServlet" method="post">
            <input type="submit" name="action" value="Logout">
        </form>
    </div>

    <%
        // Validate the logged-in user
        User user = (User) session.getAttribute("user");
    %>

    <h1>Admin Dashboard</h1>
    <p class="welcome">Welcome, <%= user.getUsername() %>!</p>

    <%-- Display Users Table --%>
    <%
        List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
        if (users != null && !users.isEmpty()) {
    %>
    <h2>All Users</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (UserInfo userInfo : users) { %>
        <tr>
            <td><%= userInfo.getId() %></td>
            <td><%= userInfo.getUsername() %></td>
            <td><%= userInfo.getRole() %></td>
            <td>
                <div class="actions">
                    <%-- Update Username Form --%>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="userId" value="<%= userInfo.getId() %>">
                        <input type="text" name="newUsername" placeholder="Enter new username" required>
                        <button type="submit" name="action" value="UpdateUsername">Update Username</button>
                    </form>

                    <%-- Update Role Form --%>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="userId" value="<%= userInfo.getId() %>">
                        <select name="role" required>
                            <% for (BO.UserRoles role : BO.UserRoles.values()) { %>
                            <option value="<%= role %>" <%= role.equals(userInfo.getRole()) ? "selected" : "" %>>
                                <%= role %>
                            </option>
                            <% } %>
                        </select>
                        <button type="submit" name="action" value="updateRole">Update Role</button>
                    </form>

                    <%-- Delete User Form --%>
                    <form action="AdminServlet" method="post">
                        <input type="hidden" name="userId" value="<%= userInfo.getId() %>">
                        <button type="submit" name="action" value="deleteUser">Delete User</button>
                    </form>
                </div>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p style="text-align: center; font-size: 16px; color: #666;">No users available</p>
    <% } %>

    <div class="add-admin-container">
        <form action="AdminServlet" method="post">
            <button type="submit" name="action" value="AddAdmin">Add New Admin</button>
        </form>
    </div>

</div>

</body>
</html>
