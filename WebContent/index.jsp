<!DOCTYPE html>
<%@include file="customernavbar.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Customer Login</title>
<html>
<h1>Customer Login</h1>
<%session.invalidate();

%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="customer" method="post">
        <center>
        <table border="1" width="30%" cellpadding="3">
            <thead>
                <tr>
                    <th colspan="2" align ="left">Login Here</th><%=request.getAttribute("errorMessage")==null?"":request.getAttribute("errorMessage") %>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Customer ID</td>
                    <td><input type="text" name="customerID" id="customerID" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" id="password" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" /></td>
                </tr>
            </tbody>
        </table>
        </center>
    </form>
</body>