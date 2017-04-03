<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		<form align="center" action="MakeReimbursementPage.jsp" style="color:blue">
			<input type="submit" name="reqButton" value="Request a Reimbursement"><br>
		</form>
		<form align="center" action="MyReimServlet.do" style="color:blue">
			<input type="hidden" name="password" value="${user.password}" id="password"><br>
			<input type="submit" name="myReim" id=2 value="View My Reimbursement"><br>
		</form>
		<form align="center" action="ViewPendingServlet.do" style="color:blue" method="POST">
			<input type="hidden" name="deptid" value="${user.dept_id}" id="deptid"><br>
			<input type="submit" value="Pending Reimbursements"><br>
		</form>
		<form align="center" action="LogoutServlet.do" style="color:blue">
			<input type="submit" name="logout" value="Logout"><br>
		</form>
	</body>
</html>