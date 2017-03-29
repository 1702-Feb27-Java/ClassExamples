<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@include file="header.jsp" %>
	<body>
		<br><br>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Reimbursement Form</h1><br>
		<h5>Denial reason: ${reimbursement.}</h5>
		<div class="row text-center">
			<div class="col-sm-4"></div>
			<div class="col-md-4">
				<form>
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
				    <label for="time">Grading Scale</label>
				    <input type="text" required class="form-control" id="gradingScale" placeholder="gradingScale" name="gradingScale" disabled value="${reimbursement.getGrading()}">
				  </div>
				  <div class="form-group">
				    <label for="time">Event Type</label>
				    <input type="text" required class="form-control" id="eventType" placeholder="eventType" name="eventType" disabled value="${reimbursement.getTypeOfEvent()}">
				  </div>
				</form><br>

				<br><br><br><br>
				</div>
			<div class="col-sm-4"></div>
		</div>
		
	
	</body>
</html>