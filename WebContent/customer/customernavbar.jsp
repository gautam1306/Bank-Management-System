<!DOCTYPE html>
<html>
<head>	
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #3399ff;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: white;
	color: black;
}
</style>
</head>
<body>

	<div class="topnav">
		<a id="dashboard" href="customer-home">Home</a> 
		<a id="pay" href="pay">Pay</a>
		<a id="invest" href="invest">Save</a> <a id="loan" href="loan">Barrow</a>
		<a id="profile" href="profile">Profile</a>
		<%
			if (request.getRequestURI().equals(request.getContextPath() + "/customer-home"))
				out.print("<a href=\"logout\">Logout</a>");
		%>
	</div>
</body>
</html>