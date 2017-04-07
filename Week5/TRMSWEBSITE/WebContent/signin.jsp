<%@page import="Employee.SignedInEmployee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


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
<title>Welcome To TRMS</title>

<!-- Bootstrap -->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<body>

<%
if(SignedInEmployee.signedIn == true)
{
	

    response.sendRedirect("Controller");
	
}
%>

		<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
				
			</ul>

		</nav>
		<div class="jumbotron">
			<h1 class="display-3">Sign in to TRMS</h1>
			<form action="Controller" method="POST">
			<%if (SignedInEmployee.failedlogin==true){ %>
			
			
			
			
			<%
			}
			%>
				 Username: <input type="text" name="uname" required = ""> <br> 
				 Password:&nbsp <input type="password" name="pwd" required = ""> <br>
				  &nbsp &nbsp &nbsp 
				  &nbsp &nbsp &nbsp 
				  &nbsp &nbsp &nbsp 
				  &nbsp &nbsp &nbsp 
				  <input	type="submit" value="       Submit       ">
			</form>
		</div>

<% SignedInEmployee.failedlogin = false; %>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>