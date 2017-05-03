<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='com.revature.dao.DAOImpl'%>
<%@ page import='com.revature.dao.DAO'%>
<%@ page import='java.util.*'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	h1 {word-wrap: break-word;
		color: orange;}
	p {color: forestgreen;}
	body {background-color: powderblue;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP FOR LIFE</title>
</head>
<body>

	<%!
	public String someMethod()
	{
		return "Method Called!";
	}
	%>
	
	<% if(request.getParameter("uname")!= null){
			session.setAttribute("name", request.getParameter("uname"));
		}%>
	
	<% if(session.isNew()){%>
	<h1>Welcome to my JSP.....</h1>
	<%}else { %>
	<h1>Welcome <%= session.getAttribute("name") %></h1>
	<% } %>
	<form action="index.jsp" method="POST">
		<p>Please enter your name</p>
		<input type="text" name="uname">
		<input type="submit" value="Submit Name">
	</form>
	<hr>
	<p>This is just some meaningless text!</p>
	<%
		int i;
		for(i = 5; i <= 10; i++)
		{
			out.println(i + "<br>");
		}
	%>
	<!-- this is a comment -->
	<%//Expressions Print contents to the webpage %>
	<%-- someMethod() --%>
	
	<%-- comment: aiokLDFjmnoslkfjsd;oifljsioksdjnofsjlflikfjslkfsdjoifdsljfsdilfjsdilfik --%>
	
	<!-- <jsp:include page="index.html"></jsp:include> -->
	<!-- <%@include file="index.html" %> -->
</body>
</html>