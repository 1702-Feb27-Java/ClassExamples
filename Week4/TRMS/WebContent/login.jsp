<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@ include file="fragments/cdn.jsp" %>
</head>
<body>
<%@ include file="fragments/nav.jsp" %>
<form action = "Login" method="post">
	<div id = "login">
		<div class="input-group" id="username-input">
			<input type="text" class="form-control" name = "username" placeholder= "Username"/>
		</div>
		<div class="input-group" id="username-input">
			<input type="password" class="form-control" name = "password" placeholder= "Password"/>
		</div>
		<button type="login" class="btn btn-default">Submit</button>
	</div>
</form>

</body>
</html>