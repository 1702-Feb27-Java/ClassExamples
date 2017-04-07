<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attach Document</title>
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

<!-- File Button --> 
<h1>CHOOSE ID YOU WISH TO UPLOAD</h1>

  <%
  for(Employee.Employees e: Employee.Collecters.emp)
  {
  	
  	
  		if(e.getEMP_ID()== Employee.SignedInEmployee.empID )
  		{
  			
  		int count = 0 ;
  			
  			for(Employee.Reinbursment r : Employee.Collecters.rein )
  			{
  				
  				if(r.getEMP_ID() == e.getEMP_ID() && ((r.getAPP_ID()>0 && r.getAPP_ID()<5) || r.getAPP_ID()==6))
  				{
  					
  					out.append("Dept : "+Employee.Display.displayDepartment(e.getDEPT_ID())+"|Reinbursment Number "+ r.getREIN_ID() + "|First Name : "+ e.getFIRST_NAME() + "|Last Name : "+ e.getLAST_NAME() + "|Status Level " + r.getAPP_ID() );
  					out.append("<form action=\"Attach\" method=\"POST\">");
  					out.append("<input type=\"hidden\" name=\"val\" value="+"\""+count+"\">"+"<input type=\"submit\" name=\"AttachAFile\" value=\"AttachFile\">");
  					out.append("</form>");
  					
  					
  				}
  				
  				count++;
  			}
  		  }
  		
  	
  }

%>
  
</div>
</body>
</html>