<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Open Fixed Deposit</title>
</head>
<style>
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
<body>
	<table>
		<form action="openFixedDepositServlet" onsubmit="return verifyForm()">
			<tr align="center">
				<th>Investment Account</th>
				<td><select name="account" id="account">
						<c:forEach var="account" items="${customer.accounts}">
							<option value="${account.value.accountNumber}">"${account.key}"</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Enter the Amount of Investment</th>
				<td><input type="number" id="amount" name="amount"
					required="required"></td>
			</tr>
			<tr>
				<th>Enter the duration of investment</th>
				<td><input type="number" id=duration name="duration"
					required="required"></td>
			</tr>
			<tr>
				<th colspan=2><input type="submit" value="submit"></th>
			</tr>
		</form>
	</table>
	<script>
		function verifyForm() {
			// Get the value of the input field with id="numb"
			let x = document.getElementById("amount").value;
			// If x is Not a Number or less than one or greater than 10
			let text;
			if (isNaN(x) || x < 1000) {
				alert("The amount should be greater than 999");
				return false;
			}
			x = document.getElementById("duration").value;
			if (isNaN(x) || x > 100) {
				alert("The time duration should be less than or equal to 100");
				return false;
			}
			if (x < 1) {
				alert("duration cannot be negative");
				return false;
			}
		}
	</script>
</body>
</html>