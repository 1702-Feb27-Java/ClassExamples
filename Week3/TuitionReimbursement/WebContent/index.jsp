<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Log In</title>
		<script
		  src="http://code.jquery.com/jquery-3.1.1.js"
		  integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
		  crossorigin="anonymous"></script>
	
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		
		<style>
			body {
				background-image: url("Images/mainPage.png");
				    -webkit-background-size: cover;
				    -moz-background-size: cover;
				    -o-background-size: cover;
				    background-size: cover;
			}
		</style>
		
	</head>
	
	<body>
	
		
		<% if(request.getParameter("uname")!=null){
			session.setAttribute("username", request.getParameter("uname"));	
		}%>
		<% if(request.getParameter("pword")!=null){
			session.setAttribute("password", request.getParameter("pword"));	
			request.setAttribute("id", null);
		}%>
		
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			  </button>
			  <a class="navbar-brand" href="#">TRMS</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			  <ul class="nav navbar-nav">
				<li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reimbursements <span class="caret"></span></a>
				  <ul class="dropdown-menu">
					<li><a href="#">Apply for Reimbursement</a></li>
					<li><a href="#">View your reimburesments</a></li>
				  </ul>
				</li>
			  </ul>
			  <form class="navbar-form navbar-left">
				<div class="form-group">
				  <input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			  </form>
			  <ul class="nav navbar-nav navbar-right">
				<li><a href="#">Logout</a></li>
			  </ul>
			</div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>

		<br><br><br><br><br><br><br><br><br><br><br><br><br>
		<div class="container" >
		  <form action="LoginServlet.do" method="POST">
			<div class="form-group row">
			  <label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
			  <div class="col-sm-10">
				<input class="form-control" type="text" placeholder="Username" name="uname">
			  </div>
			</div>
			<div class="form-group row">
			  <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
			  <div class="col-sm-10">
				<input class="form-control" type="text" placeholder="Password" name="pword">
			  </div>
			<br><br><br>
			<div class="form-group row">
			  <div class="offset-sm-2 col-sm-10">
				<button type="submit" class="btn btn-primary button" name="Submit login">Sign in</button>
			  </div>
			</div>
		  </form>
		</div>
		
	
	
		
	
	</body>
</html>