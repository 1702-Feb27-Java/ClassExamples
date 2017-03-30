<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="objects.Employee" %>
    <%@ page import="objects.Reimburse" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
        <li><a href="form.html">Request Form</a></li><li><a href="status.jsp">Request Status</a></li>       
      </ul>
     
    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<h1 align="center">Here is Form <%=((Reimburse)session.getAttribute("reim")).getReim_id() %></h1>
	
	<table border="1" width="60%" align="center">
	<tr>
	<th> </th>
	<th> </th>	
	</tr>
	
	<tr>
	<td>Employee ID</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getEmployee_id() %></td>			
	</tr>
	<tr>
	<td>Employee Name</td>
	<td> <%=((Employee)session.getAttribute("reimEm")).getFirstName() %> <%= ((Employee)session.getAttribute("reimEm")).getLastName() %></td>			
	</tr>
	<tr>
	<td>Awarded Reimbursements</td>
	<td> <%=((Employee)session.getAttribute("reimEm")).getAwarded() %></td>			
	</tr>
	<tr>
	<td>Pending Reimbursements</td>
	<td> <%=((Employee)session.getAttribute("reimEm")).getPending() %></td>			
	</tr>
	<tr>
	<td>Employee Email</td>
	<td> <%=((Employee)session.getAttribute("reimEm")).getEmail() %></td>			
	</tr>
	<tr>
	<td>Event Date</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getEvent_date() %></td>			
	</tr>
	<tr>
	<td>Event Length</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getEventLength() %></td>			
	</tr>
	<tr>
	<td>Location</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getLocation() %></td>			
	</tr>
	<tr>
	<td>Description of Event</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getDescription() %></td>			
	</tr>
	<tr>
	<td>Type of Event</td>
	<td> <% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 1){%>
				University Course
			<%} %>
			<% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 2){%>
				Seminar
			<%} %>
			<% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 3){%>
				Certification Preparation Class
			<%} %>
			<% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 4){%>
				Certification
			<%} %>
			<% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 5){%>
				Technical Training
			<%} %>
			<% if(((Reimburse)session.getAttribute("reim")).getCourseID() == 6){%>
				Other
			<%} %>	
	</td>			
	</tr>
	<tr>
	<td>Grading Format</td>
	<td><%if(((Reimburse)session.getAttribute("reim")).getGrade() == 1) {%> 
 		A-F
 		<%} %>
 		<%if(((Reimburse)session.getAttribute("reim")).getGrade() == 2) {%> 
 		P/F
 		<%} %>
 		<%if(((Reimburse)session.getAttribute("reim")).getGrade() == 3) {%> 
 		0-100
 		<%} %>
 		<%if(((Reimburse)session.getAttribute("reim")).getGrade() == 4) {%> 
 		Presentation
 		<%} %> 		
		
	</td>			
	</tr>
	<tr>
	<td>Cost</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getCost() %></td>			
	</tr>
	<tr>
	<td>Justification</td>
	<td> <%=((Reimburse)session.getAttribute("reim")).getJustification() %></td>			
	</tr>
	
	
	
	</table>
	
	<br>
	<br>
	<br>
	<form action="ApproveServ.do" method="POST">
	<br>
	<div class="btn-group btn-group-justified" role="group" aria-label="...">
  	<div class="btn-group" role="group">
    <input type="submit" class="btn btn-default" value="approve" name="choice">
  	</div>
 	 <div class="btn-group" role="group">
    <input type="submit" class="btn btn-default" value="request info" name="choice">
  	</div>
  	<div class="btn-group" role="group">
    <input type="submit" class="btn btn-default" value="deny" name="choice">
  	</div>
  	
  	
  	
</div>
<br><br>
<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="message">Message</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="message" name="message"></textarea>
  </div>
</div>
</form>
</body>
</html>