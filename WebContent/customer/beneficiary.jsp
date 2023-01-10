<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="accountsnavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>

<c:forEach var="beneficiary" items="${customer.beneficiaryList}">
<form action="paybeneficiary" method="post">
<c:out value="${beneficiary.value.nickName}"></c:out>
<input type="hidden" value="${beneficiary.key}" id="accountNumber" name="accountNumber">
<input type="submit" value="go">
</form>
</c:forEach>
</body>
</html>