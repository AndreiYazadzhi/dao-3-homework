<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drivers</title>
</head>
<body>
<h1>Drivers page</h1>

<table border = "1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>License Number</th>
        <th>Login</th>
    </tr>
    <c:forEach var = "driver" items="${drivers}">
        <tr>
            <td>
                <c:out value="${driver.id}"/>
            </td>
            <td>
                <c:out value="${driver.name}"/>
            </td>
            <td>
                <c:out value="${driver.licenseNumber}"/>
            </td>
            <td>
                <c:out value="${driver.login}"/>
            </td>
        </tr>
    </c:forEach>
    <form method="post" action="${pageContext.request.contextPath}/drivers/delete">
        Please enter Driver Id:<input type="number" name="driver_id" required>
        <button type="submit">
            Delete
        </button>
    </form>
</table>
</body>
</html>
