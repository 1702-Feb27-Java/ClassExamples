<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attach Document</title>

  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
	
	<script type="text/javascript">
	function showFileName() {
		   var fil = document.getElementById("myFile");
		   alert(fil.value);
		}

    
    $('#fileUpload').change( function(event) {
    	var tmppath = URL.createObjectURL(event.target.files[0]);
    	    $("img").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));

    	   
    	});

</script>


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
  <%
  for(Employee.Employees e: Employee.Collecters.emp)
  {
  	
  	HttpSession s = request.getSession();
  	int  val  =(int) s.getAttribute("val");
  		if(e.getEMP_ID()== Employee.SignedInEmployee.empID )
  		{
  			
  		int count = 0 ;
  			
  			for(Employee.Reinbursment r : Employee.Collecters.rein )
  			{
  				
  				if(r.getREIN_ID() == Employee.Collecters.rein.get(val).getREIN_ID())
  				{
  					
  					out.append("Dept : "+Employee.Display.displayDepartment(e.getDEPT_ID())+"|Reinbursment Number "+ r.getREIN_ID() + "|First Name : "+ e.getFIRST_NAME() + "|Last Name : "+ e.getLAST_NAME() + "|Status Level " + r.getAPP_ID() );
  				
  					
  					
  				}
  				
  				count++;
  			}
  		  }
  		
  	
  }

%>
<br>
<h1>Please Upload File</h1>
<div class="form-group">
 <form action="AttachProcess" method="POST">
  <label class="col-md-4 control-label" for="filebutton"></label>
  <div class="col-md-4">

    <input id="fileUpload" name="filebutton" class="input-file" type="file" required = ""  directory >
   <input	type="submit" value="       Submit       ">
  </div>
  </form>

  
</div>
</body>
</html>