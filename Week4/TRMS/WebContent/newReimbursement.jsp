<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="database.service.Service"%>
<%
	//request.setAttribute("entryMapDept", Service.getInstance().getDepts()); 
	request.setAttribute("entryMapEventType", Service.getInstance().getEventType());
	request.setAttribute("entryMapGradeFormat", Service.getInstance().getGradeFormats());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="fragments/cdn.jsp"%>
</head>
<body>
<%@ include file="fragments/nav.jsp" %>

	<form action="NewReimbursement" method="POST">
		<div class="form-group">
			<label>Description</label> 
			<input type="text" name="reimburse-desc"
				id="reimburse-desc" 
				class = "form-control"  />
		</div>

		<div class="form-group">
			<label>Location</label> 
			<input type="text" name="reimburse-loc"
				id="reimburse-loc" 
				class = "form-control" />
		</div>
		<div class="${callback-cost-missing != null ? 'form-group has-error' : 'form-group'}">
			<label>Cost Of Event</label> 
			<input type="number"
				name="reimburse-cost" id="reimburse-cost"
				oninput="updateReimbursementField()" 
				class = "form-control"  />
		</div>
		<!--  label>Grade Format</label><select>
	<c:forEach items="${entryMapDept}" var = "dept">
		<option value = "${dept.getKey()}"><c:out value="${dept.getValue().getDeptName()}"/></option>
	</c:forEach>
</select> -->
		<div class="form-group">
			<label>Event Type</label> <select id="reimburse-event-type"
				name="reimburse-event-type" onchange="updateReimbursementField()">
				<c:forEach items="${entryMapEventType}" var="eventType">
					<option data-coverage="${eventType.getValue().getCoverage()}"
						value="${eventType.getKey()}"><c:out
							value="${eventType.getValue().getTypeName()}" /></option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label>Grade Format</label><select id = "reimburse-grade-format" name="reimburse-grade-format"
				onchange="showCustomGradingFormat()">
				<c:forEach items="${entryMapGradeFormat}" var="gradeFormat">
					<option value="${gradeFormat.getKey()}"><c:out
							value="${gradeFormat.getValue().getFormatName()}" /></option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group" id="customGradeFormatDiv">
			<label>Custom Grading Format</label> 
			<input type="text"
				name="custom-grading-format" id="reimburse-custom-format"
				class = "form-control" />
		</div>
		<div class="form-group">
			<label>Start Date</label>
			<input type="date"
				name="reimburse-date-start" id="reimburse-date-start"
				class = "form-control"  />
		</div>
		<div class="form-group">
			<label>End Date</label>
			<input type="date" name="reimburse-date-end"
				id="reimburse-date-end" class = "form-control"  />
		</div>
		<div class="form-group">
			<label>Predicted Reimbursement</label> 
			<input class = "form-control" 
				data-max-amount="${maxReimbursement}" type="number"
				name="reimburse-reimbursement" id="reimburse-reimbursement" readonly />
		</div>
		<button type="submit" value="Apply">Apply</button>
	</form>

	<script src="js/newReimbursements.js">
		
	</script>
</body>
</html>