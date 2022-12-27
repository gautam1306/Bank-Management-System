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

<c:forEach var="beneficiary" items="${account.beneficiaryList}">
<form action="beneficiarytransfer" method="post">
<c:out value="${beneficiary.value.nickName}"></c:out>
Enter the Amount:<input type="number" name="amount">
<input type="hidden" value="${beneficiary.key}" id="accountNumber" name="accountNumber">
<input type="text" name="description" id="description">
  <label for="mode">Choose a mode of transaction:</label>
  <select name="mode" id="mode">
    <option value="RTGS">RTGS</option>
    <option value="IMBS">IMBS</option>
    <option value="NEFT">NEFT</option>   
  </select>
<input type="submit" value="go">
</form>
</c:forEach>
</body>
</html>