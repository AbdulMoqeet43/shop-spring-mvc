<%--
  Created by IntelliJ IDEA.
  Date: 21/01/2025
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Warehouse Staff Jobs</title>
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
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }
        header {
            background-color: #007bff;
            color: white;
            text-align: center;
            padding: 1rem 0;
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
            padding: 1rem;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .job-title {
            font-size: 1.2rem;
            font-weight: bold;
            color: #007bff;
        }
        .job-description {
            margin-top: 0.5rem;
        }



    </style>
</head>
<body>


<div class="basket-button">
    <form action="WarehouseServlet" method="post">
        <input type="submit" name="action" value="Logout">
    </form>
</div>



<header>
    <h1>Warehouse Staff Jobs</h1>
</header>
<div class="container">


    <ul class="job-list">
        <li class="job-item">
            <form method="post" action="WarehouseServlet" style="margin: 0;">
                <input type="hidden" name="action" value="manageInventory">
                <button type="submit" style="all: unset; cursor: pointer; color: #007bff; text-decoration: underline;">
                    <p class="job-title">Manage Inventory</p>
                </button>
            </form>
            <p class="job-description">Manage inventory levels, update product information and input supplier details</p>



        <li class="job-item">
            <form method="post" action="WarehouseServlet" style="margin: 0;">
                <input type="hidden" name="action" value="OrderProcessing">
                <button type="submit" style="all: unset; cursor: pointer; color: #007bff; text-decoration: underline;">
                    <p class="job-title">Order Processing Clerk</p>
                </button>
            </form>
        <p class="job-description">Review and process online orders, update order statuses, and communicate with customers about their orders.</p>












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
