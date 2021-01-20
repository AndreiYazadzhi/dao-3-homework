<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturers</title>
</head>
<body>
<h1>Manufacturers page</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Country</th>
    </tr>
    <c:forEach var="manufacturer" items="${manufacturers}">
        <tr>
            <td>
                <c:out value="${manufacturer.id}"/>
            </td>
            <td>
                <c:out value="${manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${manufacturer.country}"/>
            </td>
        </tr>
    </c:forEach>
    <form method="post" action="${pageContext.request.contextPath}/deleteManufacturer">
        Please enter Manufacturer Id:<input type="number" name="manufacturerId" required>
        <button type="submit">
            Delete
        </button>
    </form>
</table>
</body>
</html>
