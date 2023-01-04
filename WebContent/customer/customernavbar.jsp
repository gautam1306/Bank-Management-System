<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('div a[href="' + location.href.split("/").at(-1) + '"]')
						.addClass('active');
			});
	console.log(location.href.split("/").at(-1));
</script>
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
		<a id="dashboard" href="customer-dashboard">Home</a> <a id="pay" href="pay">Pay</a>
		<a id="invest" href="invest">Save</a> <a id="loan" href="loan">Barrow</a>
		<a id="profile" href="profile">Profile</a>
		<a id="transfer" href="transfer">Pay Using the Account Number</a> 
		<a id="beneficiary" href="beneficiary">Pay to a Beneficiary</a> 
		<a id="addbeneficiary" href="add-beneficiary">Add Beneficiary in the same bank</a>
		<%
			if (request.getRequestURI().equals(request.getContextPath() + "/customer-dashboard"))
				out.print("<a href=\"logout\">Logout</a>");
		%>
	</div>
</body>
</html>