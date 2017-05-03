<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script><meta charset="ISO-8859-1">
<script src="costs.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Event</title>
<style>
	label{display:inline-block; width:150px; text-align: right; 
	vertical-align: top;padding-right: 5px; font-size:18px;font-weight:bold}
	input, select{display:inline-block; width:200px; padding-left: 5px;}
	textarea{display:inline-block; width:500px; padding-left: 5px;}

</style>
</head>
<body>
	<h1 align="center">Create New Reimbursement Request</h1>
	<fieldset style="width:700px; margin:auto">
	<div><form action="CreateEventServlet" method="POST">
		<p><label>Event Type:</label>
			<select id="etype" name="etype" required>
				<option disabled selected> -- select an option -- </option>
				<option value="1">University Course</option>
	  			<option value="2">Seminar</option>
	  			<option value="3">Certification Prep Course</option>
	  			<option value="4">Certification</option>
				<option value="5">Technical Training</option>
	  			<option value="6">Other</option>
			</select><font size="2" color="blue">&ensp;<b>*NOTE:IF OTHER, EXPLAIN IN JUSTIFICATION*</b></font></p>
			<p><label>Description:</label><textarea rows="5" cols="60" name="descr"></textarea></p>
			<p><label>Grading Format:</label>
			<select name="grade" required>
				<option disabled selected value> -- select an option -- </option>
				<option value="1">Pass/Fail</option>
	  			<option value="2">Grades A - F</option>
	  			<option value="3">Presentation</option>
	  			<option value="4">Other</option>
			</select><font size="2" color="blue">&ensp;<b>*NOTE:IF OTHER, EXPLAIN IN JUSTIFICATION*</b></font></p>
			<p><label>Start Date:</label><input type="date" name="start" required></p>
			<p><label>Start Time:</label><input type="text" name="stime" required></p>
			<p><label>Stop Date:</label><input type="date" name="stop" required></p>
			<p><label>Location:</label><input type="text" name="location" required></p>
			<p style="display: inline;"><label>Cost: $</label><input type="text" id="cost" name="cost" style="width:40px"required>
			<p id="saved" style="width:100px; display: inline; margin-left:105px"></p>
			<p id="oop" style="width:140px; display: inline; margin-left:55px"></p>
			<p><label>Justification:</label><textarea rows="5" cols="60" name="justify"></textarea></p>
			<input type="submit" value="Submit Event">
			</form>		
		</div>
	</fieldset><br>
</body>
</html>