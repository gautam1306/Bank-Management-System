<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Employee Login</title>
<html>
<h1>Customer Login</h1>
<%response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
response.setHeader("Pragma", "no-cache");
  response.setDateHeader ("Expires", 0);
	%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action="EmployeeLogin" method="post">
        <table border="1" width="30%" cellpadding="3">
            <thead>
                <tr>
                    <th colspan="2" align ="left">Login Here</th><%=request.getAttribute("errorMessage")==null?"":request.getAttribute("errorMessage") %>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Employee ID</td>
                    <td><input type="text" name="employeeId" id="employeeId" /></td>
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
    </form>
</body>