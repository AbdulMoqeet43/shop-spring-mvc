<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Warehouse Staff Jobs</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }
        header {
            background: linear-gradient(to right, #007bff, #0056b3);
            color: white;
            text-align: center;
            padding: 1.5rem 0;
            font-size: 1.5rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 2rem 0;
        }
        .job-list {
            list-style: none;
            padding: 0;
        }
        .job-item {
            background: white;
            margin: 1rem 0;
            padding: 1.5rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
        }
        .job-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }
        .job-title {
            font-size: 1.3rem;
            font-weight: bold;
            color: #007bff;
            margin: 0;
        }
        .job-description {
            margin-top: 0.5rem;
            font-size: 1rem;
            color: #555;
        }
        .job-item form {
            display: inline;
        }
        .job-item button {
            all: unset;
            cursor: pointer;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease-in-out;
        }
        .job-item button:hover {
            color: #0056b3;
            text-decoration: underline;
        }
        .logout-button {
            position: absolute;
            top: 20px;
            right: 20px;
        }
        .logout-button input[type="submit"] {
            background-color: #ff4d4d;
            color: white;
            border: none;
            padding: 8px 16px;
            font-size: 0.9rem;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease-in-out;
        }
        .logout-button input[type="submit"]:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>

<div class="logout-button">
    <form action="/warehouse" method="post">
        <input type="hidden" name="action" value="Logout">
        <input type="submit" value="Logout">
    </form>
</div>

<header>
    Warehouse Staff Jobs
</header>

<div class="container">
    <ul class="job-list">
        <li class="job-item">
            <form method="post" action="/warehouse">
                <input type="hidden" name="action" value="manageInventory">
                <button type="submit">
                    <p class="job-title">Manage Inventory</p>
                </button>
            </form>
            <p class="job-description">Manage inventory levels, update product information, and input supplier details.</p>
        </li>

        <li class="job-item">
            <form method="post" action="/warehouse">
                <input type="hidden" name="action" value="OrderProcessing">
                <button type="submit">
                    <p class="job-title">Order Processing Clerk</p>
                </button>
            </form>
            <p class="job-description">Review and process online orders, update order statuses, and communicate with customers about their orders.</p>
        </li>

        <li class="job-item">
            <p class="job-title">Customer Returns Handler</p>
            <p class="job-description">Process online return requests, issue refunds or exchanges, and update the system with return details.</p>
        </li>

        <li class="job-item">
            <p class="job-title">Customer Support Assistant</p>
            <p class="job-description">Respond to customer inquiries via email or live chat about stock, delivery, or order status.</p>
        </li>
    </ul>
</div>

</body>
</html>
