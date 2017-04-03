<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hobbs.project01.pojo.*" %>
<%@	page import="hobbs.project01.service.ReimbursementServiceImpl" %>
<%@	page import="java.util.List" %>
<%! Employee user; List<Reimbursement> reimbursements; %>
<%	user = (Employee)session.getAttribute("user");
	reimbursements = ReimbursementServiceImpl.getReimbursementService().getReimbursementsFor(user); %>
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
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-circle fa-lg"></i>&nbsp;&nbsp;<%= user.getUsername() %> <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="account"><i class="fa fa-home"></i>&nbsp;Home</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="logout.do"><i class="fa fa-sign-out"></i>&nbsp;Sign out</a></li>
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
	            
	            <li>
	                <a href="view_all"><i class="fa fa-folder-open fa-lg"></i> View Your Applications</a>
	            </li>
	            
	            <% if (user.getRoleId() == Employee.Role.supervisor.getId() || user.getRoleId() == Employee.Role.head.getId() || user.getDepartmentId() == Employee.Department.Benco.getId()) { %>
	            	<li class="active">
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
	    
	    <h1>Application Requests <small>(viewing <%= reimbursements.size() %> application<%= reimbursements.size() != 1 ? "s" : "" %>)</small></h1>
	    
	    <hr>
	    
	    <div class="table-responsive">
		    <table class="table">
		    	<tr>
		    		<th>Id</th>
		    		<th>Current Status</th>
		    		<th>Applied On</th>
		    		<th>Description</th>
		    		<th>Notes</th>
		    	</tr>
		    <% for (Reimbursement reimbursement : reimbursements) { %>
			    <tr class="clickable-table-row" title="View Reimbursement #<%= reimbursement.getId() %>" data-href="view_app?id=<%= reimbursement.getId() %>">
			    	<td><%= reimbursement.getId() %></td>
			    	<td><%= Reimbursement.Status.getStatus(reimbursement.getStatusId()) %></td>
			    	<td><%= reimbursement.getDatetimeCreated() %></td>
			    	<td><%= reimbursement.getDescription() %></td>
			    	<td><% if (reimbursement.isUrgent()) { out.print("<i class=\"fa fa-clock-o\"></i>"); }%></td>
				 </tr>
		    <% } %>
		    </table>
	    </div>
	    
	</div>
	<!-- /.CONTENT -->
	
	
	<!-- FOOTER -->
	
	<!-- /.FOOTER -->
	
	
	<!-- JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- /.JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {
				$('.clickable-table-row').on('click', function() { window.location = $(this).data('href'); });
		});
	</script>
</body>
</html>