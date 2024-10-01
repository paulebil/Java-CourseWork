<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <ul>
        <c:if test="${empty users}">
            <li>No users found.</li>
        </c:if>
        <c:forEach var="user" items="${users}">
            <li>${user.username} - ${user.role}</li>
        </c:forEach>
    </ul>
</body>
</html>