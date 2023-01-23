<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="customernavbar.jsp"%>
<!DOCTYPE html>
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
	  
	}</style>
<title>Add Beneficiary</title>
</head>
<body>
<form action="addBeneficiaryServlet" method="post">
<table>
<tr><th>Enter the Account Number</th><td><input type="number" id="beneficiaryAccount" name="beneficiaryAccount"></td></tr>
<tr><th>Enter the Transfer Limit</th><td><input type="number" id="transferLimit" name="transferLimit"></td></tr>
<tr><th>Enter the nickname for this account</th><td><input type="text" id="nickname" name="nickname"></td></tr> 
<tr><th colspan="2"><input type="submit" value="submit"></th></tr>
</table>
</form> 
</body>
</html> 