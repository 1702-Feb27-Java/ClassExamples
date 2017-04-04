<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REVA-TRMS Login</title>
</head>
<body>
	<h1> Welcome! please login to TRMS</h1><br>
	<form action="LoginServ.do" method="POST">
		Username
		<input type="text" name="username"><br><br>
		Password
		<input type="password" name="pwd"><br><br>
		<input type="submit" value="login">		
	 </form>
	 <br>
	 <%if(request.getParameter("username") != null){
		session.setAttribute("username", request.getParameter("username"));
		
		}%>
		<%if(session.getAttribute("username") != null) {%>
		<%="error username/password combination not found"%>
		
		<%} %>
		
		
		
		
</body>
</html>