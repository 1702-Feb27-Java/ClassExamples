<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	
	<% if(request.getParameter("uname")!=null){
		session.setAttribute("username", request.getParameter("uname"));	
	}%>
	<% if(request.getParameter("pword")!=null){
		session.setAttribute("password", request.getParameter("pword"));	
		request.setAttribute("id", null);
	}%>
	
	<% if(session.isNew()){ %>
	<h1>HElloO PlEASE number 1</h1>
	<%} else { %>
	<h1>HEllooopleaseO number <%= session.getAttribute("username") %></h1>
	<%} %>
	<form action="TestServlet.do" method="POST">
		<p>Username</p>
		<input type="text" name="uname">
		<p>Password</p>
		<input type="text" name="pword">
		<input type="submit" name="Submit login">
	</form>
	


	

</body>
</html>