<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="com.revature.pojo.*" %>
<%@ page import="com.revature.dao.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Account</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>

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

<%! UserClass thisUser = new UserClass(); %>
<% thisUser = (UserClass)session.getAttribute("userInfo"); %>

<%! AppClass app = new AppClass(); 
	AppDAOImp appDAO = new AppDAOImp(); 
	ArrayList<Integer> numberApps = new ArrayList<Integer>();
	ArrayList<AppClass> appsByUser = new ArrayList<AppClass>();%>

<div class="page-header">
	<h1>Tuition Reimbursement Management System</h1>
	
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
  <li role="presentation" class="active"><a href="appstatus.jsp">Application Status</a></li>
  <li role="presentation"><a href="application.jsp">New Application</a></li>
  
<% if (thisUser.getDeptID() == 3) {%>
  <li role="presentation"><a href="pendingapps.jsp">View Pending Apps</a></li>
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


<% numberApps = appDAO.getAppIDsByUserID(thisUser); 
	appsByUser = appDAO.getAppsByUserID(thisUser);
	pageContext.setAttribute("appsByUser", appsByUser ); %>
	
<br>
<p>You have a total of <%= numberApps.size() %> app(s).</p>
<br>
<table class="table">
	<tr>
		<th>App ID</th>
		<th>Priority</th>
		<th>Date Created</th>
		<th>Status</th>
		<th>Event</th>
		<th>Location</th>
		<th>Total Cost</th>
		<th>Grading Format</th>
		<th>Justification</th>
		<th>Reimbursement</th>
		<th>Approval Status</th>
		<th colspan="2">Action</th>
	</tr>
	<% int i = 0; %>
	<c:forEach var="app" items="${appsByUser}">
		<tr>
			<td><c:out value="${app.appID}" /></td>
			
			<c:choose>
				<c:when test="${app.priority == '1'}"><td>normal</td></c:when>
				<c:otherwise><td>urgent</td></c:otherwise>
			</c:choose>
			
			<td><c:out value="${app.dateCreated}" /></td>
			<c:choose>
				<c:when test="${app.statusID == '1'}"><td id="activeCheck">active</td></c:when>
				<c:otherwise><td id="activeCheck">inactive</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${app.eventID == '1'}"><td>University Courses</td></c:when>
				<c:when test="${app.eventID == '2'}"><td>Seminars</td></c:when>
				<c:when test="${app.eventID == '3'}"><td>Certification Prep.</td></c:when>
				<c:when test="${app.eventID == '4'}"><td>Certification</td></c:when>
				<c:when test="${app.eventID == '5'}"><td>Technical Training</td></c:when>
				<c:otherwise><td>Other</td></c:otherwise>
			</c:choose>
			<td><c:out value="${app.loc}" /></td>
			<td><c:out value="${app.totalCost}" /></td>
			<td><form action="Grading" method="POST"><input name="appID" type="hidden" value="${app.appID}"></input>
			<button type="submit" class="btn btn-link">Check</button></form></td>
			<td><c:out value="${app.justification}" /></td>
			<td><form action="Reimbursement" method="POST"><input name="appID" type="hidden" value="${app.appID}"></input>
			<button type="submit" class="btn btn-link">Check</button></form></td>
			<td><form action="Approvals" method="POST"><input name="appID" type="hidden" value="${app.appID}">
			<input name ="active" type="hidden" value="${app.statusID}">
			<button type="submit" class="btn btn-link">Check</button></form></td>
			<td>
			<!-- View the attachments -->
			<form action="ViewFiles" method="POST"><input name="appID" type="hidden" value="${app.appID}"></input>
			<button type="submit" class="btn btn-link">View Files</button></form>
			</td>
			
			<td>
			
			<!-- Upload attachments -->
			<form action="ToFile" method="POST"><input name="appID" type="hidden" value="${app.appID}"></input>
			<button type="submit" class="btn btn-link">File Upload</button></form></td>
			
		</tr>
	</c:forEach>
</table>


</body>
</html>