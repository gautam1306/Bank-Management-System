<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@include file="navbar.html" %>
<meta charset="ISO-8859-1">

<title>Dash Board</title>
</head>
<body>
	<c:out value="${customer.profile}"></c:out><br>
	<c:forEach var = "account" items = "${customer.accounts}" >
	<form action="account" method="post">
	<input type="hidden" id="account" name="account" value="${account.key}">
	<input type="Submit" value="Submit">
	</form>
	<c:out value="${account.value.accountType}"></c:out><br>
	</c:forEach>
</body>
</html>