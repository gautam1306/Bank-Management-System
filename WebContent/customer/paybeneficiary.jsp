<%@page import="com.bank.customer.Beneficiary"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@page import="com.bank.customer.Accounts"%>
<%@page import="com.bank.customer.Customer"%>
	<%
		Customer customer = (Customer) session.getAttribute("customer");
		int toaccountnumber = Integer.parseInt(request.getParameter("accountNumber"));
		Beneficiary beneficiary = customer.getBeneficiaryList().get(toaccountnumber);
		pageContext.setAttribute("beneficiary", beneficiary);
		request.setAttribute("toAccountNumber",beneficiary.getAccountnumber()); 
	%>
<head>
<style type="text/css">
table {
	width: 100%;
}

table, th, td {
	border: 1px solid;
	margin-left: auto;
	margin-right: auto;
	border-color: #3399ff;
}
</style>
<meta charset="UTF-8">
<title>Pay Beneficiary</title>
</head>
<body>


	<table >
		<form action="beneficiarytransfer" method="post"
			accept-charset="utf-8">
			<tr align="center">
				<th>Paying to</th>
				<td><c:out value="${beneficiary.nickName}" /></td>
			</tr>
			<tr align="center">
				<th>Account Number</th>
				<td>${beneficiary.accountnumber}</td>
			</tr>
			<tr align="center">
				<th>Paying from</th>
				<th><select name="account" id="account">
						<c:forEach var="account" items="${customer.accounts}">
							<option value="${account.value.accountNumber}">"${account.key}"</option>
						</c:forEach>
				</select></th>
			</tr>
			<input type="hidden" value="${beneficiary.accountnumber}" name="accountNumber"
				id="accountNumber">
			<%-- <h2>${accountNumber }</h2> --%>
			<!--  -->
			<input type="hidden" value=1 name="beneficiary">
			<tr align="center">
				<th>Enter the Amount</th>
				<td><input type="number" id="amount" name="amount"></td>
			</tr>
			<tr align="center">
				<th>Select the mode</th>
				<td><select name="mode" id="mode">
						<option value="RTGS">RTGS</option>
						<option value="IMBS">IMBS</option>
						<option value="NEFT">NEFT</option>
						<option value="UPI">UPI</option>
				</select></td>
			</tr>
			<tr align="center">
				<th>Description</th>
				<td><input type="text" name="description" id="description"></td>
			</tr>
			<tr align="center">
				<th colspan="2"><input type="submit" value="submit"></th>
			</tr>
		</form>
		<tr ><th colspan="2"><a href='beneficiary'>
        <button>
           go back
        </button></th></tr>
	</table>
</body>
</html>