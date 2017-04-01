<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hobbs.project01.pojo.*" %>
<%@	page import="hobbs.project01.service.ReimbursementServiceImpl" %>
<%! Employee user; Reimbursement reimbursement; %>
<%	user = (Employee)session.getAttribute("user");
	reimbursement = ReimbursementServiceImpl.getReimbursementService().getReimbursement(Integer.parseInt(request.getParameter("id")));
%>
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
<body>


	<!-- HEADER -->
	<nav class="navbar navbar-fixed-top" id="header">
		<div class="navbar-header">
		    <a class="navbar-brand" href="landing">TRMS</a>
		  </div>
		  <div class="collapse navbar-collapse navbar-right" id="header-collapsible">
			<ul class="nav navbar-nav">
				<!-- USER BUTTON -->
	   			<li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= user.getUsername() %> <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="account"><i class="fa fa-home"></i>&nbsp;Home</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="logout.do"><i class="fa fa-sign-out"></i>&nbsp;Logout</a></li>
		          </ul>
		        </li>
			</ul>
		  </div>
	</nav>
	<!-- /.HEADER -->
	
	
	<!-- USER OPS PANEL -->
	<div class="nav-side-menu">
	    <div class="brand">Actions</div>
	    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
	    <div class="menu-list">
	        <ul id="menu-content" class="menu-content collapse out">
	            <li>
	                <a href="apply">
	                    <i class="fa fa-plus fa-lg"></i> New Application
	                </a>
	            </li>
	            
	            <li class="active">
	                <a href="view_all"><i class="fa fa-folder-open fa-lg"></i> View Your Applications</a>
	            </li>
	            
	            <% if (user.getRoleId() == Employee.Role.supervisor.getId() || user.getRoleId() == Employee.Role.head.getId()) { %>
	            	<li>
	            		<a href="view_apps"><i class="fa fa-tasks fa-lg"></i> View Application Requests</a>
	            	</li>
	            <% } %>
	            
	            <li>
	                <a href="account_details"><i class="fa fa-user-circle fa-lg"></i> Account Details</a>
	            </li>
	
	        </ul>
	    </div>
	</div>
	<!-- /.USER OPS PANEL -->
	
	
	<!-- CONTENT -->
	<div class="container" id="content">
	    
	    <h1>Application #<%= reimbursement.getId() %> <small><% if (reimbursement.isUrgent()) { out.print("<i class=\"fa fa-clock-o\" title=\"Urgent\"></i>"); } %></small></h1>
	    
	    <hr>
	    
	    <!-- DATES -->
	    <div class="panel panel-default">
	    	<div class="panel-heading">Significant Dates</div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>Application Submitted On</th>
		    			<th>Event Start Datetime</th>
		    			<th>Event End Datetime</th>
		    		</tr>
		    		<tr>
		    			<td><%= reimbursement.getDatetimeCreated() %></td>
		    			<td><%= reimbursement.getStartDatetime() %></td>
		    			<td><%= reimbursement.getEndDatetime() %></td>
		    		</tr>
		    	</table>
	    	</div>
	    </div>
	    <!-- /.DATES -->
	    
	    <!-- EVENT -->
	    <div class="panel panel-default">
	    	<div class="panel-heading">Event</div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>Type</th>
		    			<th>Description</th>
		    			<th>Event Cost</th>
		    			<th>Percentage Covered</th>
		    			<th>Projected Reimbursement</th>
		    		</tr>
		    		<tr>
		    			<td><%= Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()) %></td>
		    			<td><%= reimbursement.getDescription() %></td>
		    			<td><%= String.format("$%.2f", reimbursement.getCost()) %></td>
		    			<td><%= Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()).getCoveragePercentage() + "%" %></td>
		    			<td><%= String.format("$%.2f", reimbursement.getCost() * Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()).getCoveragePercentage() * .01) %></td>
		    		</tr>
		    	</table>
	    	</div>
	    </div>
	    <!-- /.EVENT -->
	    
	</div>
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