<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dashboard</title>
</head>
<body>
Welcome to your dashboard ${username}

<form id="applyreimbursement" action = "reimbursement.jsp" method = "POST">
	<input type = "hidden" value = "${username }">
	<button id="reqreimb" type = "submit">Request Reimbursement</button>
</form>

<form id="viewpending" action = "GeneratePending" method = "POST">
	<input type = "hidden" value = "${username }">
	<button id="viewpend" type = "submit">View Pending Reimbursements</button>
</form>

</body>
</html>