<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<p>Username: ${user.username}</p>
<p>Email: ${user.email}</p>
<p>Address: ${user.address}</p>
<p>Role: ${user.role}</p>
<a href="${pageContext.request.contextPath}/user/all">View All Users</a>
</body>
</html>