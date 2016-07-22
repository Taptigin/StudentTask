<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="list" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>StudentTask</title>
</head>
<body>
<h1>StudentTask</h1>
<br>
<form method="post" action="Servlet">
    <button type="commit">123</button>
</form>
<c:forEach items="${list}" var="user">
    <br>
    <p>${list.get(i).toString()}</p>
    <p>-------------------</p>
    <br>

</c:forEach>

<c:forEach var="user" items="${list}" varStatus="rowCounter">
<p>${user.getFirstname()}</p>
    <p>${user.firstname}</p>
</c:forEach>
</body>
</html>