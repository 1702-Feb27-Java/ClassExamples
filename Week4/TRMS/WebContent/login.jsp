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
<div class = "container">

<form action = "Login" method="post">
	<div id = "login" class = "container">
		<div class = "row">
		<div class="form-group col-sm-6">
			<label>Username</label><input type="text" class="form-control"
				name="username" />
		</div>
		<div class="form-group col-sm-6">
			<label>Password</label><input type="password" class="form-control"
				name="password" />
		</div>
		</div>
		<button type="login" class="btn btn-default">Submit</button>
	</div>
</form>
</div>
</body>
</html>