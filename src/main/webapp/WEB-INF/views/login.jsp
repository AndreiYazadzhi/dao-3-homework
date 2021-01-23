<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>

<h4 style="color:red'>${errorMsg}"></h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <form method="post" action="${pageContext.request.contextPath}/login">
        Login <input required type="text" name="login">
        Password <input required type="password" name="password">
        <button type="Sign in">Send</button>
    </form>

    <form method="get" action="${pageContext.request.contextPath}/drivers/add">
        <button type="submit">
            Register
        </button>
    </form>

</form>
</body>
</html>
