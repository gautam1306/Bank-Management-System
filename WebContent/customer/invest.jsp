<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>                      
<html>     
<%@include file="customernavbar.jsp" %>
<style>table, th, td{
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
	.button{
		background-color: #3399ff;
	}
	</style>
<head>                     
<meta charset="ISO-8859-1">
<title>Deposits</title>    
</head>                    
<body> 
                    
<table>
<tr><th colspan="4">Recurring Deposit</th></tr>
<tr><th>Deposit ID</th> <th>Amount</th><th>start date</th></tr>
<c:forEach var="deposit" items="${customer.recurringDeposit}">
<tr><td>${deposit.value.depositid}</td><td>${deposit.value.amount}</td><td>${deposit.value.startDate}</td>
<td>
<form action="recurringDeposit">
<input type="hidden" value=${deposit.value.depositid} id="depositID" name="depositID">
<input type="submit" value="select">
</form>
</tr>
</c:forEach>
<tr><th colspan="4"><a href="openrecurringdeposit"><button>Open Recurring Deposit</button></a>
</th></tr>
</table>
<table>
<tr><th colspan="4">Fixed Deposit</th></tr>
<tr><th>Deposit ID</th> <th>Amount</th><th>start date</th></tr>
<c:forEach var="deposit" items="${customer.fixedDeposits}">
<tr><td>${deposit.value.depositId}</td><td>${deposit.value.amount}</td><td>${deposit.value.startDate}</td></tr>
</c:forEach>
<tr><th colspan="3"><a href="openf	ixeddeposit"><button>Open Fixed Deposit</button></a></th></tr>
</table>
</body>                    
