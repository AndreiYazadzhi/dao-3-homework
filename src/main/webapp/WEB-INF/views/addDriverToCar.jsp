<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Add driver to car</h1>
<form method="post" action="${pageContext.request.contextPath}/addDriverToCar">
    Please enter Car id:<input type="number" name="carId" required>
    Please enter Driver id:<input type="number" name="driverId" required>

    <button type="submit">Create</button>
</form>
</body>
</html>
