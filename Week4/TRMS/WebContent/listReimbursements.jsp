<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reimbursements</title>
<%@ include file="fragments/cdn.jsp" %>
</head>
<body>
<%@ include file="fragments/nav.jsp" %>
<form action="NewReimbursement">
	<input type="submit" class="btn btn-default" value = "Create new reimbursement"/>
</form>
<h2> Your Reimbursements</h2>
<table class = "table table-striped">
<thead>
<tr>
	<th>Reimbursement ID</th>
	<th>Event Name </th>
	<th>Date of Request </th>
	<th>Status</th>
	<th></th>
</tr> 
</thead>
<tbody>
<c:forEach items="${list}" var = "item">
<tr>
	<td><c:out value="${item.getReimbursementId()}" /></td>
	<td><c:out value="${item.getDescription() }"/></td>
	<td><c:out value="${item.getDateOfRequest()} " /></td>
	<td><c:out value="${item.getStatus().getStatusName()}"/></td>
	<td>
		<form action="EditReimbursement" >
			<input type="hidden" name="id" value = "${item.getReimbursementId()}"/>
			<input type="submit" class="btn btn-default" value="View/Edit"/>	
		</form>
	</td>
</tr>
</c:forEach>
</tbody>

</table>
<!-- Reimbursements To Approve -->

<hr/>
<h2> Reimbursement To Approve</h2>
<table id="reimburse-to-approve-table" class = "table table-striped">

<thead>
<tr>
	<th>Reimbursement ID</th>
	<th>User</th>
	<th>Event Name </th>
	<th>Date of Request </th>
	<th>Start Date</th>
	<th>Status</th>
	<th> </th>
</tr> 
</thead>
<tbody>
<c:forEach items="${toApprove}" var ="item">
<tr class = ${item.isUrgent() ? "warning" : ""} }>
	<td><c:out value="${item.getReimbursementId()}" /></td>
	<td><c:out value="${item.getUser().getUsername()}"/></td>
	<td><c:out value="${item.getDescription() }"/></td>
	<td><c:out value="${item.getDateOfRequest()} " /></td>
	<td><c:out value="${item.getStartDate()}"/>	
		<c:if test="${item.isUrgent()}">
			<span class="glyphicon glyphicon-alert"></span>
		</c:if>
	</td>
	<td><c:out value="${item.getStatus().getStatusName()}"/></td>
	<td>


	</td>
	<td>
		<form action="EditReimbursement" >
			<input type="hidden" name="id" value = "${item.getReimbursementId()}"/>
			<input type="submit" class="btn btn-default" value="View/Edit" />	
		</form>
	</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>