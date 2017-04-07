<%@page import="Employee.SignedInEmployee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>




<body>
<%
if(SignedInEmployee.signedIn==false)
{
	
	 response.sendRedirect("signin.jsp");	
}
%>

	<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
		<li class="nav-item"><a class="nav-link" href="Controller">MAIN MENU</a></li>
	<li class="nav-item"><a class="nav-link" href="DisplayMessages">MESSAGES</a></li>
			
				<li class="nav-item"><a class="nav-link" href="SignOut">SIGN OUT</a></li>
		
			</ul>

		</nav>


<%
if(SignedInEmployee.signedIn == true)
{
	
out.append("<h1>Hello,"+ SignedInEmployee.firstname+" " + SignedInEmployee.lastname + "</h1>"+"</br>");	
}
%>




	










<%
if((Employee.SignedInEmployee.deptID==6))
{
	
	
	



%>



<%

if((Employee.SignedInEmployee.role)==1)
{
%>
<h1>Welcome to the  Employee Page!!!</h1>
<form action="Reinbursments" method = "POST" >
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
         <option value="ApproveReinbursments">Approve Reimbursements</option>
           <option value="UploadAttachments">Upload Attachments</option>
       
           
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>


<%

if((Employee.SignedInEmployee.role)==2)
{
%>
<h1>Welcome to the Benefits Cordinator Direct Supervisor Page!!!</h1>
<form action="Reinbursments" method = "POST">
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
    <option value="ApproveApplications">Approve Applications</option>
    <option value="InitiateApproval">Initiate Applications</option>
     <option value="ApproveReinbursments">Approve Reinbursments</option>
      <option value="UploadAttachments">Upload Attachments</option>
      
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>

<%

if((Employee.SignedInEmployee.role)==3)
{
%>
<h1>Welcome to the Benefits Cordinator Department Head Page!!!</h1>
<form action="Reinbursments" method = "POST">
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
    <option value="ApproveApplications">Approve Applications</option>
    <option value="InitiateApproval">Initiate Applications</option>
       <option value="ApproveReinbursments">Approve Reinbursments</option>
        <option value="UploadAttachments">Upload Attachments</option>
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>

<% } else { %>



<%------------------------------------------------------------------------------------------------------- --%>



<%

if((Employee.SignedInEmployee.role)==1)
{
%>
<h1>Welcome to the  Cordinator Employee Page!!!</h1>
<form action="Reinbursments"  method = "POST" >
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
     <option value="ApproveReinbursments">Approve Reinbursments</option>
      <option value="UploadAttachments">Upload Attachments</option>
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>
<%

if((Employee.SignedInEmployee.role)==2)
{
%>
<h1>Welcome to the Direct Supervisor Page!!!</h1>
<form action="Reinbursments" method = "POST">
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
    <option value="ApproveApplications">Approve Applications</option>
    <option value="InitiateApproval">Initiate Applications</option>
     <option value="UploadAttachments">Upload Attachments</option>
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>

<%

if((Employee.SignedInEmployee.role)==3)
{
%>
<h1>Welcome to the Department Head Page!!!</h1>
<form action="Reinbursments" method = "POST">
  <select name="item">
  	<option value = "" ></option>
    <option value="SignUpForReinbursment">Sign Up For Reimbursement</option>
    <option value="ViewReinbursmentStatus">View Reimbursement Status</option>
    <option value="ApproveApplications">Approve Applications</option>
    <option value="InitiateApproval">Initiate Applications</option>
     <option value="UploadAttachments">Upload Attachments</option>
  </select>
  <input type="submit" value="Submit">
</form>
<%
}
%>
<% } %>


</body>
</html>