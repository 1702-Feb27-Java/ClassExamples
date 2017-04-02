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
	
     <table border="1">
     	<tr><th>Reimbursement ID</th><th>Employee ID</th><th>Location</th><th>Add Date</th>
     		<th>Course Start Date</th><th>Course End Date</th><th>Course Time</th><th>Course Cost</th>
     		<th>Reimbursement Amount</th><th>Approval Number</th><th>Course ID</th><th>Grade Type ID</th></tr>
     	<c:forEach items="${reimbursements}" var="row">
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
    </div>
  </body>
</html>