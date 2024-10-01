<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/user/login" method="get">
    <input type="text" name="username" placeholder="Username" required />
    <input type="password" name="password" placeholder="Password" required />
    <button type="submit">Login</button>
</form>
<c:if test="${not empty error}">
    <div style="color: red;">${error}</div>
</c:if>
<c:if test="${not empty message}">
    <div style="color: green;">${message}</div>
</c:if>
</body>
</html>