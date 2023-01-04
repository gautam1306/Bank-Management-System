<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>                      
<html>                     
<head>                     
<meta charset="ISO-8859-1">
<title>Deposits</title>    
</head>                    
<body>                     
<table>
<tr><th>Deposit ID</th> <th>Amount</th><th>start date</th></tr>
<c:forEach var="deposit" items="${customer.recurringDeposit}" >
<tr><td>${deposit.value.depositid}</td><td>${deposit.value.amount}</td><td>${deposit.value.startDate}</td></tr>
</c:forEach>
</table>
<form action="createrecurringdeposit" method="get">
<input type="hidden" value="2" id="deposit">
<input type="submit" value="open fixed deposit">
</form>
<form action="openfixeddeposit" method="get"></form>
</body>                    
