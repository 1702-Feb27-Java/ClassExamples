<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<body>

	<form id = "logIn" class="form-horizontal" action = "Login" method = "POST">
		<fieldset>

			<!-- Form Name -->
			<legend>Login</legend>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="">Username</label>
				<div class="col-md-4">
					<input name="username" type="text"
						class="form-control input-md" required>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="">Password</label>
				<div class="col-md-4">
					<input name="password" type="password"
						class="form-control input-md" required>

				</div>
			</div>

			

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for=""></label>
				<div class="col-md-8">
					<button type ="submit" form ="logIn" class="btn btn-warning">Login</button>
				</div>
			</div>

		</fieldset>
	</form>

	<ul>
	<%
		if (session.getAttribute("loggedIn") != null) {
			if ((boolean) session.getAttribute("loggedIn") == false) {
	%>
		<li>Incorrect username or password!</li>
	<%
		session.setAttribute("loggedIn", null);
	%>
	<%
		}
		}
	%>
	
	</ul>
</body>
</html>