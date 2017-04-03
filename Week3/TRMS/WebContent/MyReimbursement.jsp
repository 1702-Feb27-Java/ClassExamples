<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>TRMS</title>
  </head>
 		
  <body style="background-color:orange;">
  
    <h1 style="color:blue;" align="center">Viewing Your Reimbursement</h1>
	<hr>
	<table border="1">
     	<tr><th>Reimbursement ID</th><th>Employee ID</th><th>Location</th><th>Add Date</th>
     		<th>Course Start Date</th><th>Course End Date</th><th>Course Time</th><th>Course Cost</th>
     		<th>Reimbursement Amount</th><th>Approval Number</th><th>Course ID</th><th>Grade Type ID</th></tr>
        <tr>
            <td>${reimbursement.reim_id}</td>
            <td>${reimbursement.emp_id}</td>
            <td>${reimbursement.location}</td>
            <td>${reimbursement.addDate}</td>
            <td>${reimbursement.courseStartDate}</td>
            <td>${reimbursement.courseEndDate}</td>
            <td>${reimbursement.time}</td>
            <td>${reimbursement.courseCost}</td>
            <td>${reimbursement.reimburstAmt}</td>
            <td>${reimbursement.approvalString}</td>
            <td>${reimbursement.courseIDString}</td>
            <td>${reimbursement.gradeTypeString}</td>
        </tr>
    </table>
      
  </body>
</html>