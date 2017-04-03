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
        <tr>
            <td>${reim.reim_id}</td>
            <td>${reim.emp_id}</td>
            <td>${reim.location}</td>
            <td>${reim.addDate}</td>
            <td>${reim.courseStartDate}</td>
            <td>${reim.courseEndDate}</td>
            <td>${reim.time}</td>
            <td>${reim.courseCost}</td>
            <td>${reim.reimburstAmt}</td>
            <td>${reim.approval}</td>
            <td>${reim.courseID}</td>
            <td>${reim.gradeTypeID}</td>
        </tr>
    </table>
    
    <form align="center" action="ApprovalServlet.do" style="color:blue">
    	<input type="hidden" name="approveid" value="${reim.reim_id}">
    	<input type="hidden" name="approvelevel" value="${reim.approval}">
    	<input type="submit" name="approve" value="Approve">
    </form>
    <form align="center" action="DeclineServlet.do" style="color:blue">
    	<input type="hidden" name="declineid" value="${reim.reim_id}">
    	<input type="hidden" name="declinelevel" value="${reim.approval}">
    	<input type="submit" name="decline" value="Decline">
    </form>
</body>
</html>