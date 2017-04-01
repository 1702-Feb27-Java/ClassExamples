<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Dashboard</title>
	</head>
	
	<body style="background-color:orange">
		<h1 align="center" style="color:blue">Welcome to your Dept. Head dashboard!</h1><hr><br>
		<h2 align="center" style="color:blue">Please select an option below:</h2><br>
		
		<form align="center" action="MakeReimbursementPage.html" style="color:blue">
			<input type="submit" name="reqButton" value="Request a Reimbursement"><br>
		</form>
		<form align="center" action="MyReimServlet.do" style="color:blue">
			Please enter your password to continue:<br>
			<input type="password" name="password" id="password"><br>
			<input type="submit" name="myReim" id=2 value="View My Reimbursement"><br>
		</form>
		<form align="center" action="ViewPendingServlet.do" style="color:blue" method="POST">
			View Pending Reimbursements:<br>
			Please enter your Department ID<br>
			<input type="text" name="deptid" id="deptid"><br>
			<input type="submit" value="Pending Reimbursements"><br>
		</form>
	</body>
</html>