<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <input type="email" name="email" id="email" placeholder="email" required>
    <input type="text" name="username" placeholder="Username" required />
    <input type="password" name="password" placeholder="Password" required />
    <input type="text" name="address" id="address" placeholder="address" required>
    <button type="submit">Register</button>
</form>
<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
<c:if test="${not empty success}">
    <div style="color: green;">${success}</div>
</c:if>

<a href="${pageContext.request.contextPath}/user/login">Login</a>
</body>
</html>