<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dash Board</title>
</head>
<body>
	<c:out value="${customer.profile}"></c:out><br>
	<c:forEach var = "account" items = "${customer.accounts}" >
	<c:out value="${account.key}"></c:out>
	<c:out value="${account.value.accountType}"></c:out><br>
	<button onclick=""></button>
	</c:forEach>
</body>
</html>