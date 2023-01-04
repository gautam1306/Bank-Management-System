<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% %>
    <%@include file="accountsnavbar.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
<style>
	table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
	.button{
		background-color: #3399ff;
	} </style>
<meta charset="ISO-8859-1">
<title>account</title>
</head>
<body>
<table><tr>
<th>Account Holder</th><td>${customer.profile.name}</td></tr>
<tr><th>IFSC</th><td>${account.ifsc}</td><tr>
<tr><th>Account Number</th><td>${account.accountNumber}</td></tr>
<tr><th>Account Type</th><td>${account.accountType}</td></tr>
<c:if test="${account.card!=null}"> <tr><th>Card</th><td>${account.card.cardnumber}</td></tr></c:if>
<tr><th>Spending Limit</th><td>${account.transactionlimit}</td></tr>
</table>
</body>
</html>