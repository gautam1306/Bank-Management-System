<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@include file="customernavbar.jsp"%>
<html>
<head>
<style type="text/css">table{
	width:100%;
}
table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}x</style>
<meta charset="UTF-8">
<title>transfer</title>
</head>
<body>
<form action="transferServlet" method="post">
<table>
<tr><th>Select Account</th><td>
<select name="account" id="account">
<c:forEach var="account" items="${customer.accounts}">
	<option value="${account.value.accountNumber}">"${account.key}"</option>
</c:forEach>
</select></tr>
</tr>
<tr><th>Enter the account number</th><td><input type="number" id="toaccount" name="toaccount"></td></tr>
<tr><th>Re-enter the account number</th><td><input type="number" id="toaccountverify"></td></tr>
<tr><th>Enter the amount to be transfered</th><td><input type="number" id="amount" name="amount" ></td></tr>
<tr><th>Decription of transfer</th><td><input type="text" name="description"></td></tr>
 <tr><th> <label for="mode">Choose a mode of transaction</label></th>
<td>  <select name="mode" id="mode">
    <option value="RTGS">RTGS</option>
    <option value="IMBS">IMBS</option>
    <option value="NEFT">NEFT</option>  
    <option value="UPI">UPI</option>
  </select></td></tr>
<tr><th colspan=2><input type="submit" value="submit"></th></tr>
</table>
</form>
</body>
</html>