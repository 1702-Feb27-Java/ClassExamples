<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.revature.pojo.*" %>
	<%@ page import="com.revature.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply for a reimbursement.</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Oleo Script">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Source Code Pro">

<link rel="stylesheet" href="CSS/styles1.css">
</head>
<body>

<div class="page-header">
	<h1>Tuition Reimbursement Management System</h1>

<%! UserClass thisUser = new UserClass(); %>
<% thisUser = (UserClass)session.getAttribute("userInfo"); %>

	<% if (thisUser.getDeptID() == 1) {%>
	<h2>Marketing</h2>
	<% } %>
	
	<% if (thisUser.getDeptID() == 2) {%>
	<h2>Human Resources</h2>
	<% } %>
	
	<% if (thisUser.getDeptID() ==3) {%>
	<h2>Benefits Coordination</h2>
	<% } %>
	
	<% if (thisUser.getRoleID()==1){ %>
	<h2>Employee Portal</h2>
	<% } %>
	
	<% if (thisUser.getRoleID()==2){ %>
	<h2>Supervisor Portal</h2>
	<% } %>
	
	<% if (thisUser.getRoleID()==3) { %>
	<h2>Department Head Portal</h2>
	<% } %>
</div>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="empaccount.jsp">Home</a></li>
  <li role="presentation"><a href="appstatus.jsp">Application Status</a></li>
  <li role="presentation" class="active"><a href="application.jsp">New Application</a></li>
  
	<% if (thisUser.getDeptID() == 3) {%>
  	<li role="presentation"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<li role="presentation"><a href="approvedapps.jsp">View Approved Apps</a></li>
  		<% } else { %>
  
     <% if (thisUser.getRoleID() == 2) {%>
  		<li role="presentation"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<% } %>
  
  	<% if (thisUser.getRoleID() == 3) {%>
  		<li role="presentation"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<% } %>
  
   <% } %>
  
  <li role="presentation"><form action="LogOutServlet" method="POST">
	<button type="submit" class="btn btn-default">Logout</button>
	</form></li>

</ul>
	<br><br>
	<form action="ApplicationServlet" method="POST">
	
		<b>What type of course are you taking?</b> <br> <br>
		<div id=selectmenus>
		<select class="form-control" name="event">
			<option>University Courses</option>
			<option>Seminars</option>
			<option>Certification Prep Classes</option>
			<option>Certification</option>
			<option>Technical Training</option>
			<option>Other</option>
		</select> </div>
		<br>
		
		<div class="row">
			<div class="col-xs-2">
				<label for="inputStartDate">Start date:</label> <input type="text"
					class="form-control" name="startdate" placeholder="mm-dd-yyyy">
			</div>
			
			<div class="col-xs-2">
				<label for="inputEndDate">End date:</label> <input type="text"
					class="form-control" name="enddate" placeholder="mm-dd-yyyy">
			</div>
			
			<div class="col-xs-2">
				<label for="hoursPerWeek">Hours Per Week:</label> <input type="text"
					class="form-control" name="hours">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-xs-2">
				<label for="inputLoc">Location:</label> <input type="text"
					class="form-control" name="location">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-xs-2">
				<label for="inputCost">Total Cost:</label> <input type="text"
					class="form-control" name="cost">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-xs-2">
				<label for="inputJust">Justification:</label> <input type="text"
					class="form-control" name="justification" placeholder="Limit to 250 characters">
			</div>
		</div>
		<br>
		
		<b>Grading format:</b> <br> <br>
		<div id=selectmenus>
		<select class="form-control" name="gradingFormat">
			<option>Pass/Fail</option>
			<option>A-F</option>
			<option>Percentage</option>
			<option>Presentation</option>
		</select> </div>
		<br>
		
		<div class="row">
			<div class="col-xs-2">
				<label for="inputCutoff">Grade Cutoff:</label> <input type="text"
					class="form-control" name="gradeCutoff" placeholder="Enter passing grade">
			</div>
		</div>
		
		<br>
		<h2>Please click the button to complete your
		application. </h2><br> 
		<button type="submit" class="btn btn-default">Apply</button>

		<!-- to a registration complete page, which redirects to the main page after 5 secs -->
		</p>
	</form>


</body>
</html>
