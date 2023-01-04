<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="accountsnavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Beneficiary</title>
</head>
<body>
<form action="addBeneficiaryServlet" method="post">
Enter the Account Number<input type="number" id="beneficiaryAccount" name="beneficiaryAccount">
Enter the Transfer Limit<input type="number" id="transferLimit" name="transferLimit">
Enter the nickname for this account<input type="text" id="nickname" name="nickname"> 
<input type="submit" value="submit">
</form> 
</body>
</html> 