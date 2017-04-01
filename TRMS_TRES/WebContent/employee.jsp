<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="head.html" %>
	<%@ include file="navbar.jsp" %>
		<title>Employee Menu</title>
	</head>
	<body>
		<h1> Welcome <%= session.getAttribute("uname")%> </h1>
		<h1> Reports to <%= session.getAttribute("repto")%> </h1>
		<h1> Reports to id <%= session.getAttribute("repid")%> </h1>
		<hr>
			
	</body>
</html>