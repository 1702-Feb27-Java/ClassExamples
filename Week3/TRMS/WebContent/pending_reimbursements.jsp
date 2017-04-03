<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.revature.pojo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<!-- Panel container -->
	<div class="row">
				<!-- Default panel contents -->
				
				<div class="panel-body">

				<!-- Table -->
				
				<table class="table" id="pending-reimb-table">
					<tr>
						<th>ID</th>
						<th>Location</th>
						<th>Cost</th>
						<th>Event</th>
						<th>Date</th>
						<th>Grade</th>
						<th>Waiting for Approval</th>
						<th>Edit</th>
					</tr>
					<!-- Generate table records using JSTL forEach -->
					
					<c:forEach items="${sessionScope.pendingReimbursements}" var="reimbursement">
						<!-- iterate over each reimbursement -->
						<tr class="clickable-row">
							<td>${reimbursement.getReimbId()}</td>
							<td>${reimbursement.getLocation()}</td>
							<td>${reimbursement.getCost()}</td>
							<td>${reimbursement.getEventTitle()}</td>
							<td>${reimbursement.getEventDate()}</td>
							<td>${reimbursement.getGrade()}</td>
							<td>${reimbursement.getAppLevelTitle()}</td>
							<c:if test="${reimbursement.getAppLevel() == 6}">
								<td><a href="ReimbursementServlet">Edit</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				</div>
	</div> <!-- end of panel -->
</body>
</html>