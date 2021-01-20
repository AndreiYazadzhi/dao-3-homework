<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<h1>Cars page</h1>
<table border = "1">
    <tr>
        <th>ID</th>
        <th>Model</th>
        <th>Manufacturer</th>
        <th>Country</th>
        <th>Drivers</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <c:out value="${car.id}"/>
            </td>
            <td>
                <c:out value="${car.model}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.country}"/>
            </td>
            <td>
                <c:forEach var="driver" items="${car.drivers}">
                    <c:out value="${driver.id}"/>
                    <c:out value="${driver.name}"/>
                    <c:out value="${driver.licenseNumber}"/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    <form method="post" action="${pageContext.request.contextPath}/cars/delete">
        Please enter Car Id:<input type="number" name="carId" required>
        <button type="submit">
            Delete
        </button>
    </form>
</table>
</body>
</html>
