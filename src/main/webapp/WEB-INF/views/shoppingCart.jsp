<%@ page import="java.util.List" %>
<%@ page import="BO.ShoppingCart" %>
<%@ page import="BO.ShoppingCartManager" %>
<%@ page import="BO.UserManager" %>
<%@ page import="UI.Info.ShoppingCartInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.sql.SQLException" %>

<%--

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <style>

        h2, li {
            font-size: 1.5em;
        }
    </style>
</head>
<body>
<h2>Items in your shopping Cart</h2>
<ul>

    <%
        String username = (session != null) ? (String) session.getAttribute("username") : "";
        List<ShoppingCartInfo> shoppingCartInfos = (List<ShoppingCartInfo>) request.getAttribute("shoppingCart");





        if (!shoppingCartInfos.isEmpty()) {
            for (ShoppingCartInfo info : shoppingCartInfos) {
    %>
    <h2><%= info.getName() %></h2>
    <p><strong>You have Selected:</strong> <%=  info.getQuantity() %></p>
    <p><strong>Price for one is:</strong> $<%= info.getPrice() %></p>
    <p><strong>Price for selected items:</strong> $<%= info.getPrice() * info.getQuantity() %></p>


    <%
        }
    } else {
    %>
    <li>No items in the Cart</li>
    <%
        }
    %>

</ul>
</body>
</html>