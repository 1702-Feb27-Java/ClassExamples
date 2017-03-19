<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.Integer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<style>
	
	label{display:inline-block; width:150px; text-align: right;}
	input, select{display:table-cell; width:200px}
</style>
</head>
<body>
	<h1 align="center">Create New TRMS Account</h1>
	<div align="center"><form action="NewUserServlet" method="POST">
		<p><label>First Name:</label><input type="text" name="fname" required></p>
		<p><label>Last Name:</label><input type="text" name="lname" required></p>
		<p><label>Username:</label><input type="text" name="uname" required></p>
		<p><label>Password:</label><input type="password" maxlength=16 name="password" required></p>
		<p><label>Re-Enter Password:</label><input type="password" name="password1" required></p>
		<p><label>Email:</label><input type="text" name="email" required></p>
		<p><label>Re-Enter Email:</label><input type="text" name="reemail" required></p>
		<p><label>Role:</label>
		<select name="role" required>
			<option disabled selected value> -- select an option -- </option>
			<option value="4">Employee</option>
  			<option value="3">Direct Supervisor</option>
  			<option value="2">Department Head</option>
  			<option value="1">Benefits Coordinator</option>
		</select></p>
		<p><label>Department:</label>
		<select name="dept" required>
			<option disabled selected value> -- select an option -- </option>
			<option value="1">Development</option>
  			<option value="2">Management</option>
  			<option value="3">Projects</option>
  			<option value="4">Security</option>
			<option value="5">Testing</option>
		</select></p>
		<p><label>Supervisor Name:</label><input type="text" name="supname" required></p><br>
		<input type="submit" value="Submit Name">
		</div>
	</form>
</body>
</html>