<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Customer</title>
</head>
<body>
<table>
<form action="addCustomerServlet" method="get">
<tr><th>Customer Name</th><td><input type="text"id="customerName" name="customerName"></td></tr>
<tr><th>Aadhaar Number</th><td><input type="number" id="aadhaarNumber" name="aadhaarNumber"> </td></tr>
<tr><th>Date of Birth</th><td><input type="date" id="dob" name="dob"></td></tr>
<tr><th>Mobile Number</th><td><input type="number" id="mobileNumber" name="mobileNumber"></td></tr>
<tr><th>Pan Card</th><td><input type="text" id="pan" name="pan"></td></tr>
<tr><th>Gender</th><td><select name="gender" id="gender"><option value="male">male</option><option value="female">female</option></select></td>
<tr><th>Address House Number</th><td><input type="text" id="address_house" name="address_house"></td></tr>
<tr><th>Address Street</th><td><input type="text" id="address_street" name="address_street"></td></tr>
<tr><th>Address District</th><td><input type="text" id="address_district" name="address_district"></td></tr>
<tr><th>Address State</th><td><input type="text" id="address_state" name="address_state"></td></tr>
<tr><th>Address Pin Code</th><td><input type="number" id="address_pincode" name="address_pincode"></td></tr>
<tr><th><input type="submit" value="submit">
</form>
</table>
</body>
</html>