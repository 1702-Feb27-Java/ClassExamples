<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


	<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
	<%@include file="header.jsp"%>
	<body>	
		<%{
			request.setAttribute("uId", request.getAttribute("id"));
			
		}%>
		
		<br><br><br>
		
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Reimbursement Form</h1><br>
		<div class="row text-center">
			<div class="col-sm-4"></div>
			<div class="col-md-4">
				<form action="SubmitEditServlet.do" method="POST" enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="event">Event Name</label>
				    <input type="text" required class="form-control" id="event" placeholder="Event" name="event" disabled value="${reimbursement.getEvent()}">
				  </div>
				  
					<div class="row">
						    <div class="form-group">
						    	<div class="row">
						    		<div class="col-sm-3"></div>
						        	<label class="col-md-6 control-label">Event Date</label>
						        	<div class="col-sm-3"></div>
						        </div>
						        <div class="row">
						    		<div class="col-sm-3"></div>
						            <div class="col-md-6 input-group input-append date" id="dateRangePicker">
						                <input type="text" required class="form-control" name="eventDate"  disabled value="${reimbursement.getEventDate()}"/>
						                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
						            </div>
						            <div class="col-sm-3"></div>
						        </div>
						    </div>	
					</div>
		
				  <div class="form-group">
				    <label for="time">Event time</label>
				    <input type="text" required class="form-control" id="time" placeholder="Time" name="time" disabled value="${reimbursement.getTime()}">
				  </div>
				  <div class="form-group">
				    <label for="time">Event Location</label>
				    <input type="text" required class="form-control" id="locationSelect" placeholder="Time" name="location" disabled value="${reimbursement.getLocation()}">
				  </div>
				  <div class="form-group">
				    <label for="description">Event description</label>
				    <input type="text" required class="form-control" id="description" placeholder="Description" name="description" disabled value="${reimbursement.getDescription()}"> 
				  </div>
				  <div class="form-group">
				    <label for="cost">Event cost</label>
				    <input type="number" required class="form-control" id="cost" placeholder="Cost" name="cost" disabled value="${reimbursement.getCost()}">
				  </div>
				  <div class="form-group">
				    <label for="cost">Reimbursement</label>
				    <input type="number" required class="form-control" id="reimbursementAmt" placeholder="Reimbursement" name="reimbursementAmt" disabled value="${reimbursement.getProjectedReimbursement()}">
				  </div>
				  <div class="form-group">
				    <label for="time">Grading Scale</label>
				    <input type="text" required class="form-control" id="gradingScale" placeholder="gradingScale" name="gradingScale" disabled value="${reimbursement.getGrading()}">
				  </div>
				  <div class="form-group">
				    <label for="time">Event Type</label>
				    <input type="text" required class="form-control" id="eventType" placeholder="eventType" name="eventType" disabled value="${reimbursement.getTypeOfEvent()}">
				  </div>
				  <div class="form-group">
				    <label for="time">Final Grade</label>
				    <input type="text" required class="form-control" id="finalGrade" placeholder="Final Grade" name="finalGrade" disabled value="${reimbursement.getFinalGrade()}">
				  </div>
				  <div class="form-group" style="visibility:hidden;">
				    <label for="time">Reimb id</label>
				    <input type="text" required class="form-control" id="reimbId" placeholder="reimbId" name="reimbId" value="${reimbursement.getReimbId()}">
				  </div>
				  <c:forEach items="${attachments}" var="attachment">
				   <div class="row">
					   <div class="col-md-1">
					   		<a style="text-align:left; font-size: 30px;" href="${attachment.getLink()}">${attachment.getName()}</a><br>
					   </div>
					   <div class="col-md-11"></div>
				   </div>
				   </c:forEach><br>
		
				</form><br>
		
				</div>
			<div class="col-sm-4"></div>
		</div>
		<br><br>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
			  	<form action="ConfirmApproveServlet.do" method="POST">
			  		<button name="edit" value="approve" type="submit" class="btn btn-default btn-lg">Approve Reimbursement</button>
			  	</form>
			</div>
			<div class="col-md-3">
				<form action="ConfirmApproveServlet.do" method="POST">
					<button name="edit" value="decline" type="submit" class="btn btn-default btn-lg">Decline Reimbursement</button><br>
					<label for="reason">Reason for Decline</label>
					<input type="text" required class="form-control" id="reason" placeholder="Reason" name="reason">
			  	</form>
			</div>
			<div class="col-md-3">
				<form action="ConfirmApproveServlet.do" method="POST">
			  		<button name="edit" value="request" type="submit" class="btn btn-default btn-lg">Request more information</button>
			  	</form>
			</div>
		</div>
=
				<br><br><br><br>
	
	</body>
</html>