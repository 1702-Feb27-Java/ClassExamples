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
			   		<li><a href="login">Login</a></li>
			   	<% } else { %>
			   		<li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= user.getUsername() %> <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="account"><i class="fa fa-home"></i>&nbsp;Home</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="logout.do"><i class="fa fa-sign-out"></i>&nbsp;Logout</a></li>
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
		It provides a complete software solution for applying for and managing the approval of tuition reimbursement applications by your organization's employees.</div>
    	</div>
	<div >
	    
	    
    <nav id="hypervisor" class="nav">
    	<ul class="nav nav-pills nav-justified">
    		<li><i class="fa fa-hourglass-2 fa-2x"></i><p>Timely and accurate approvals</p></li>
    		<li><i class="fa fa-handshake-o fa-2x"></i><p>Automatic and correct routing of tasks</p></li>
    		<li><i class="fa fa-cogs fa-2x"></i><p>Complete solution for all your organization's employees</p></li>
    	</ul>
    </nav>
    
    <div class="slide-left">
    	<span class="background-text-left"><i class="fa fa-hourglass-2 fa-4x"></i></span>
    	<div class="slide-content">Timely</div>
    </div>

	<div class="slide-right">
    	<span class="background-text-right"><i class="fa fa-handshake-o fa-4x"></i></span>
    	<div class="slide-content">Automatic</div>
    </div>

	<div class="slide-left">
		<span class="background-text-left"><i class="fa fa-cogs fa-4x"></i></span>
		<div class="slide-content">Complete</div>
	</div>
	<!-- 
	<div class="stack">
		<p>Solution Stack</p>
		<ul>
			<li>HTML5</li>
			<li>jQuery</li>
			<li>Java / Java EE</li>
			<li>Oracle 11g</li>
			<li>Amazon AWS S3</li>
			<li>Tomcat</li>
		</ul>
	</div>
	-->
	<!-- /.CONTENT -->
	
	
	<!-- FOOTER -->
	
	<!-- /.FOOTER -->

	
	<!-- JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- /.JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>