<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bank.customer.Customer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%@include file="accountsnavbar.jsp"%><c:catch var="exception">
<%if(session.getAttribute("account")==null){
	int accountNumber = Integer.parseInt(request.getParameter("account"));
	Customer customer = (Customer) session.getAttribute("customer");
	if(!customer.getAccounts().containsKey(accountNumber)){
		response.sendRedirect("error.jsp");
	}
	session.setAttribute("account",customer.getAccounts().get(accountNumber));
}%>
</c:catch>
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>	
<body>
	<h2>${account.accountType} Account</h2>
	<h2>${account.accountNumber }</h2>
	<h2>Balance : ${account.balance}</h2>
<c:if test="${exception!=null}">
<p>There is an Exception ${exception}</p>
</c:if>
</body>
</html>