<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Online Shopping Center</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"], input[type="password"], select {
            width: 75%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .actions {
            display: flex;
            justify-content: space-between;
        }

        button {
            padding: 10px 15px;
            border: none;
            background-color: #007BFF;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to Online Shopping Center</h1>

    <% if (request.getAttribute("message") != null) { %>
    <p><%= request.getAttribute("message") %></p>
    <% } %>

    <% if (request.getParameter("error") != null) { %>
    <p style="color: red;"><%= request.getParameter("error") %></p>
    <% } %>


    <form action="loginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>



        <div class="actions">
            <button type="submit" name="action" value="signin">Sign In</button>
            <button type="submit" name="action" value="signup">Sign Up</button>
        </div>
    </form>
</div>

</body>
</html>
