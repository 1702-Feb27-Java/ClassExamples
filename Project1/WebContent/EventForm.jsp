<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Event</title>
<style>
	
	label{display:inline-block; width:150px; text-align: right; vertical-align: top;padding-right: 5px;}
	input, select{display:inline-block; width:200px; padding-left: 5px;}
	textarea{display:inline-block; width:500px; padding-left: 5px;}
</style>
</head>
<body>
	<h1 align="center">Create New Reimbursement Request</h1>
	<div><form action="CreateEventServlet" method="POST">
	<p><label>Event Type:</label>
		<select name="etype" required>
			<option disabled selected> -- select an option -- </option>
			<option value="1">University Course</option>
  			<option value="2">Seminar</option>
  			<option value="3">Certification Prep Course</option>
  			<option value="4">Certification</option>
			<option value="5">Technical Training</option>
  			<option value="6">Other</option>
		</select><font size="2" color="red">&ensp;*Note: Other, explain in Justification</font></p>
		<p><label>Description:</label><textarea rows="5" cols="60" name="descr"></textarea></p>
		<p><label>Grading Format:</label>
		<select name="grade" required>
			<option disabled selected value> -- select an option -- </option>
			<option value="1">Pass/Fail</option>
  			<option value="2">Grades A - F</option>
  			<option value="3">Presentation</option>
  			<option value="4">Other</option>
		</select><font size="2" color="red">&ensp;*Note: Other, explain in Justification</font></p>
		<p><label>Start Date:</label><input type="text" name="start" required></p>
		<p><label>Start Time:</label><input type="text" name="stime" required></p>
		<p><label>Stop Date:</label><input type="text" name="stop" required></p>
		<p><label>Location:</label><input type="text" name="location" required></p>
		
		<p><label>Cost:</label><input type="text" name="cost" required></p>
		
		<p><label>Justification:</label><textarea rows="5" cols="60" name="justify"></textarea></p>
		</div>
		<input align="right" type="submit" value="Submit Event">
		
	</form>
</body>
</html>