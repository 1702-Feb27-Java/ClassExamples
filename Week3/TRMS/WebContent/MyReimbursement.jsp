<%-- <%@page import="pojo.*, java.util.*" %> --%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>TRMS</title>
  </head>
 	<%-- <%!
  		Reimburstment reim = (Reimburstment)request.getAttribute("Reimbursement");
  	%> --%>	
  <body style="background-color:orange;">
  
    <h1 style="color:blue;" align="center">Viewing Your Reimbursement</h1>
	<hr>
	<form align="center" method="POST">
	     Please Enter Your Employee-ID:<br>
	     <input type="text" readonly name="emp_id"><br>
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
	     Please Enter The Reimbursement Amount:<br>
	     <input type="text" name="reimAmt"><br>
	     Please Enter The Grade Type:<br>
	     <input type="text" name="gradeType"><br>
	     <br>
	     <input type="submit" value="Submit Your Reimbursement">
	</form>
      
  </body>
</html>