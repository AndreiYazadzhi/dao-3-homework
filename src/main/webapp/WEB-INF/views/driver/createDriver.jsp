<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Driver</title>
</head>
<body>
<h1>Driver creator</h1>
<form method="post" action="${pageContext.request.contextPath}/drivers/add">

    Please enter Driver name:<input type="text" name="name" required>
    Please enter license number:<input type="text" name="license_number" required>
    Please enter Driver login:<input type="text" name="login" required>
    Please enter Driver password:<input type="password" name="password" required>
    <button type="submit">Create</button>
</form>
</body>
</html>
