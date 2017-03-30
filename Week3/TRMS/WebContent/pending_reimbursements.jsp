<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.revature.pojo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Panel container -->
	<div class="row">
				<!-- Default panel contents -->
				
				<div class="panel-body">

				<!-- Table -->
				
				<table class="table">
					<tr>
						<th>ID</th>
						<th>Location</th>
						<th>Cost</th>
						<th>Event</th>
						<th>Date</th>
						<th>Grade</th>
						<th>Status</th>
					</tr>
					<!-- Generate table records using JSTL forEach -->
					
					<c:forEach items="${sessionScope.pendingReimbursements}" var="reimbursement">
						<!-- iterate over each reimbursement -->
						<tr>
							<td>${reimbursement.getReimbId()}</td>
							<td>${reimbursement.getLocation()}</td>
							<td>${reimbursement.getCost()}</td>
							<!-- TODO: Event Title Id to Event Title String -->
							<td>${reimbursement.getEventTitleId()}</td>
							<td>${reimbursement.getEventDate()}</td>
							<!-- TODO: Event Grade Id to Event Grade Format String -->
							<td>${reimbursement.getGradeId()}</td>
							<!-- TODO: App Level Id to Pending/Approved/Declined (Hide pending level?) -->
							<td>${reimbursement.getAppLevel()}</td>
							
						</tr>
					</c:forEach>
				</table>
				</div>
	</div> <!-- end of panel -->
</body>
</html>