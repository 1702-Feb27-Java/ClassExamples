<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="objects.Employee" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
</head>
<body>
<sql:setDataSource var="snapshot" driver="oracle.jdbc.driver.OracleDriver"
     url="jdbc:oracle:thin:@localhost:1521:xe"
     user="trms"  password="p4ssw0rd"/>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- SEND TO INEXT -->    
    <div class="navbar-header">      
      <a class="navbar-brand" href="index.jsp">TRMS</a>
    </div>

    <!-- SEND TO REQUEST FORM OR INBOX -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="inbox.jsp">Inbox<span class="sr-only">(current)</span></a></li>
        <li><a href="form.html">Request Form</a></li><li><a href="status.jsp">Request Status</a></li><li><a href="Menu.jsp">Menu</a></li>       
      </ul>
     
    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	<%if(((Employee)session.getAttribute("employee")).getDepart().equals("BenCo")) {%>
		<sql:query dataSource="${snapshot}" var="result">
		SELECT DISTINCT REIMBURSE.REIM_ID, REIMBURSE.EMPLOYEE_ID, REIMBURSE.REIMBURSE_COST, REIMBURSE.URGENT from REIMBURSE, EMPLOYEE, APPROVE where (APPROVE.STATUS_NUM between 5 and 6 AND EMPLOYEE.USERNAME = '<%= ((Employee)session.getAttribute("employee")).getUserName() %>' AND  REIMBURSE.EMPLOYEE_ID != EMPLOYEE.EMPLOYEE_ID AND APPROVE.R_ID = REIMBURSE.REIM_ID) OR (EMPLOYEE.USERNAME = '<%= ((Employee)session.getAttribute("employee")).getUserName() %>' AND APPROVE.E_ID = EMPLOYEE.EMPLOYEE_ID AND APPROVE.R_ID = REIMBURSE.REIM_ID AND APPROVE.STATUS_NUM < 7) ORDER BY REIMBURSE.URGENT DESC		
		</sql:query>
		
		Here is the list of Request for your attention
	<table border="1" width="100%">
	<tr>
	<th>Reim ID</th>
	<th>Employee ID</th>
	<th>Amount Requested</th>
	<th>Marked Urgent?</th>	
	<th>View Request</th>		
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
	<td><c:out value="${row.REIM_ID}"/></td>
	<td><c:out value="${row.EMPLOYEE_ID}"/></td>
	<td><c:out value="${row.REIMBURSE_COST}"/></td>
	<td><c:if test="${row.URGENT == 0}">
  			NO
		</c:if>
		<c:if test="${row.URGENT == 1}">
  			YES
		</c:if>
	</td>
	<td><form action="ViewRequest.do"><input type="submit" name="action" value="${row.REIM_ID}" id="${row.REIM_ID}"></form></td>	
	</tr>
	</c:forEach>
	</table>
	<%}else{ %>	
		
	<%if(((Employee)session.getAttribute("employee")).getRole().equals("Associate")) {%>
		Sorry you can't approve requests
		<%}else{ %>
		
		<sql:query dataSource="${snapshot}" var="result">
		SELECT REIMBURSE.REIM_ID, REIMBURSE.EMPLOYEE_ID, REIMBURSE.REIMBURSE_COST, REIMBURSE.URGENT from REIMBURSE, EMPLOYEE, APPROVE where EMPLOYEE.USERNAME = '<%= ((Employee)session.getAttribute("employee")).getUserName() %>' AND APPROVE.E_ID = EMPLOYEE.EMPLOYEE_ID AND APPROVE.R_ID = REIMBURSE.REIM_ID AND APPROVE.STATUS_NUM < 10 ORDER BY REIMBURSE.URGENT DESC
		</sql:query>
		
		Here is the list of Request for your attention
	<table border="1" width="100%">
	<tr>
	<th>Reim ID</th>
	<th>Employee ID</th>
	<th>Amount Requested</th>
	<th>Marked Urgent?</th>		
	<th>View Request</th>	
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
	<td><c:out value="${row.REIM_ID}"/></td>
	<td><c:out value="${row.EMPLOYEE_ID}"/></td>
	<td><c:out value="${row.REIMBURSE_COST}"/></td>
	<td><c:if test="${row.URGENT == 0}">
  			NO
		</c:if>
		<c:if test="${row.URGENT == 1}">
  			YES
		</c:if>
	</td>
	<td><form action="ViewRequest.do"><input type="submit" name="action" value="${row.REIM_ID}" id="${row.REIM_ID}"></form></td>	
	</tr>
	</c:forEach>
	</table>
	<%}} %>
	
	
	
	
</body>
</html>