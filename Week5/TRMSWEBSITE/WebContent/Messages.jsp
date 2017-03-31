<%@page import="Employee.Display"%>
<%@page import="Employee.*"%>

<%@page import="Servlets.EmployeeAcceptBenco"%>
<%@page import="Employee.Messages"%>
<%@page import="DAO.DAO"%>
<%@page import="Employee.SignedInEmployee"%>
<%@page import="Employee.Collecters"%>
<%@page import="java.util.ArrayList" %>
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
	<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link" href="Controller">MAIN MENU</a></li>
				<li class="nav-item"><a class="nav-link" href="SignOut">SIGN OUT</a></li>
			
			</ul>

		</nav>

<h1>Messages </h1>


<TABLE BORDER=10>
	
	<TD>Message</TD>
	<TD>Reimbursement ID</TD>
	<TD>Application Status Level</TD>

	
<%
DAO d = new DAO();
Employee.Collecters.msgs =new ArrayList<Messages>();
d.pullAllMessages();
for(int i =0 ;i< Collecters.msgs.size();i++)
{
	%>
	
	 
	  
 	  

   <% 
	if(Collecters.msgs.get(i).getEmp_id()==SignedInEmployee.empID)
	{
		String msg = Collecters.msgs.get(i).getMessage();
		int appid = Collecters.msgs.get(i).getApp_id();
		int empid = Collecters.msgs.get(i).getEmp_id();
		int rein_id = Collecters.msgs.get(i).getRein_id();
		
	String name = Employee.Display.displayStatus(appid);
	    
 %>
 
 
 <TR>

      
    
        <TD><%= msg %></TD>
        

        <TD><%= rein_id %></TD>
        

        <TD><%= name %></TD>
          

       

        	
	   	
	    	
	    	<%
	    	
			//out.append("<form action=\"EmployeeAcceptBenco\" method=\"POST\">");
		//	out.append("<input type=\"hidden\" name=\"val\" value="+"\""+i+"\">"+"<input type=\"submit\" name=\""+i+"\" value=\"Approve\">");
			//out.append("</form>");
			
		//	out.append("<form action=\"EmployeeRejectBenco\" method=\"POST\">");
	//		out.append("<input type=\"hidden\" name=\"val\" value="+"\""+i+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Reject\">");
//			out.append("</form>");
	    }
		//out.append("<br>");
	  %>
	  
	  
	  </TR>
	  <% 
	 
	
}

%>
</TABLE>


</body>
</html>