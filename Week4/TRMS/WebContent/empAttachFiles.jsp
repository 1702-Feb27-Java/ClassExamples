<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attach additional info</title>
</head>
<body>

	<p>
	Message from ${addinfofrom}
	<%= session.getAttribute("addinfomessage") %>
	</p>

	<form action="AdditionalFiles" method="POST">
		Select Files: <input type="file" name="addfiles" multiple> 
		<br><input type="submit" name = "additionalfiles" value="">
	</form>


</body>
</html>