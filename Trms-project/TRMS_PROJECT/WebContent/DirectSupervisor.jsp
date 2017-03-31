<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.pojo.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="com.revature.pojo.Reimbursement"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Direct Supervisor</title>
</head>
<body>
	<% Employee e = (Employee) session.getAttribute("user"); %>
	<h2>Welcome <%= e.getFname() %></h2>
	<p> Please check the forms below </p>
    
    <div align="Center">
    	<table border="1" cellpadding="5">
    		<caption><h2>List of Forms</h2></caption>
    		<tr>
                <th>Employee id</th>
                <th>location</th>
                <th>Form Date</th>
                <th>Course Start Date</th>
                <th>Course End Date</th>
                <th>Course Length(in Minutes)</th>
                <th>Course Cost</th>
                <th>Amount Reimbursed</th>
                <th>Form id</th>
                <th>Approval Status</th>
                <th>Course id</th>
                <th>Grade type</th>
                <th>Actual Grade</th>
                
            </tr>
    
    		<% ArrayList<Reimbursement> TT = 
    		(ArrayList<Reimbursement>) session.getAttribute("forminfo"); 
    		%>
    		
    		<%for (Reimbursement r: TT)  { %>
                <tr>
                    <td><%=r.getEmp_id() %></td>
                    <td><%=r.getLocation_()%></td>
                    <td><%=r.getAdd_date() %></td>
                    <td><%=r.getStart_course() %></td>
                    <td><%=r.getEnd_course()%></td>
                    <td><%=r.getCourse_time()%></td>
                    <td><%=r.getCourse_cost()%></td>
                    <td><%=r.getReim_amt() %></td>
                    <td><%=r.getReim_id()%></td>
                    <td><%=r.getAppr_id()%></td>
                    <td><%=r.getCourse_id()%></td>
                    <td><%=r.getGrade_type_id()%></td>
                    <td><%=r.getGrade()%></td>
                    
                </tr>
            <% } %>
    	</table>
    </div>
	
	<div>
		<a href = "Employee.jsp"> <input type = "button" value="Sign for reimbursement" name = "Sign up form"></a>
	</div>
	
	
	

</body>
</html>