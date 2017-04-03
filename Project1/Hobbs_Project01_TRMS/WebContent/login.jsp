<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TRMS</title>
	<!-- CSS -->
	<!-- bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
		integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- fontawesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- app -->
	<link rel="stylesheet" href="css/main.css">
	<!-- /.CSS -->
</head>
<body class="non-user">

	<div class="container" id="login">
	
		<h1>TRMS</h1>
	
		<h2>Sign in to continue</h2>
		
		<div class="panel panel-default">
			<div class="panel-body">
				<form action="login.do" method="POST">
					<p>
					<label>Username:<br>
					<input type="text" name="username" placeholder="Username">
					</label>
					</p>
					<p>
					<label>Password:<br>
					<input type="password" name="password" placeholder="Password">
					</label>
					</p>
					<input class="btn btn-primary" type="submit" value="Sign in">
				</form>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- /.JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>