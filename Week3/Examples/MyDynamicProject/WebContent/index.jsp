<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My JSP Page</title>
</head>
<body>
	<h1>Using JSTL</h1>
	<c:out value="randomThing"></c:out> <!-- Using cout to print "randomThing" to the page -->
	<c:catch var="excptn">					<!-- Using catch to set up a block to test risky code then make a variable excptn that stays null unless an enception occurs. When it does, it gets stored in excptn" -->
		<c:if test="${2/0}"></c:if> <!-- Note the Expression language: "${something to evaluate}" -->
	</c:catch>
	<c:out value="${excptn}"></c:out> <!-- Contents wil be null unless exception occurs -->
	
	<!-- Database interaction with JSTL -->
	<c:catch var="ExceptionObject"> <!-- Define driver connectivity details -->
		<sql:setDataSource var="NameOfDatabase" 
		driver="oracle.jdbc.driver.OracleDriver" 
		url="jdbc:oracle:thin:@localhost:1521:xe"
		user="admin" 
		password="admin"/>
		<!-- Next: Query the database. Include the datasource variable described above, then a variable to store result -->
		<sql:query dataSource="NameOfDatabase" var="queryName">select * from dual;</sql:query>
	</c:catch>
	<c:out value="${ExceptionObject}"></c:out> <!-- If exception occurs, we catch it and print it. -->
	
	<c:forEach var="row" items="$queryName.rows}">
	<tr>
	   <td><c:out value="${row.eid}"/></td>
	   <td><c:out value="${row.ename}"/></td> <!-- Use a for each loop to parse the query results -->
	</tr>
	</c:forEach>
	<hr>
	<h1>JSP Tags!</h1>
	<!-- Declarations can create instance variables and methods -->
	<%!
		public String someMethod(){
			return "Method Called!";
		}
	%>

	<!-- Scriptlet tags let you define a block where you can write java code -->
	<% if(request.getParameter("uname")!=null){
		
		session.setAttribute("name", request.getParameter("uname"));
	}%>

	<!-- Check if this is the clients first time opening the site -->
	<% if(session.isNew()){  %>
	
	<h1>Welcome to my JSP!.... page</h1>
	
	<%}else {%>
	<!-- If it isn't, then read the name of the client which will be stored in the session object -->
	<h1>Welcome  
	<%= session.getAttribute("name") %>
	<%}%> 
	</h1> 
	
	<!-- This form will submit to this same page. When that occurs the Request object will be available with the stored parameters -->
	<form action="index.jsp" method="POST">
		<p>Please enter your name</p>
		<input type="text" name="uname">
		<input type="submit" value="Submit Name">
	</form>
	
	
	
	<hr>
	<!-- Another scriptlet example -->
	<p>This is just some meaningless text</p>
	<% 
		int i;
		for(i=5; i < 10; i++){
			out.print(i);
			out.print("<br>\n");
			out.print("<br>");
		}
		someMethod();
	%>
	<!-- This is a comment -->
	<%//Expressions print contents to the webpage 
	/* asldkj*/%>
	<%= someMethod() %>
	
	<%-- ;lsdhf;ajf;lkasd;zokfdjofklafj;osdf;lksdjo
	sadadlasjdlkasjd
	asdasdda
	--%>
	
	<%-- 
	<jsp:include page="index.html"> </jsp:include>
	<%@include file="index.html" %> --%>
	
</body>
</html>