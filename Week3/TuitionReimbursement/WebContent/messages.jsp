<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@include file="header.jsp" %>
	<body>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Messages</h1>
	
		    <ul>
			      <c:forEach items="${messageList}" var="message">
			      	<option value=${message}>
			      		<li>From: ${message.messager} --- ${message.message}</li>
			      </c:forEach>
		     </ul>
		
	
	</body>
</html>