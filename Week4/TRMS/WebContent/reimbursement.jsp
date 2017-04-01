<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Reimbursement</title>

<style>

input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
 
input[type="number"] {
    -moz-appearance: textfield;
}

</style>
</head>
<body>
	<%
 		if(session.getAttribute("loggedIn")==null){
 			response.sendRedirect("logIn.jsp");
 		}
 	
 	%> 



	<form class="form-horizontal" action = "Reimbursement" method ="POST">
		<fieldset>

			<!-- Form Name -->
			<legend>Request Reimbursement ${username}</legend>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="coursetype">Course
					Type</label>
				<div class="col-md-4">
					<select id="coursetype" name="coursetype" class="form-control">
						<option value="1">University Course</option>
						<option value="2">Seminar</option>
						<option value="3">Certification Preparation Class</option>
						<option value="4">Certification</option>
						<option value="5">Technical Training</option>
						<option value="6">Other</option>
					</select>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="startdate">Start
					Date</label>
				<div class="col-md-4">
					<input id="startdate" name="startdate" placeholder="mm-dd-yyyy"
						class="form-control input-md" required type="text">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="enddate">End Date</label>
				<div class="col-md-4">
					<input id="enddate" name="enddate" placeholder="mm-dd-yyyy"
						class="form-control input-md" required type="text">

				</div>
			</div>
			

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="cost">Cost</label>
				<div class="col-md-4">
					<input id="cost" name="cost" placeholder="0.00"
						class="form-control input-md" required type="number" step = ".01" min = "0">
				</div>
			</div>
			
			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="requestamount">Requested Amount</label>
				<div class="col-md-4">
					<input id="requestamount" name="requestamount" placeholder="0.00"
						class="form-control input-md" required type="number" step = ".01" min = "0">
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="location">Location</label>
				<div class="col-md-4">
					<input id="location" name="location" placeholder="Address"
						class="form-control input-md" required type="text">

				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="gradeformat">Grading
					Format</label>
				<div class="col-md-4">
					<select id="gradeformat" name="gradeformat" class="form-control">
						<option value="1">Letter Grade</option>
						<option value="2">Percentile</option>
						<option value="3">Pass/Fail</option>
						<option value="4">Presentation</option>
					</select>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="description">Description</label>
				<div class="col-md-4">
					<textarea class="form-control" id="description" name="description"
						placeholder="Description of the event. Max 2000 characters."></textarea>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="workjust">Work
					related justification</label>
				<div class="col-md-4">
					<textarea class="form-control" id="workjust" name="workjust"
						placeholder="Explain why this event is necessary for work. Max 2000 characters."></textarea>
				</div>
			</div>
			
			<input id="currentdate" name ="currentdate" type ="hidden" value = "somevalue" >
			<input id="currenttime" name ="currenttime" type ="hidden" value = "somevalue" >
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit">Submit
					Request </label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary" type ="submit">Submit</button>
				</div>
			</div>

		</fieldset>
	</form>
	
		<div id ="wrongInputs" >
	<ul>
	<% if (session.getAttribute("enddatetooshort") != null && (boolean)session.getAttribute("enddatetooshort") == true){
	%>
		<li>The end date entered is before the start date.</li>
	<% session.setAttribute("enddatetooshort", null);
	} %>
	<% if (session.getAttribute("invalidstartdate") != null && (boolean)session.getAttribute("invalidstartdate") == true){
	%>
		<li>The start date is too close to the current date</li>
	<% session.setAttribute("invalidstartdate", null);
	} %>
	<% if (session.getAttribute("notenoughfunds") != null && (boolean)session.getAttribute("notenoughfunds") == true){
	%>
		<li>You do not have the available funds to make the requested amount</li>
	<%session.setAttribute("notenoughfunds", null);
	} %>
	<% if (session.getAttribute("requesttoomuch") != null && (boolean)session.getAttribute("requesttoomuch") == true){
	%>
		<li>The ammount requested exceeds the reimbursement allowance for the selected course type</li>
	<%session.setAttribute("requesttoomuch", null);
	} %>
	
	
	</ul>
	</div>

	<script>
		$(function() {
			$('#startdate').datepicker({
				dateFormat : "mm-dd-yy"
			});
		});

		$(function() {
			$('#startdate').keypress(function(event) {
				event.preventDefault();
				return false;
			});
		});

		$(function() {
			$('#enddate').datepicker({
				dateFormat : "mm-dd-yy"
			});
		});
		$(function() {
			$('#enddate').keypress(function(event) {
				event.preventDefault();
				return false;
			});
		});

		
		jQuery(document).ready( function($) {
			
			var datetime = new Date();
			var currDate = (datetime.getMonth()+1) + "/" + datetime.getDay() + "/" + datetime.getFullYear();
			var currTime = datetime.getHours() + ":" + datetime.getMinutes();
			document.getElementById("currentdate").value = currDate;
			document.getElementById("currenttime").value = currTime;
			 
		    // Disable scroll when focused on a number input.
		    $('form').on('focus', 'input[type=number]', function(e) {
		        $(this).on('wheel', function(e) {
		            e.preventDefault();
		        });
		    });
		 
		    // Restore scroll on number inputs.
		    $('form').on('blur', 'input[type=number]', function(e) {
		        $(this).off('wheel');
		    });
		 
		    // Disable up and down keys.
		    $('form').on('keydown', 'input[type=number]', function(e) {
		        if ( e.which == 38 || e.which == 40 )
		            e.preventDefault();
		    });  
		      
		});
	</script>


</body>
</html>