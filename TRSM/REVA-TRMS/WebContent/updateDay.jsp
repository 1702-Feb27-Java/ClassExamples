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
<title>Update Day</title>
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
        <li><a href="form.jsp">Request Form</a></li><li><a href="status.jsp">Request Status</a></li><li><a href="Menu.jsp">Menu</a></li>       
      </ul>
     
   
		
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
The Day has been set forward one Day

		<form action="Advance.do" method="GET">	
		
		<input type="submit" value="Advance Day">
	
	</form>
	
</body>
</html>