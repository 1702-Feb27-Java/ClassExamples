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
  	<li role="presentation" class="active"><a href="pendingapps.jsp">View Pending Apps</a></li>
  		<% } else { %>
  
     <% if (thisUser.getRoleID() == 2) {%>
  		<li role="presentation" class="active"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<% } %>
  
  	<% if (thisUser.getRoleID() == 3) {%>
  		<li role="presentation" class="active"><a href="pendingapps.jsp">View Pending Apps</a></li>
  	<% } %>
  
   <% } %>
  

  <li role="presentation"><form action="LogOutServlet" method="POST">
	<button type="submit" class="btn btn-default">Logout</button>
	</form></li>

</ul>


<% AppDAOImp appDAO = new AppDAOImp(); 
	int flag = 0;
	ArrayList<AppClass> pending = new ArrayList<AppClass>();
	ArrayList<AppClass> bPending = new ArrayList<AppClass>();
	int apprLvl = 0;
	boolean check = userDAO.checkForSuper(thisUser.getDeptID());
	System.out.println(check);
	
	// if this user is in depts other than benco
	if (thisUser.getDeptID() == 1 || thisUser.getDeptID() == 2){
		if (thisUser.getRoleID() == 2){ // if supervisor
			apprLvl = 1;
			pending = appDAO.getPendingAppsByManager(apprLvl, 1, thisUser);
			bPending = pending;
		}
		else if (thisUser.getRoleID() == 3){ // if department head
			if (check == false){ // no supers, we go down one approval level
				apprLvl = 1;  // look for apps on supervisor approval level
				pending = appDAO.getPendingAppsByManager(apprLvl, 1, thisUser);
				bPending = pending;
				flag = 1;
			}
			else { // otherwise
				apprLvl = 2;
				pending = appDAO.getPendingAppsByManager(apprLvl, 1, thisUser);
				bPending = pending;
			}
		}
	} else { // if BenCo
		if (thisUser.getRoleID() == 2){ // if benco supervisor
			apprLvl = 3;
			bPending = appDAO.getPendingAppsByBenco(thisUser);
			pending = appDAO.getPendingAppsByManager(apprLvl-2, 1, thisUser);
			int i = 0;
			for (AppClass ac : pending){
				bPending.add(pending.get(i));
				i++;
			}
		}
		else if (thisUser.getRoleID() == 3){ // if benco department head
			if (check == false){ // no supers, we go down one approval level
				apprLvl = 3;
				bPending = appDAO.getPendingAppsByBenco(thisUser);
				pending = appDAO.getPendingAppsByManager(apprLvl-2, 1, thisUser);
				int i = 0;
				for (AppClass ac : pending){
					bPending.add(pending.get(i));
					i++;
				}
				flag = 1;
			}
			else { // otherwise
				apprLvl = 3;
				bPending = appDAO.getPendingAppsByBenco(thisUser);
				pending = appDAO.getPendingAppsByManager(apprLvl-1, 1, thisUser);
				int i = 0;
				for (AppClass ac : pending){
					bPending.add(pending.get(i));
					i++;
				}
			}
		}
	
		apprLvl = 3;
		bPending = appDAO.getPendingAppsByBenco(thisUser);
	}
	
	request.setAttribute("pending", bPending);
	session.setAttribute("flag", flag); %>

<br>
<p>There are a total of <%= pending.size() %> pending app(s) to be reviewed.</p>
<br>
<table class="table">
	<c:forEach var="pend" items="${requestScope['pending']}">
	
		<tr>
		<th>User ID</th>
		<th>App ID</th>
		<th>Priority</th>
		<th>Date Created</th>
		<th>Status</th>
		<th>Event Type</th>
		<th>Cost</th>
		<th>Justification</th>
		<th>Attachments</th>
		<th>Reimbursement</th>
		<th colspan="2">Actions</th>
		</tr>
		<tr>
			<td><c:out value="${pend.userID}" /></td>
			<td><c:out value="${pend.appID}" /></td>
			<td><c:out value="${pend.dateCreated}" /></td> 
			<c:choose>
				<c:when test="${pend.statusID == '1'}"><td>active</td></c:when>
				<c:otherwise><td>inactive</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pend.priority == '1'}"><td>normal</td></c:when>
				<c:otherwise><td>urgent</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pend.eventID == '1'}"><td>University Courses</td></c:when>
				<c:when test="${pend.eventID == '2'}"><td>Seminars</td></c:when>
				<c:when test="${pend.eventID == '3'}"><td>Certification Prep.</td></c:when>
				<c:when test="${pend.eventID == '4'}"><td>Certification</td></c:when>
				<c:when test="${pend.eventID == '5'}"><td>Technical Training</td></c:when>
				<c:otherwise><td>Other</td></c:otherwise>
			</c:choose>
			<td><c:out value="${pend.totalCost}" /></td>
			
			<td><c:out value="${pend.justification}" /></td>
			
			<td><form action="Attachments" method="POST"><input name="appID" type="hidden" value="${pend.appID}"></input>
			<button type="submit" class="btn btn-link">View</button></form></td>
			
			<td><form action="ReimbAsManager" method="POST"><input name="appID" type="hidden" value="${pend.appID}"></input>
			<button type="submit" class="btn btn-link">Check</button></form></td>
			
			<td><form action="ToApprove" method="POST">
			Message (limit to 250 chars):
			<textarea class="form-control" rows="3" name="message"></textarea>
			<br>
			<input name="appID" type="hidden" value="${pend.appID}"></input>
			<button type="submit" class="btn btn-default">Approve</button></form>
			
			<form action="Deny" method="POST">
			Message (limit to 250 chars):
			<textarea class="form-control" rows="3" name="message"></textarea>
			<br>
			<input name="appID" type="hidden" value="${pend.appID}"></input>
			<button type="submit" class="btn btn-default">Deny</button></form>
			</td>
			
			<td><form action="AddInfo" method="POST">
			Message (limit to 250 chars):</label>
			<textarea class="form-control" rows="3" name="info"></textarea>
			<br>
			<input name="appID" type="hidden" value="${pend.appID}"></input>
			<button type="submit" class="btn btn-default">Request Info</button></form></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>