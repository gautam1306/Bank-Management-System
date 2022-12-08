<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="navbar.html" %>
<title>Customer Login</title>
</head>
<h1>Customer Login</h1>
<body>
<form action="customer" method="post">
	<label for="fname">customer id:</label><br>
  	<input type="text" id="customerID" name="customerID"><br>
  	<label for="password">Password:</label><br>
  	<input type="password" id="password" name="password"><br>
  	<input type="submit" value="Submit">
</form>
</body>
</html>