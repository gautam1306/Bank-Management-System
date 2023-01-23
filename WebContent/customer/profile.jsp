<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@include file="customernavbar.jsp" %>


<html>
<script>var a=0;
function show()
{
    if(a==1){
    	a=0;
        document.getElementById("form").style.display="none";
    }
    else{
    	a=1;
        document.getElementById("form").style.display="block";
    }
}
</script>
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
<tr><th><button onclick="show()">CHANGE PASSWORD</button></th></tr>
</table></body>
<form  name="myForm" id="form" onsubmit="return  formatCheck({28:1,29:7})" action="changePasswordServlet"  method="post"  style="display:none;">
<tr><th>ENTER THE OLD-PASSWORD :<input type="text" name="oldPassword"><br></th></tr>
<tr><th>ENTER THE NEW-PASSWORD :<input type="text" name="newPassword"><br></th></tr>
<p style="font-size: 12px;" >* THE PASSWORD [8 - 15] CHARACTERS MUST ATLEAT HAVE A 1 UPPERCASE , 1 LOWERCASE ,1 NUMBER AND 1 SPECIAL CHARACTER</p>
<input type="submit" value="CONFIRM">
</html>