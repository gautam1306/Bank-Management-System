<%@page import="com.bank.customer.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.customer.Accounts"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="accountsnavbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
</style>
<head>
<body>
<% response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		  response.setDateHeader ("Expires", 0);%>
<h2>${account.accountNumber}</h2>
<%ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions"); %>
<c:if test="${transactions[0]!=null}">
<table>
<tr><th>Transaction ID</th>
<th>Account Number</th><th>Type</th><th>Transfer Amount</th><th>Description</th><th>Balance</th></tr>
<c:forEach var="transaction" items="${transactions}">
	<tr>
	<td><c:out value="${transaction.transaction_number}"/></td>
	<td>
	<c:choose>
	<c:when test="${transaction.from_account_number==account.accountNumber }">
	${transaction.to_account_number}
	</c:when>
	<c:when test="${transaction.from_account_number!=account.accountNumber  }">
	${transaction.from_account_number}
	</c:when>	
	</c:choose>
	</td>
	<td><c:choose>
	<c:when test="${transaction.from_account_number==account.accountNumber}">
	${"Debit"}
	</c:when>
	<c:when test="${transaction.from_account_number!=account.accountNumber  }">
	${"Credit"}
	</c:when>	
	</c:choose></td>
	<td><c:out value="${transaction.transferamount}"/></td>
	<td><c:out value="${transaction.description}"></c:out></td>
	<td><c:out value="${transaction.balance }"></c:out>
	</tr>
</c:forEach>
</table></c:if>
<c:if test="${transactions[0]==null}"><h2>Sorry no Previous records have been registered in this account</h2></c:if>
</body>
</html>