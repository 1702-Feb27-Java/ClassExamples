<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script><!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Home Page</title>
</head>
<body>
	
	<!-- Space reserved for header -->
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
	</div>
	<!-- Breadcrumb container -->	
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<ul class="breadcrumb">
				  <li><a href="#">Home </a></li>
				  <span class="arrow sep">►</span>
				  <li class="active">Login</li>
				</ul>
			</div>
		</div>
	</div>
	<br>
	<div class="well">
		This is in request.getParameter(username)
		<%= request.getParameter("username") %>
		<br>
		This is in request.getParameter(password)
		<%= request.getParameter("password") %>
		<br>
		This is in session.getAttribute(firstname)
		<%= session.getAttribute("firstname") %>
		<br>
		This is in session.getAttribute(lastname)
		<%= session.getAttribute("lastname") %>
		<br>
		This is in session.getAttribute(employee)
		<%= session.getAttribute("employee") %>
	</div>
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-6">
		</div>
		<div class="col-md-4">
			<div class="btn-group btn-group-vertical">
			  <a href="ReimbursementServlet" class="btn btn-default">Apply for a New Reimbursement</a>
			  <a href="#" class="btn btn-default">View Pending Reimbursements</a>
			  <a href="#" class="btn btn-default">View Awarded Reimbursements</a>
			</div>
		</div>
	</div>
	<br>
	<hr>
	<div class="row">
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>