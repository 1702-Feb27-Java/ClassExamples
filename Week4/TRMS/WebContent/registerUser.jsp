<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register New User</title>

<head>
	<%@ include file="fragments/cdn.jsp" %>
</head>
<body>
<%@ include file="fragments/nav.jsp" %>
	<form action="RegisterUser" method="post">
		<div id="register"> <!-- class="container" --> 
			<div class="input-group" id="username-input">
				<input type="text" class="form-control" name = "username" placeholder= "Username"/>
			</div>
			<div class = "input-group" id ="password-input">
				<input type="password" class="form-control" name="password" placeholder = "Password"/>
			</div>
			<div class = "input-group" id ="confirm-password-input">
				<input type="password" class="form-control" name="confirm-password" placeholder = "Confirm Password"/>
			</div>
			<div class = "input-group" id ="first-name-input">
				<input type="text" class="form-control" name="fname" placeholder = "First Name"/>
			</div>
			<div class = "input-group" id ="last-name-input" >
				<input type="text" class="form-control" name="lname" placeholder = "Last Name"/>
			</div>
			<div class = "input-group" id ="role-id-input" >
				<input type="number" class="form-control" name="role" placeholder = "Role"/>
			</div>
			<div class = "input-group" id ="dept-id-input" >
				<input type="number" class="form-control" name="dept" placeholder = "Dept"/>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</form>
</body>
</html>