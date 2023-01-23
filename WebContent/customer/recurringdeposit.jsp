<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.bank.customer.Customer"%>
<!DOCTYPE html>
<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid;
	margin-left: auto;
	margin-right: auto;
	border-color: #3399ff;
}

.button {
	background-color: #3399ff;
}
</style>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recurring Deposit</title>
</head>
<%
	if (session.getAttribute("recurringDeposit") == null) {
		int depositID = Integer.parseInt(request.getParameter("depositID"));
		Customer customer = (Customer) session.getAttribute("customer");
		if (!customer.getRecurringDeposit().containsKey(depositID)) {
			response.sendRedirect("error.jsp");
		}
		session.setAttribute("recurringDeposit", customer.getRecurringDeposit().get(depositID));
	}
%>
<body>
	<table>
		<tr>
			<th colspan="2">Recurring Deposit</th>
		</tr>
		<tr>
			<th>Deposit ID</th>
			<td>${recurringDeposit.depositid}</td>
		</tr>
		<tr>
			<th>Monthly Investment Amount</th>
			<td>${recurringDeposit.amount}</td>
		</tr>
		<tr>
			<th>Total Amount Invested</th>
			<td>${recurringDeposit.depositamount}</td>
		</tr>
		<tr>
			<th>Total Interest</th>
			<td>${recurringDeposit.interestamount}</td>
		</tr>
		<tr>
			<th>Maturity Date</th>
			<td>${recurringDeposit.maturityDate}</td>
		</tr>
		<tr>
			<th>Account Associated with Deposit</th>
			<td>${recurringDeposit.accountnumber}</td>
		</tr>
		<tr>
			<th>Intrest Rate</th>
			<td>${recurringDeposit.interestrate}%</td>
		</tr>
		<tr>
		<th>Click the Button for
					premature withdrawal</th>
			<td colspan="2"><form action="recurringDepositWithdrawal"
					method="get" onsubmit="return validateForm()">
					<input type="hidden" value=${recurringDeposit.depositid
						}
						name="depositID" id="depositID"> <input type="submit" value="withdraw">
				</form></td>
		</tr>
	</table>
	<script>
		function validateForm() {
			if (confirm("Press OK to Withdraw the funds from the Depsoit") == true) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>