<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import='Main.Main'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>T.R.M.S</title>
</head>
<body>



	<h1>Welcome to the T.R.M.S. Login page</h1>
	<hr>
	<form action="login" method="POST">

		Username <input type="text" name="uname"> 
		Password <input type="password" name="pwd">

		<!-- 			User Status<br> -->
		<!-- 		<input type="radio" name="myradio" value="button1">	Employee 
		<input type="radio" name="myradio" value="button2"> Supervisor 
		<input type="radio" name="myradio" value="button3"> departmentHead 
		<input type="submit" value="Login"> -->
		<!-- 	<select name="choice">
			<br>
			<option value = ""></option>
			<option value="Employee">Employee</option>
			<option value="Supervisor">Supervisor</option>
			<option value="Department head">Department head</option>
			<option value="BenCo.">BenCo.</option>
			
			
			</select>	 -->

		<input type="submit" value="Login">

	</form>
	<%
		Main main = new Main();
		out.print(main.toString());
	%>
</body>
</html>