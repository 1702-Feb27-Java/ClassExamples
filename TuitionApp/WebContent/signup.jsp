<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.revature.pojo.*" %>
<%@ page import="com.revature.dao.*" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Hashtable" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up for a TRMS account.</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Oleo Script">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Source Code Pro">

<link rel="stylesheet" href="CSS/styles1.css">
</head>
<body>

	<h1>Welcome to the TRMS!</h1>
	<h2>Tuition Reimbursement Management System</h2>
	<hr>

	<form action="SignupServlet" method="POST">
		<h2>
			First, tell us about yourself.<br> <br>
		</h2>
		<p class="big">
		<div class="row">
			<div class="col-xs-2">
				<label for="pwd">First Name:</label> <input type="text"
					class="form-control" name="firstname">
			</div>
			<div class="col-xs-2">
				<label for="pwd">Last Name:</label> <input type="text"
					class="form-control" name="lastname">
			</div>
		</div>
		<br>

		<div class="row">
			<div class="col-xs-2">
				<label for="pwd">Username:</label> <input type="text"
					class="form-control" name="username">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" name="password">
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-xs-2">
				<label for="pwd">Email:</label> <input type="text"
					class="form-control" name="email">
			</div>
		</div>
		<br> <b>Which department are you from?</b> <br> <br> 
		<div id=selectmenus>
		<select class="form-control" name="dept">
			<option>Marketing</option>
			<option>Human Resources</option>
			<option>BenCo</option>
		</select> </div>
		<br>
		
		<b>Are you a(n): employee, supervisor, or department head?</b> <br> <br> 
		<div id=selectmenus>
		<select class="form-control" name="role">
			<option>Employee</option>
			<option>Supervisor</option>
			<option>Department Head</option>
		</select> </div>
		<br>
		
		<h2>Please click the button to complete your
		registration. </h2><br> 
		<button type="submit" class="btn btn-default">Register</button>

		<!-- to a registration complete page, which redirects to the main page after 5 secs -->
		</p>
	</form>


</body>
</html>