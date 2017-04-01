<%@page import="Employee.Reinbursment"%>
<%@page import="Employee.SignedInEmployee"%>
<%@page import="Employee.Collecters"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve Employee</title>

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

	<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="nav-link" href="Controller">MAIN MENU</a></li>
		
				<li class="nav-item"><a class="nav-link" href="SignOut">SIGN OUT</a></li>
				
			</ul>

		</nav>
<h1> APPROVE EMPLOYEE APPLICATION</h1>

	<TABLE BORDER=2>
<%


%>
	<TABLE BORDER=10>
	<TD>DEPT</TD>
	<TD>Reinbursment Number</TD>
	<TD>First Name</TD>
	<TD>Last Name</TD>
	<TD>Status Level</TD>
	<TD></TD>
	<TD></TD>
	
<%
for(Employee.Employees e: Employee.Collecters.emp)
{
	
	if(e.getREPORTS_TO_ID() == SignedInEmployee.empID || SignedInEmployee.reportstoid ==0)
	{
		if(e.getEMP_ID()== SignedInEmployee.empID )
		{
			
		}
		else
		{int count = 0 ;
			
			for(Employee.Reinbursment r : Employee.Collecters.rein )
			{
				System.out.println("WORKING" + " r emp id "+r.getEMP_ID() +" e emp id " +e.getEMP_ID());
				
			
				if(r.getEMP_ID() == e.getEMP_ID() && SignedInEmployee.role == r.getAPP_ID() )
				{
					
					//String dept = Employee.Display.displayDepartment(e.getDEPT_ID());
			%>
				
				
		<TR>

		

			<TD><% out.append("" + Employee.Display.displayDepartment(e.getDEPT_ID()));%> </TD>
		

				<TD><% out.append("" + r.getREIN_ID()); %></TD>
			

			<TD><% out.append("" +e.getFIRST_NAME()); %></TD>
			

			<TD><% out.append("" +e.getLAST_NAME()); %></TD>
			
			<TD><% out.append("" +r.getAPP_ID()); %></TD>

			<TD>
				<%	out.append("<form action=\"ApproveEmps\" method=\"POST\">");
				out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Approve\" value=\"Approve\">");
				out.append("</form>"); %>
			</TD>


			<TD>
				<%out.append("<form action=\"RejectionDirect\" method=\"POST\">");
				out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Reject\">");
				out.append("</form>");		 %>
			</TD>

		<TD>
				<%out.append("<form action=\"RequestDirect\" method=\"POST\">");
				out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Request More Info\">");
				out.append("</form>");		 %>
			</TD>


	
		</TR>

					
		<%
							
					
				}
				
				count++;
			}
		  }
		}
	
}

%>
</TABLE>
</body>
</html>