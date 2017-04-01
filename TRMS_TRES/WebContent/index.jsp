<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="head.html" %>
		<title>Login Page</title>
	</head>
	<body>
		<form action="Login" method="POST">
  			<div class="form-group">
    			<label for="uname">Username</label>
    			<input type="text" class="form-control" id="uname" placeholder="Username" name="uname" >
  			</div>
  			<div class="form-group">
    			<label for="exampleInputPassword1">Password</label>
    			<input type="password" class="form-control" id="passwd" placeholder="Password" name="password" >
  			</div>
  			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</body>
</html>