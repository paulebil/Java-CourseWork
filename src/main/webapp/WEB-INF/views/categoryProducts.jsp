<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Products in ${category}</title>
</head>
<body>
<h1>Products in Category: ${category}</h1>
<ul>
    <c:forEach var="product" items="${products}">
        <li>${product.name} - ${product.price}</li>
    </c:forEach>
</ul>
</body>
</html>