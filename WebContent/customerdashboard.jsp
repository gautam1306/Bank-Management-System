<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@include file="customernavbar.jsp" %>
<meta charset="ISO-8859-1">

<title>Dash Board</title>
</head>
<style>
	table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
	.button{
		background-color: #3399ff;
	} 
</style>
<body>
	<c:out value="${customer.profile}"></c:out><br>
	<table style="width:50%">
	<tr>
	<th>Account Number</th>
	<th>Account Type</th>
	<th>Account Balance</th>
	<th></th>
	</tr>
	<c:forEach var = "account" items = "${customer.accounts}" >
	<tr>
	<td>${account.value.accountNumber}</td>
	<td>
	<c:out value="${account.value.accountType}"></c:out><br>
	</td>
	<td>${account.value.balance}</td>
	<td>
	<form action="account" method="post">
	<input type="hidden" id="account" name="account" value="${account.key}">
	<input type="Submit" class="button" value= "GO">
	</form>
	</td>
	</tr></c:forEach>
	</table>
</body>
</html>