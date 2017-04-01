<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revature TRMS</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Oleo Script">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Source Code Pro">

<link rel="stylesheet" href="CSS/styles1.css">

</head>
<body>
	<div class="page-header">
	<h1>Revature Internal Application</h1>
	<h2>Tuition Reimbursement Management System</h2></div>
	
	<% if (request.getAttribute("error") != null) 
	out.println(request.getAttribute("error"));
	%>
	<br><br>
	<form action="LoginServlet" method="POST">
		<div class="row">
			<div class="col-xs-2">
				<label for="usr">Username:</label> 
				<input type="text" class="form-control" name="usr">
			</div>
			<div class="col-xs-2">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" name="pwd">
			</div>
		</div>
		
		<br><button type="submit" class="btn btn-default">Login</button>
	</form>
	
	<hr>
	<p>
		Are you a new employee? <a href="signup.jsp">Sign up here.</a>
	</p>


</body>
</html>