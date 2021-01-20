<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello world!</h1>

<form method="get" action="${pageContext.request.contextPath}/createDriver">
    <button type="submit">
        Create Driver
    </button>
</form>
<form method="get" action="${pageContext.request.contextPath}/createManufacturer">
    <button type="submit">
        Create Manufacturer
    </button>
</form>
<form method="get" action="${pageContext.request.contextPath}/createCar">
    <button type="submit">
        Create Car
    </button>
</form>
<form method="get" action="${pageContext.request.contextPath}/addDriverToCar">
    <button type="submit">
        Add driver to Car
    </button>
</form>

<form method="get" action="${pageContext.request.contextPath}/cars">
    <button type="submit">
        Show Cars
    </button>
</form>

<form method="get" action="${pageContext.request.contextPath}/drivers">
    <button type="submit">
        Show drivers
    </button>
</form>

<form method="get" action="${pageContext.request.contextPath}/manufacturers">
    <button type="submit">
        Show manufacturers
    </button>
</form>
</body>
</html>
