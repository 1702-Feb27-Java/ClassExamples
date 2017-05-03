<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>TRMS</title>
  </head>
  
  <body style="background-color:orange;">
  
     <h1 style="color:blue;" align="center">Reimbursement Creation Page</h1>
	<hr>
	<form align="center" action="MakeReimServlet.do" method="POST">
	     Please Enter Your Employee-ID:<br>
	     <input type="text" name="emp_id" value="${user.emp_id}" readonly><br>
	     Please Enter The Location Of The Business:<br>
	     <input type="text" name="relocation"><br>
	     Please Enter The Add Date Of The Course:<br>
	     <input type="text" name="addDate"><br>
	     Please Enter The Course Start Date:<br>
	     <input type="text" name="startDate"><br>
	     Please Enter The Course End Date:<br>
	     <input type="text" name="endDate"><br>
	     Please Enter The Time/Length of the course:<br>
	     <input type="text" name="courseTime"><br>
	     Please Enter The Cost of the Course:<br>
	     <input type="text" name="courseCost"><br>
	     Please enter the course ID:<br>
	     1 for University Course, 2 for Seminars, 3 for Certification Prep. Class<br>
	     4 for Certification, 5 for Technical Training<br>
	     <input type="text" name="courseID"><br>
	     <!-- Please Enter The Reimbursement Amount:<br>
	     <input type="text" name="reimAmt"><br> -->
	     Please Enter The Grade Type ID:<br>
	     <input type="text" name="gradeTypeID"><br>
	     <br>
	     <input type="submit" value="Submit Your Reimbursement">
	</form>
      
  </body>
</html>