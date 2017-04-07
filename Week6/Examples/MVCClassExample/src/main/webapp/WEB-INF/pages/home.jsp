<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.beans.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"        prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"        prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"    prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Example | Home</title>
<link rel="stylesheet" href="static/css/styles.css">
</head>
<body>
	<h1>Home</h1>
	<div>
		<label class=for>
			Hi, my name is ${user.getUsername()}.
			${someInfo}<br>
			${alsoUser }
		</label>
	</div>
</body>
</html>