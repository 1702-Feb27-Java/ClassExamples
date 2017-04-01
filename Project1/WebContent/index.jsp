<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRMS Login Screen</title>
<style>
	body {background-color:#002266; color:#ebebe0}
	p {font-size:35px;}
	hr{color:#ebebe0;size:2px}
</style>
</head>
<body link=white vlink=white alink=white>
	<div>
	<% if (session.isNew()){ %>
		<h1 align="center"><font size=35>Welcome to ... TRMS!</font></h1>
	<% } else { %>
		<h1 align="center"><font size=35>Welcome to ... TRMS!</font></h1>
		<h3 align="center" style="color:red"># Invalid Username / Password, please try again.</h3>
	<% } %>
	</div>
 	<div align="center"><form action="ValidateLogin" method="POST">
		<p>Username:
		<input type="text" name="uname"></p>
		<p>Password:
		<input type="password" name="password"></p>
		<a href="NewUser.jsp">Create New Account</a><br><br>
		<a href="NewUser.jsp">Forgot Username / Password</a><br><br>
		<input type="submit" value="Log In">
	</form></div>	
</body>
</html>