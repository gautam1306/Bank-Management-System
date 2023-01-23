<!DOCTYPE html>
<html>
<head>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('div a[href="'+location.href.split("/").at(-1)+'"]').addClass('active');
});

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
<%response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
response.setHeader("Pragma", "no-cache");
  response.setDateHeader ("Expires", 0);
	%>
<div class="topnav">
<a id="dashboard"href="account">Accounts</a>
<a id="transactionhistory"href="transactionhistory">Statement</a>
<a id="accountview"href="accountview">Show Account Details</a>
<a id="customer-home"href="customer-home"> Dashboard</a>	
</div>
</body>
</html>
