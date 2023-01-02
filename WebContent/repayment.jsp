<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Repayment</title>
</head>

<body><% int loanID =Integer.parseInt(request.getParameter("loanID"));
session.setAttribute("loanID", loanID);%>
<c:out value="${loanID}"></c:out>
<form action="repaymentservlet" method="post">
Enter the amount <input type="number" id="amount" name="amount">
Select the account to transfer <select name="account" id="account">
<c:forEach var="account" items="${customer.accounts}">
	<option value="${account.value.accountNumber}">"${account.key}"</option>
</c:forEach>
</select>
<input type="submit" value="go">
</form>
</body>
</html>