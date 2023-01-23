<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%-- <%@include file="navbar.jsp" %> --%>
<meta charset="ISO-8859-1">

<title>Dash Board</title>
</head>
<%if(session.getAttribute("account")!=null){
	session.removeAttribute("account");}%>
<style>
	table{
	width:100%;}
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
	<%=session.getAttribute("status")==null?"":session.getAttribute("status") %>
	<%if((String)session.getAttribute("status")!=null)session.removeAttribute("status");%>
<%-- 
	<c:out value="${customer.profile}"></c:out><br>
 --%>
</body>
</html>