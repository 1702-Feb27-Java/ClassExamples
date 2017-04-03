<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="com.revature.pojo.*" %>
<%@ page import="com.revature.dao.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Account - Pending Tasks</title>

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

<%! UserClass thisUser = new UserClass(); 
	UserDAOImp userDAO = new UserDAOImp(); %>
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
  <li role="presentation"><a href="application.jsp">New Application</a></li>
  
	<% if (thisUser.getDeptID() == 3) {%>
  	<li role="presentation"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<li role="presentation" class="active"><a href="approvedapps.jsp">View Approved Apps</a></li>
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


<% AppDAOImp appDAO = new AppDAOImp(); 
	ArrayList<AppClass> approved = new ArrayList<AppClass>();
	approved = appDAO.getApprovedApps();
		
	request.setAttribute("approved", approved); %>

<br>
<p>There are a total of <%= approved.size() %> approved app(s) to be reviewed.</p>
<br>

<% if (approved.size() > 0) { %>
<table class="table table-striped">
<thead class="thead-inverse">
		<tr>
		<th>User ID</th>
		<th>App ID</th>
		<th>Priority</th>
		<th>Date Created</th>
		<th>Status</th>
		<th>Event Type</th>
		<th>Cost</th>
		<th>Justification</th>
		<th>Grading</th>
		<th>Attachments</th>
		<th>Reimbursement</th>
		</tr>
		</thead>
		
		<tbody>
		
		<c:forEach var="approved" items="${requestScope['approved']}">
		<tr>
			<td><c:out value="${approved.userID}" /></td>
			<td><c:out value="${approved.appID}" /></td>
			<td><c:out value="${approved.dateCreated}" /></td> 
			<c:choose>
				<c:when test="${approved.statusID == '1'}"><td>active</td></c:when>
				<c:otherwise><td>inactive</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${approved.priority == '1'}"><td>normal</td></c:when>
				<c:otherwise><td>urgent</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${approved.eventID == '1'}"><td>University Courses</td></c:when>
				<c:when test="${approved.eventID == '2'}"><td>Seminars</td></c:when>
				<c:when test="${approved.eventID == '3'}"><td>Certification Prep.</td></c:when>
				<c:when test="${approved.eventID == '4'}"><td>Certification</td></c:when>
				<c:when test="${approved.eventID == '5'}"><td>Technical Training</td></c:when>
				<c:otherwise><td>Other</td></c:otherwise>
			</c:choose>
			<td><c:out value="${approved.totalCost}" /></td>
			
			<td><c:out value="${approved.justification}" /></td>
			<td><form action="GradingAsBenco" method="POST"><input name="appID" type="hidden" value="${approved.appID}"></input>
			<button type="submit" class="btn btn-link">Check</button></form></td>
			
			<td><form action="ViewFilesAsBenco" method="POST"><input name="appID" type="hidden" value="${approved.appID}"></input>
			<button type="submit" class="btn btn-link">View</button></form></td>
			
			<td><form action="ReimbAsBenco" method="POST"><input name="appID" type="hidden" value="${approved.appID}"></input>
			<button type="submit" class="btn btn-link">Check</button></form></td>
			
		</tr>
	</c:forEach>
	</tbody>
</table>

<% } %>
</body>
</html>