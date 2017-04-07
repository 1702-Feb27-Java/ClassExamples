<%@page import="DAO.DAO"%>
<%@page import="Employee.Collecters"%>
<%@page import="DAO.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Benco Approval</title>

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
	<nav class="navbar navbar-light bg-faded"> <a
		class="navbar-brand" href="index.html">TRMS</a>
	<ul class="nav navbar-nav">
		<li class="nav-item"><a class="nav-link" href="Controller">MAIN
				MENU</a></li>
		<li class="nav-item"><a class="nav-link" href="SignOut">SIGN
				OUT</a></li>

	</ul>

	</nav>

	<h1>APPROVE REINBURSMENT</h1>
	<TABLE BORDER=10>
	<TD>DEPT</TD>
	<TD>Reinbursment Number</TD>
	<TD>First Name</TD>
	<TD>Last Name</TD>
	<TD>Status Level</TD>
	<TD></TD>
	<TD></TD>
	<TD></TD>
		<%
		DAO d = new DAO();
		 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
		  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
		  d.pullAllReinDown();
		 d. pullAllEmpDown();
for(Employee.Employees e: Employee.Collecters.emp)
{
	
	
		if(e.getEMP_ID()== Employee.SignedInEmployee.empID )
		{
			
		}
		else
		{
			int count = 0;
			for(Employee.Reinbursment r : Employee.Collecters.rein)
			{
				if(r.getEMP_ID() == e.getEMP_ID() && r.getAPP_ID()==4 )
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
				<%out.append("<form action=\"ApproveBenco\" method=\"POST\">");
							out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\""+count+"\" value=\"Approve\">");
							out.append("</form>"); %>
			</TD>


			<TD>
				<%out.append("<form action=\"RejectionDirect\" method=\"POST\">");
							out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Reject\">");
							out.append("</form>"); %>
			</TD>


			<TD>
				<%out.append("<form action=\"ChangeCostBenco\" method=\"POST\">");
							out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"ChangeCostBenco\" value=\"Change Reinbursment\">");
							out.append("</form>"); %>
			</TD>
					<TD>
				<%out.append("<form action=\"RequestDirect\" method=\"POST\">");
				out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Request More Info\">");
				out.append("</form>");		 %>
			</TD>

			
		</TR>

					
			<%
					
					
					
			 // out.append("Dept : "+Employee.Display.displayDepartment(e.getDEPT_ID())+"|Reinbursment Number "+ r.getREIN_ID() + "|First Name : "+ e.getFIRST_NAME() + "|Last Name : "+ e.getLAST_NAME() + "|Status Level " + r.getAPP_ID() );
				//out.append("<form action=\"ApproveBenco\" method=\"POST\">");
				//out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\""+count+"\" value=\"Approve\">");
				//out.append("</form>");
				
				//out.append("<form action=\"Reinhandler\" method=\"POST\">");
				/* out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Reject\">");
				out.append("</form>");
				
				
				
				out.append("<form action=\"ChangeCostBenco\" method=\"POST\">");
				out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"ChangeCostBenco\" value=\"Change Reinbursment\">");
				out.append("</form>"); */
				}
				
				count++;
				//System.out.println(count);
				
			}
		  }
		

}

%>

	</TABLE>
</body>
</html>