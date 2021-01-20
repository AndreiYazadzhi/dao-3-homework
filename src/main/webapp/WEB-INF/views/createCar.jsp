<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Car</title>
</head>
<body>
<h1>Car creator</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/add">

    Please enter Car model:<input type="text" name="model" required>
    Please enter manufacturer id:<input type="number" name="manufacturerId" required>

    <button type="submit">Create</button>
</form>
</body>
</html>
