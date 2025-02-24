<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f7;
            padding: 20px;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #007bff;
        }
        .logout-button {
            text-align: right;
        }
        .logout-button form input {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        button {
            padding: 8px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            color: white;
            transition: 0.3s;
        }
        button[name="action"][value="updateUsername"] { background-color: #28a745; }
        button[name="action"][value="updateRole"] { background-color: #ffc107; color: black; }
        button[name="action"][value="deleteUser"] { background-color: #dc3545; }
    </style>
</head>
<body>

<div class="container">

    <div class="logout-button">
        <form action="${pageContext.request.contextPath}/auth/logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>

    <h1>Admin Dashboard</h1>

    <h2>All Users</h2>
    <c:choose>
        <c:when test="${not empty users}">
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
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.role}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/updateUsername" method="post">
                                <input type="hidden" name="userId" value="<c:out value='${user.id}' />">
                                <input type="text" name="newUsername" placeholder="New Username" required>
                                <button type="submit" name="action" value="updateUsername">Update Username</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/updateRole" method="post">
                                <input type="hidden" name="userId" value="<c:out value='${user.id}' />">
                                <select name="role">
                                    <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                    <option value="CUSTOMER" ${user.role == 'CUSTOMER' ? 'selected' : ''}>CUSTOMER</option>
                                    <option value="MODERATOR" ${user.role == 'MODERATOR' ? 'selected' : ''}>MODERATOR</option>
                                    <option value="WAREHOUSE_STAFF" ${user.role == 'WAREHOUSE_STAFF' ? 'selected' : ''}>WAREHOUSE_STAFF</option>
                                </select>
                                <button type="submit" name="action" value="updateRole">Update Role</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/deleteUser" method="post">
                                <input type="hidden" name="userId" value="<c:out value='${user.id}' />">
                                <button type="submit" name="action" value="deleteUser">Delete User</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No users available</p>
        </c:otherwise>
    </c:choose>

    <div style="text-align:center; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/admin/addAdmin" method="post">
            <button type="submit" name="action" value="addAdmin" style="background-color: #007bff;">Add New Admin</button>
        </form>
    </div>

</div>

</body>
</html>
