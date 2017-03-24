<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!
		public String someMethod(){
			return "Method called";
		}
	%>
	
	<% if(request.getParameter("uname")!=null){
		session.setAttribute("name", request.getParameter("uname"));	
	}%>
	
	
	<% if(session.isNew()){ %>
	<h1>HElloO PlEASE number 1</h1>
	<%} else { %>
	<h1>HEllooopleaseO number <%= session.getAttribute("name") %></h1>
	<%} %>
	<form action="index.jsp" method="POST">
		<p>Please enter your name</p>
		<input type="text" name="uname">
		<input type="submit" name="Submit Name">
	</form>
	<hr>
	<p>Welcome to shitty walk, how can i help you?</p>
	<%
		int i;
		for(i=5; i < 200; i++){
			if(i%10 == 0)
				out.println("<br>");
			out.print(i + " ");
		}
		someMethod();
	%> 
	<%//Expressions print content %>
	<%= someMethod() %>
	
	<%--LKJSDFLKJSDOIGJIOSDJGOIJSDLGKLKSDGJLSDJGBkjl;sGLKJKASDGNDFNLDFWJKLFDAJLKDFSAJLK --%>
	
	<jsp:include page="index.html"></jsp:include>
	<%@include file="index.html" %>
	
	
</body>
</html>


