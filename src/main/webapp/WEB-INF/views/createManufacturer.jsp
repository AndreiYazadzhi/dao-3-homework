<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Manufacturer</title>
</head>
<body>
<h1>Manufacturer creator</h1>
<form method="post" action="${pageContext.request.contextPath}/createManufacturer">

    Please enter Manufacturer name:<input type="text" name="name" required>
    Please enter Manufacturer country:<input type="text" name="country" required>

    <button type="submit">Create</button>
</form>
</body>
</html>
