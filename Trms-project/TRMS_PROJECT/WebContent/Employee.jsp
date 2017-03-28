<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Page</title>
</head>
<body>
	<h1>Welcome Employee, Please fill out the form below for your reimbursetment</h1>
	
	<!-- The form below will be taken to another servlet that deals with putting in the information into the reimbursement pojo and table-->
	<form>
			Location of Course: <input type = "text" name = "loca"><br>
			Today's Date: <input type = "date" name = "Current_date"><br>
			Start Date of course: <input type ="date" name="start_date"><br>
			End Date of course: <input type = "date" name="end_date"><br> 
			length of Course(In minutes): <input type = "time" name="course_length"><br>
			Course Type: <input type = "text" name="course_type"><br>
			Grade: <input type="text" name="actual_grade"><br>
			<input type = "submit" value="Submit form">
	
	
	
	</form>


</body>
</html>