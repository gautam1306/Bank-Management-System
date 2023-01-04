<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@include file="customernavbar.jsp" %>


<html>
<style>
table, th, td {
	  border: 1px solid;
	  margin-left: auto;
	  margin-right: auto;
	  border-color: #3399ff;
	  
	}
</style>
<head>

<meta charset="ISO-8859-1">

<title>My Profile</title>
</head>
<body>
<table>
<tr><th>Name </th><td> ${customer.profile.name}</td></tr>
<tr><th>Mobile Number</th><td>${customer.profile.mobileNumber}</td></tr>
<tr><th>Date of Birth</th> <td>${customer.profile.dateofBirth}</td></tr>
<tr><th>Aadhaar Number</th><td>${customer.profile.aadhaarNumber}</td></tr>
<tr><th>Pan </th><td>${customer.profile.panCardCode}</td></tr>
<tr><th>Gender </th><td>${customer.profile.gender}</td></tr>
<tr><th>Address</th> <td>${customer.profile.address}</td></tr>
</table></body>
</html>