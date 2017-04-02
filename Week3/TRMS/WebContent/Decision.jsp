<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRMS</title>
</head>
<body style="background-color:orange;">
	<h1 style="color:blue;" align="center">Approval Page</h1>
	
	<table border="1">
     	<tr><th>Reimbursement ID</th><th>Employee ID</th><th>Location</th><th>Add Date</th>
     		<th>Course Start Date</th><th>Course End Date</th><th>Course Time</th><th>Course Cost</th>
     		<th>Reimbursement Amount</th><th>Approval Number</th><th>Course ID</th><th>Grade Type ID</th>
     		</tr>
     	<c:forEach items="${reims}" var="row">
        <tr>
            <td>${row.reim_id}</td>
            <td>${row.emp_id}</td>
            <td>${row.location}</td>
            <td>${row.addDate}</td>
            <td>${row.courseStartDate}</td>
            <td>${row.courseEndDate}</td>
            <td>${row.time}</td>
            <td>${row.courseCost}</td>
            <td>${row.reimburstAmt}</td>
            <td>${row.approval}</td>
            <td>${row.courseID}</td>
            <td>${row.gradeTypeID}</td>
        </tr>
    	</c:forEach>
    </table>
    
    <form align="center" action="ApprovalServlet.do" style="color:blue">
    	<input type="hidden" name="approveid" value="${row.reim_id}">
    	<input type="hidden" name="approvelevel" value="${row.approval}">
    	<input type="submit" name="approve" value="Approve">
    </form>
    <form align="center" action="DeclineServlet.do" style="color:blue">
    	<input type="hidden" name="declineid" value="${row.reim_id}">
    	<input type="hidden" name="declinelevel" value="${row.approval}">
    	<input type="submit" name="decline" value="Decline">
    </form>
</body>
</html>