<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>StudentTask</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="Servlet" method="post">
    <button type="submit">123</button>
</form>
<c:forEach items="${list}" var="user">
    <br>
    <p>${list.get(i).toString()}</p>
    <p>-------------------</p>
    <br>

</c:forEach>

</body>
</html>
