<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, UI.Info.RecommendationInfo" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Suggestions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            color: #333;
        }

        .section {
            margin-bottom: 30px;
            padding: 15px;
            border-radius: 8px;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .item {
            padding: 15px;
            border-radius: 8px;
            border: 1px solid #ddd;
            margin-top: 10px;
        }

        .item h3 {
            margin: 0;
            font-size: 18px;
            color: #007bff;
        }

        .item p {
            font-size: 14px;
            margin: 5px 0;
        }

        .no-items {
            font-size: 16px;
            color: #777;
        }

        .basket-button {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }

        .basket-button input {
            padding: 10px 15px;
            border: none;
            background: #007bff;
            color: white;
            font-size: 14px;
            cursor: pointer;
            border-radius: 5px;
        }

        .basket-button input:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Suggestions for you, <%= (session != null) ? (String) session.getAttribute("username") : "Guest" %></h1>

    <div class="basket-button">
        <!--<input type="submit" name="action" value="View Cart">-->
    </div>

    <!-- Most Buying Items -->
    <div class="section">
        <h2>Most Buying Items</h2>
        <%
            List<RecommendationInfo> availableRecommendation = (List<RecommendationInfo>) request.getAttribute("availableRecommendations");
            if (availableRecommendation == null || availableRecommendation.isEmpty()) {
        %>
        <p class="no-items">No Suggestions</p>
        <%
        } else {
            for (RecommendationInfo recommendation : availableRecommendation) {
        %>
        <div class="item">
            <h3><%= recommendation.getName() %></h3>
            <p><strong>Price:</strong> $<%= recommendation.getPrice() %></p>
            <p><strong>Description:</strong> <%= recommendation.getDescription() %></p>
        </div>
        <%
                }
            }
        %>
    </div>

    <!-- Recently Viewed Items -->
    <div class="section">
        <h2>Most Viewed Items</h2>
        <%
            List<RecommendationInfo> availableSeenRecommendations = (List<RecommendationInfo>) request.getAttribute("availableSeenRecommendations");
            if (availableSeenRecommendations == null || availableSeenRecommendations.isEmpty()) {
        %>
        <p class="no-items">No Recently Viewed Items</p>
        <%
        } else {
            for (RecommendationInfo recommendationinfo : availableSeenRecommendations) {
        %>
        <div class="item">
            <h3><%= recommendationinfo.getName() %></h3>
            <p><strong>Price:</strong> $<%= recommendationinfo.getPrice() %></p>
            <p><strong>Description:</strong> <%= recommendationinfo.getDescription() %></p>
        </div>
        <%
                }
            }
        %>
    </div>
</div>

</body>
</html>