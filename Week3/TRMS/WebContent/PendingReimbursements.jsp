<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@page import='java.util.*' %>
<%@page import='com.revature.pojo.*' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
  	<script src="http://code.jquery.com/jquery-latest.min.js"
        type="text/javascript"></script>
     <meta charset="UTF-8">
     <title>TRMS</title>
  </head>
  
  <body style="background-color:orange;">
  
     <h1 style="color:blue;" align="center">Pending Reimbursements Page</h1>
	<hr>
	<div id="somediv">
	<% ArrayList<Reimburstment> arr = (ArrayList<Reimburstment>)request.getSession().getAttribute("reimbursements"); %>
	<% for(Reimburstment r: arr){
		System.out.println(r.toString());
	}
		%>
     	<c:forEach  var="reimbursements" items="${request.Session.reimbursements}">
     	<c:out value="${reimbursements}" />
     	</c:forEach>
     	<table>
     		<th>HELP</th>
     		<tr><td><p>Reimbursement: </p></td></tr>
     	</table>
    </div>
  </body>
</html>