<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@page import = "com.bank.customer.Accounts"%>
<head>
<meta charset="UTF-8">
<title>Pay Beneficiary</title>
</head>
<body>
<h2>Paying to</h2>
<h2>${beneficiary.nickname}</h2>
<h2>${beneficiary.accountNumber}</h2>
<h2>Paying from</h2>
<% session.setAttribute("accountnumber", Integer.parseInt(request.getParameter("accountNumber"))); %>
<form action="beneficiarytransfer" method="post" accept-charset="utf-8	">
<select name="account" id="account">
<c:forEach var="account" items="${customer.accounts}">
	<option value="${account.value.accountNumber}">"${account.key}"</option>
</c:forEach>
<%=request.getAttribute("transactionstatus")==null?"":request.getAttribute("transactionstatus")%>
</select>
<input type="hidden" value="${accountNumber}" name="accountNumber" id="accountNumber">
<h2>${accountNumber }</h2>
<input type="hidden" value=1 name="beneficiary">
<h2>Enter the Amount : </h2> <input type="number" id="amount" name="amount">
<select name="mode" id="mode">
    <option value="RTGS">RTGS</option>
    <option value="IMBS">IMBS</option>
    <option value="NEFT">NEFT</option> 
	<option value="UPI">UPI</option>
  </select>
<input type="text" name="description" id="description">
<input type="submit" value="submit">
</form>
</body>
</html>