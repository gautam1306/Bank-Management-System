<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bank.customer.Customer" %>

<!DOCTYPE html>
<style>
a.button {
    -webkit-appearance: button;
    -moz-appearance: button;
    appearance: button;
    text-decoration: none;
    color: white;
    background-color: blue; 
}
</style>
<html>
<head>
<%@include file="accountsnavbar.html"%>
<%
	int accountNumber = Integer.parseInt(request.getParameter("account"));
	Customer customer = (Customer) session.getAttribute("customer");
	if(!customer.getAccounts().containsKey(accountNumber)){
		response.sendRedirect("error.jsp");
	}
	session.setAttribute("account",customer.getAccounts().get(accountNumber));
%>
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>
<body>
	<h2>${account.accountType} Account</h2>
	<h2>${account.accountNumber }</h2>
	<h2>Balance : ${account.balance}</h2>
</body>
</html>