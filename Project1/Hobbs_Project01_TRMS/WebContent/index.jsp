<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hobbs.project01.pojo.*" %>
<%! Employee user; %>
<% user = (Employee)session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>TRMS</title>
	
	<!-- CSS -->
	<!-- bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
		integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<!-- fontawesome -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- app -->
	<link rel="stylesheet" href="css/main.css">
	<!-- /.CSS -->
</head>
<body class="non-user">


	<!-- HEADER -->
	<nav class="navbar navbar-fixed-top" id="header">
		<div class="navbar-header">
		    <a class="navbar-brand" href="landing">TRMS</a>
		  </div>
		  <div class="collapse navbar-collapse navbar-right" id="header-collapsible">
			<ul class="nav navbar-nav">
				<!-- LOGIN OR USER BUTTON -->
				<% if (user == null) { %>
			   		<li><a href="login"><i class="fa fa-sign-in fa-lg"></i>&nbsp;&nbsp;Sign in</a></li>
			   	<% } else { %>
			   		<li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-circle fa-lg"></i>&nbsp;&nbsp;<%= user.getUsername() %> <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="account"><i class="fa fa-home"></i>&nbsp;Home</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="logout.do"><i class="fa fa-sign-out"></i>&nbsp;Sign out</a></li>
			          </ul>
			        </li>
			   	<% } %>
			</ul>
		  </div>
	</nav>
	<!-- /.HEADER -->
	
		
	<!-- CONTENT -->
	<div id="hero">
    	<h1>Tuition Reimbursement Management System</h1>
    	
    	 <div>TRMS is a business process management (BPM) solution that improves the timeliness and accuracy of tuition reimbursement approvals.
		It provides a complete software solution for applying for and managing the approval of tuition reimbursement applications by your organization's employees.
    	</div>
	</div>
	    
	    
    <nav id="hypervisor" class="nav">
    	<ul class="nav nav-pills nav-justified">
    		<li><i class="fa fa-hourglass-2 fa-2x"></i><h4>Timely and accurate approvals</h4></li>
    		<li><i class="fa fa-handshake-o fa-2x"></i><h4>Automatic and correct routing of tasks</h4></li>
    		<li><i class="fa fa-cogs fa-2x"></i><h4>Complete solution for all your organization's employees</h4></li>
    	</ul>
    </nav>
    
    <div class="slide-left">
    	<span class="background-text-left"><i class="fa fa-hourglass-2 fa-4x"></i></span>
    	<div class="slide-content">
    		<h2>Timely</h2>
    	</div>
    </div>

	<div class="slide-right">
    	<span class="background-text-right"><i class="fa fa-handshake-o fa-4x"></i></span>
    	<div class="slide-content">
    		<h2>Automatic</h2>
    	</div>
    </div>

	<div class="slide-left">
		<span class="background-text-left"><i class="fa fa-cogs fa-4x"></i></span>
		<div class="slide-content">
			<h2>Complete</h2>
		</div>
	</div>
	
	<div id="stack">
		<h2>Solution Stack</h2>
		<div class="row">
			<div class="col-md-4">
				<h3>Presentation</h3>
				<div class="thumbnail">
					<img src="media/branding/bootstrap.png" alt="Bootstrap">
					<div class="caption"><h4>Bootstrap</h4></div>
				</div>
				<div class="thumbnail">
					<img src="media/branding/jquery.png" alt="jQuery">
					<div class="caption"><h4>jQuery</h4></div>
				</div>
			</div>
			<div class="col-md-4">
				<h3>Application</h3>
				<div class="thumbnail">
					<img src="media/branding/java-ee.png" alt="Java EE">
					<div class="caption"><h4>Java EE</h4></div>
				</div>
			</div>
			<div class="col-md-4">
				<h3>Data</h3>
				<div class="thumbnail">
					<img src="media/branding/oracle.png" alt="Oracle Database">
					<div class="caption"><h4>Oracle Database</h4></div>
				</div>
				<div class="thumbnail">
					<img src="media/branding/aws.png" alt="Amazon S3">
					<div class="caption"><h4>Amazon S3</h4></div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- /.CONTENT -->
	
	
	<!-- FOOTER -->
	<footer id="footer">
		<div class="links">
			<ul class="list-inline">
				<li><a href="#">Terms</a></li>
				<li><a href="#">Privacy Policy</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Contact</a></li>
				<li><a href="#">Feedback</a></li>
			</ul>
		</div>
		<div class="notice">
			Copyright 2017 Michael Hobbs
		</div>
	</footer>
	<!-- /.FOOTER -->

	
	<!-- JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- /.JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>