<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 <%@ page import="objects.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users list</title>
</head>
<body>
	<c:out value="This is a list of users in the database"></c:out>
	<br>
	<sql:setDataSource var="snapshot" driver="oracle.jdbc.driver.OracleDriver"
     url="jdbc:oracle:thin:@localhost:1521:xe"
     user="trms"  password="p4ssw0rd"/>

	<sql:query dataSource="${snapshot}" var="result">
	SELECT * from EMPLOYEE
	</sql:query>
 
	<table border="1" width="100%">
	<tr>
	<th>Emp ID</th>
	<th>UserName</th>
	<th>First Name</th>
	<th>Last Name</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
	<td><c:out value="${row.EMPLOYEE_ID}"/></td>
	<td><c:out value="${row.USERNAME}"/></td>
	<td><c:out value="${row.FIRST_NAME}"/></td>
	<td><c:out value="${row.LAST_NAME}"/></td>
	</tr>
	</c:forEach>
	</table>
	
	<br>
		<h2> This is by Employee who logged in</h2>	
		<%=((Employee)session.getAttribute("employee")).getUserName() %>
		
		
		
</body>
</html>