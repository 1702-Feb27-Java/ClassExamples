<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@include file="header.jsp" %>
	<body>
		<br><br>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Request more information</h1><br>
		<div class="row text-center">
			<div class="col-sm-4"></div>
			<div class="col-md-4">
				<form action="SendMessageServlet.do" method="POST">
				  <div class="form-group">
				    <label for="event">Employee: </label>
				    <input type="text" required class="form-control" id="event" placeholder="${empName}" name="event" disabled>
				    <input type="hidden" id="empMessage" name="empMessage" value="${empMessage}" style="visibility:hidden;">
				  </div>
				  <div class="form-group">
				    <input type="text" required class="form-control" id="message" placeholder="Message body" name="message">
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Send</button>
				</form><br>


				<br><br><br><br>
				</div>
			<div class="col-sm-4"></div>
		</div>
		
	
	</body>
</html>