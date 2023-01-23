<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    <%@include file="customernavbar.jsp"%>
    
<!DOCTYPE html>
<html>
<style>
.selection a:link, a:visited {
  background-color: #f44336;
  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

.selection a:hover, a:active {
  background-color: #f44336;
  
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Pay</title>
</head>
<body><br>
<div class="selection">
<a href="transfer">Pay Using the Account Number</a><br><br>
<a id="beneficiary" href="beneficiary">Pay to a Beneficiary</a><br><br>
<a id="addbeneficiary" href="add-beneficiary">Add Beneficiary</a><br>	
</div></body>
</html>