<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@include file="header.jsp" %>
	<body>
		<br><br>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Reimbursement Form</h1><br>
		<div class="row text-center">
			<div class="col-sm-4"></div>
			<div class="col-md-4">
				<form action="SubmitReimbursement.do" method="POST" enctype="multipart/form-data">
				  <div class="form-group">
				    <label for="event">Event Name</label>
				    <input type="text" required class="form-control" id="event" placeholder="Event" name="event">
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
			                <input type="text" required class="form-control" name="eventDate" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			            <div class="col-sm-3"></div>
			        </div>
			    </div>	
		</div>
			
			<script>
			$(document).ready(function() {
			    $('#dateRangePicker')
			        .datepicker({
			            format: 'mm/dd/yyyy',
			            startDate: '01/01/2010',
			            endDate: '12/30/2020'
			        })
			        .on('changeDate', function(e) {
			            // Revalidate the date field
			            $('#dateRangeForm').formValidation('revalidateField', 'date');
			        });
			});
			</script>
		
				  <div class="form-group">
				    <label for="time">Event time</label>
				    <input type="text" required class="form-control" id="time" placeholder="Time" name="time">
				  </div>
				  <div class="form-group">
				    <label for="location">Event Location</label>
				    <select class="form-control" id="locationSelect" name="location">
				      <c:forEach items="${locationsList}" var="location">
				      	<option value=${location}>
				      		${location}
				      </c:forEach>
				      	<option value="none" id="none">none of the above</option>
				    </select>
				  </div>
				  <div class="row" id="newLocation" style="display:none">
				  	<div class="col-sm-3">New location: </div>
				  	<div class="col-lg-9">
				  		<div class="form-group">
				    		<input type="text" class="form-control" placeholder="Location" name="location2">
				    	</div>
				  	</div>
				  </div>
				 
				  
				  <script type="text/javascript">
					   $('#locationSelect').bind('change', function(event) {
									
						           var i= $('#locationSelect').val();
						         
						           $('#newLocation').hide();
						           console.log(i);
						           
						            if(i=="none") // equal to a selection option
						             {
						                 $('#newLocation').show();
						              	 
						             }
						           else if(i!="none")
						             {
						               $('#newLocation').hide(); // hide the first one
						              }
						});
				   </script>
				   
				  <div class="form-group">
				    <label for="description">Event description</label>
				    <input type="text" required class="form-control" id="description" placeholder="Description" name="description"> 
				  </div>
				  <div class="form-group">
				    <label for="cost">Event cost</label>
				    <input type="number" required class="form-control" id="cost" placeholder="Cost" name="cost">
				  </div>
				  <div class="form-group">
				    <label for="gradingType">Grading Scale</label>
				    <select class="form-control" id="gradingType"  name="gradingId">
				      <c:forEach items="${gradingTypes}" var="gradingType">
				      	<option value="${gradingType}">
				      		${gradingType}
				      </c:forEach>
				      	<option value="none">none of the above</option>
				    </select>
				  </div>
				  <div class="row" id="newGrade" style="display:none">
				  		<div class="row">
						  	<div class="col-sm-3">New Grading Scale: </div>
						  	<div class="col-lg-9">
						  		<div class="form-group">
						    		<input type="text" class="form-control" id="newGradingInput" placeholder="Grading Scale"  name="gradingId2">
						    	</div>
						  	</div>
					  	</div>
					  	<div class="row">
						  	<div class="col-sm-3">Passing Grade: </div>
						  	<div class="col-lg-9">
						  		<div class="form-group">
						    		<input type="text" class="form-control" id="passingGrade" placeholder="Passing Grade"  name="passingGrade">
						    	</div>
						  	</div>
					  	</div>
				  </div>
				  
				  <script type="text/javascript">
					   $('#gradingType').bind('change', function(event) {
									
						           var i= $('#gradingType').val();
						         
						           $('#newGrade').hide();
						           
						            if(i=="none") // equal to a selection option
						             {
						                 $('#newGrade').show();
						              	 
						             }
						           else if(i!="none")
						             {
						               $('#newGrade').hide(); // hide the first one
						              }
						});
				   </script>
				  
				  	<div class="form-group">
				    <label for="eventType">Event Type</label>
				    <select class="form-control" required id="eventType" name="typeOfEvent">
				      <c:forEach items="${eventTypes}" var="eventType">
				      	<option value="${eventType}">
				      		${eventType}
				      </c:forEach>
				    </select>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputFile">File input</label>
				    <p>Filename:<br><input type="text" name="filename"></p>
				    <input type="file" class="form-control-file" id="file" aria-describedby="fileHelp" name="file">
				    <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
				  </div>
				  <button type="submit" class="btn btn-primary">Submit</button>
				</form><br>
				<script>
					boolean submitResult = (boolean)request.getAttribute("submitResult");
					var node;
				    if (submitResult == true) {
				    	node = document.getElementById("success");
				    } else {
				    	node = document.getElementById("failure");
				    }
				    node.style.visibility = 'visible';
				</script>
				
				<div id="success" class="alert alert-success" style="visibility: hidden">
				  <strong>Success! Reimbursement request submitted.</strong>
				</div>
				<div id="failure" class="alert alert-danger" style="visibility: hidden">
				  <strong>Failure! Reimbursement request failed.</strong>
				</div>

				<br><br><br><br>
				</div>
			<div class="col-sm-4"></div>
		</div>
		
	
	</body>
</html>