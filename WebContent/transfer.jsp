<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@include file="accountsnavbar.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>transfer</title>
</head>
<body>
<form action="transferServlet" method="post">
Enter the amount to be transfered:<input type="number" id="amount" name="amount" ><br>
Enter the account number:<input type="number" id="account_number" name="account_number">
<br>
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