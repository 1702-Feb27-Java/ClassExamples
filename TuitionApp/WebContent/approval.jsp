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
<title>Your Account - Approving</title>
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

<%	int appID = Integer.parseInt(request.getParameter("appID"));
	
	AppDAOImp appDAO = new AppDAOImp();
	
	ArrayList<ApprovalClass> approvals = new ArrayList<ApprovalClass>();
	approvals = appDAO.getApprovalsByAppID(appID);
	request.setAttribute("approvs", approvals);%>
	
<br>
<form action="BackToPending" method="POST">
	<button type="submit" class="btn btn-default">Back</button>
	</form>

<br>

<h2>You've successfully processed the application!</h2>

<table class="table">
	<tr>
		<th>Approval Level</th>
		<th>Approval Status</th>
		<th>Approver ID</th>
		<th>Message</th>
	</tr>
	<c:forEach var="appr" items="${requestScope['approvs']}">
		<tr>
			<c:choose>
				<c:when test="${appr.approvalLevel == '1'}"><td>Direct Supervisor</td></c:when>
				<c:when test="${appr.approvalLevel == '2'}"><td>Dept Head</td></c:when>
				<c:otherwise><td>BenCo</td></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${appr.approvalStatus == '1'}"><td>pending</td></c:when>
				<c:when test="${appr.approvalStatus == '2'}"><td>approved</td></c:when>
				<c:otherwise><td>denied</td></c:otherwise>
			</c:choose>
			<td><c:out value="${appr.approverID}" /></td>
			<td><c:out value="${appr.approvalMessage}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>