<%@page import="Employee.SignedInEmployee"%>
<%@page import="Employee.Collecters"%>
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

<h1>Reinbursment Status </h1>


<TABLE BORDER=10>
	
	<TD>Reinbursment Number</TD>
	<TD>First Name</TD>
	<TD>Last Name</TD>
	<TD>Status Level</TD>
	<TD></TD>
	<TD></TD>
	
<%

for(int i =0 ;i< Collecters.rein.size();i++)
{
	%>
	
	 
	  
 	  

   <% 
	if(Collecters.rein.get(i).getEMP_ID()==SignedInEmployee.empID)
	{
		String first = SignedInEmployee.firstname;
		String last = SignedInEmployee.lastname;
		int rein_id = Collecters.rein.get(i).getREIN_ID();
	    int app_id= Collecters.rein.get(i).getAPP_ID();
	    
		String name = Employee.Display.displayStatus(app_id);
	    
	    
 %>
 
 
 <TR>

      
    
        <TD><%= rein_id %></TD>
        

        <TD><%= first %></TD>
        

        <TD><%= last %></TD>
          

        <TD><%= name %></TD>
 <%
	    
	    //out.append("|Reinbursment Number "+ rein_id + "|First Name : "+ first + "|Last Name : "+ last + "|Status Level " + app_id );
	    if(Collecters.rein.get(i).getAPP_ID() == 8)
	    {
	    	
	    	
	    	%>
	    	
	    <TD><%
	    out.append("<form action=\"EmployeeAcceptBenco\" method=\"POST\">");
		out.append("<input type=\"hidden\" name=\"val\" value="+"\""+i+"\">"+"<input type=\"submit\" name=\""+i+"\" value=\"Approve\">");
		out.append("</form>"); %></TD>
        	
	    
	    <TD><%
		out.append("<form action=\"EmployeeRejectBenco\" method=\"POST\">");
		out.append("<input type=\"hidden\" name=\"val\" value="+"\""+i+"\">"+"<input type=\"submit\" name=\"Reject\" value=\"Reject\">");
		out.append("</form>");
		 %></TD>
        	
	   	
	    	
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
}

%>
</TABLE>


</body>
</html>