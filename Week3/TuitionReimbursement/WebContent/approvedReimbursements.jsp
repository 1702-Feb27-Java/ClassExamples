<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style>
		table {
	    	width: 100%;
	    	border: 1px solid black;
		}
		
		th {
		    height: 50px;
		    border: 1px solid black;
		}
		td {
			border: 1px solid black;	
		}
		#subButton {
			border-radius: 25px;
    		border: 2px solid #73AD21;
    		width: 100%;
		}
	</style>
</head>
<body>

	<%@include file="header.jsp" %>
		
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<table id="tableId">
		    <tr>
		        <th>Event</th>
		        <th>Time</th>
		        <th>Description</th>
		        <th>Cost</th>
		        <th>Reimbursement</th>
		        <th>Location</th>
		        <th>Grading Scale</th>
		        <th>Type of Event</th>
		        <th>Approval Step</th>
		        <th>Event Date</th>
		        <th>Date Filled out</th>
		        <th>Add Grade</th>
		    </tr>
		    <c:forEach items="${approvedReimbursements}" var="reimbursement">	
		   <form action="AddGrade.do" method="POST">
			    <tr id="${reimbursement.getReimbId()}">
			    	<input type="hidden" name="reimbId" value="${reimbursement.getReimbId()}" style="visibility:hidden;">
			        <td>
			            <c:out value="${reimbursement.getEvent()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getTime()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getDescription()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getCost()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getProjectedReimbursement()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getLocation()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getGrading()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getTypeOfEvent()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getApprovalStep()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getEventDate()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getFormDate()}" />
			        </td>
			        <td>
			        	<button id="subButton" type="submit">${reimbursement.getReimbId()}</button>
			       	</td>
			    </tr>
		    </form>
		    </c:forEach>
		</table>
		</div>
</div>
	
</body>
</html>



