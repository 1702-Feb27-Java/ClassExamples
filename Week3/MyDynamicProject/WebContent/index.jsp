<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Example</title>

<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Pacifico">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lemonada">
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open Sans">


<style>

h1{
	font-family: 'Lemonada', serif;
}
body {
  font-family: 'Open Sans', serif;
  font-size: 20px;
  padding-left: 80px;
  padding-right: 80px;
}
</style>
</head>
<body>
	<%! 
	public String someMethod(){
		return "Method called.";
	}
	%>
	
	<% if(request.getParameter("uname")!=null){
		session.setAttribute("name", request.getParameter("uname"));
	}%>
	
	<% if(session.isNew()) {%>
	<h1>Welcome to the JSP Page.</h1>
	<% }else { %>
	<h1>Welcome  
	<%= session.getAttribute("name") %>
	<% } %>
	</h1>
	
	
	<form action="index.jsp" method="POST">
		<p>Please enter your name</p>
		<input type="text" name="uname">
		<input type="submit" value="Submit Name">
	</form>
	
	<hr>
	<p>This is just some text.</p>
	<% 
		int i;
		for (i = 5; i < 10; i++){
			out.print(i);
			out.print("<br>");
		}
	%>
	<br>
	
	<%-- outputs a method onto your page --%>
	<%= someMethod() %>
	<br><br>
	
	<%-- jsp include appends the other file's CODE and runs when the index.jsp runs --%>
	<%-- <jsp:include page="index.html"></jsp:include> --%>
	
	<%-- directive brings the other file precompiled --%>
	<br>
	<%-- <%@include file="index.html" %> --%>
</body>
</html>