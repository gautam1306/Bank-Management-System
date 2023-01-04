<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><%@include file="customernavbar.jsp" %>
<head>
<meta charset="ISO-8859-1">
<title>Loans</title>
</head>
<style>
	table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
	.button{
		background-color: #3399ff;
	} 
</style>

<body>
<%=request.getAttribute("status")==null?"":request.getAttribute("status") %>
<%request.setAttribute("status",null);
System.out.println(request.getParameter("loanID"));
%>
<table><tr>
<th>Loan ID</th>
<th>Loan Type</th>
<th>Payable Amount</th>
<th>Interest Amount</th>
<th>Loan Amount</th>
<th></th>
</tr>
<c:forEach var = "loan" items = "${customer.loans}" >
<tr>
<td>${loan.value.loanID}</td>
<td>
${loan.value.loanType}</td>
<td>
<c:out value="${loan.value.payableAmount}"></c:out><br>
</td>
<td>${loan.value.interestPayable}</td>
<td>${loan.value.loanAmount}</td>
<td>
<form action="repayment" method="post">
<input type="hidden" id="loanID" name="loanID" value="${loan.key}">
<input type="Submit" class="button" value= "GO">
</form>
</td>
</tr></c:forEach>
	</table>
</body>
</html>