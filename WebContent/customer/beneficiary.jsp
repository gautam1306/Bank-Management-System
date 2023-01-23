<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<table>
<tr><th>Nick Name</th><th></th></tr>
<c:forEach var="beneficiary" items="${customer.beneficiaryList}">
<tr>
<td align="center"><c:out value="${beneficiary.value.nickName}"></c:out></td>
<td align="center">
<form action="paybeneficiary" method="post">
<input type="hidden" value="${beneficiary.key}" id="accountNumber" name="accountNumber">
<input type="submit" value="select">
</form>
</td>
</tr>
</c:forEach>
</table>
<c:if test="${beneficiary.isEmpty()}"><h2>Sorry no Previous records have been registered in this account</h2></c:if>
</body>
</html>