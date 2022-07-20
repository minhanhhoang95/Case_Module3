<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/20/2022
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/users?action=create">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>PassWord</th>
            <th>FullName</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.getId()}"/></td>
                <td><c:out value="${user.getUsername()}"/></td>
                <td><c:out value="${user.getPassword()}"/></td>
                <td><c:out value="${user.getFullName()}"/></td>
                <td><c:out value="${user.getPhone()}"/></td>
                <td><c:out value="${user.getAddress()}"/></td>
                <td><c:out value="${user.getEmail()}"/></td>
                <td><c:out value="${user.getRole()}"/></td>

                <td>
                    <a href="/users?action=edit&id=${user.id}">Edit</a>
                    <a href="/users?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>