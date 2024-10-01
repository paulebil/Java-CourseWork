<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<p>Username: ${user.username}</p>
<p>Role: ${user.role}</p>
<a href="${pageContext.request.contextPath}/user/all">View All Users</a>
</body>
</html>