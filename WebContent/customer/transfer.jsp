<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@include file="accountsnavbar.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>transfer</title>
</head>
<body>
<form action="transferServlet" method="post">
Select Account
<select name="account" id="account">
<c:forEach var="account" items="${customer.accounts}">
	<option value="${account.value.accountNumber}">"${account.key}"</option>
</c:forEach>
</select><br>
Enter the account number:<input type="number" id="toaccount" name="toaccount"><br>
Reenter the account number:<input type="number" id="toaccountverify"><br>
Enter the amount to be transfered:<input type="number" id="amount" name="amount" ><br>

Decription of transfer<input type="text" name="description"><br>
  <label for="mode">Choose a mode of transaction:</label>
  <select name="mode" id="mode">
    <option value="RTGS">RTGS</option>
    <option value="IMBS">IMBS</option>
    <option value="NEFT">NEFT</option>  
    <option value="UPI">UPI</option>
  </select>
<input type="submit" value="submit"></form>
</body>
</html>