<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Dashboard</title>
	</head>
	
	<body style="background-color:orange">
		<h1 align="center" style="color:blue">Welcome to your dashboard!</h1><hr><br>
		<h2 align="center" style="color:blue">Please select an option below:</h2><br>
		
		<form align="center" action="MakeReimbursementPage.html" style="color:blue">
			<input type="submit" name="reqButton" value="Request a Reimbursement"><br>
		</form>
		<form align="center" action="MyReimServlet.do" style="color:blue">
			Please enter your password to continue:<br>
			<input type="password" name="password" id="password"><br>
			<input type="submit" name="myReim" id=2 value="View My Reimbursement"><br>
		</form>
		<form align="center" style="color:blue">
			<input type="button" name="reqButton" id=3 value="View Reimbursements For Your Department"><br>
		</form>
		
	</body>
</html>