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
		<div class="container-fluid well span6">
			<div class="row-fluid">
		        <div class="span2" >
				    <img src="http://wraggelabs.com/static/images/default_avatar-2.gif" class="img-circle">
		        </div>
				<h1> Welcome: <%= session.getAttribute("uname")%> </h1>
				<h4> Reports to: <%= session.getAttribute("repto")%> </h4>
				<h4> Reports to id: <%= session.getAttribute("repid")%> </h4>
				<h3> Pending Amount: <%= session.getAttribute("pendAmnt")%> </h3>
				<h3> Awarded Amount: <%= session.getAttribute("awardAmnt")%> </h3>
				<h3> Remaining Amount: <%= session.getAttribute("balance")%> </h3>
			</div>
		</div>		
	</body>
</html>