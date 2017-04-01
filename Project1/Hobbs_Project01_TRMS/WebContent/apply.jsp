<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hobbs.project01.pojo.*" %>
<%@ page import="hobbs.project01.service.*" %>
<%@ page import="java.util.List" %>
<%! Employee user; List<GradeFormat> gradeFormats; %>
<%	user = (Employee)session.getAttribute("user"); 
	gradeFormats = ReimbursementServiceImpl.getReimbursementService().getGradeFormats();
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
	            <li class="active">
	                <a href="apply">
	                    <i class="fa fa-plus fa-lg"></i> New Application
	                </a>
	            </li>
	            
	            <li>
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
	    
	    <h1>New Application</h1>
	    
	    <hr>
	    
		<form action="submit_application.do" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="employeeId" value="<%= ((Employee)session.getAttribute("user")).getId() %>">
		
		<p>
		<label>Event type:<br>
			<select name="eventType">
				<% boolean firstEvent = true; for (Reimbursement.EventType eventType : Reimbursement.EventType.values()) { if (firstEvent) { %>
					<option value="<%= eventType.getId() %>" selected="selected"><%= eventType.toString() %></option>
				<% firstEvent = false; } else { %>
					<option value="<%= eventType.getId() %>"><%= eventType.toString() %></option>
				<% }} %>
			</select>
		</label>
		</p>
		
		<p>
		<label>Grading format:<br>
			<select name="gradeFormat">
				<%	boolean firstFormat = true; for (GradeFormat gradeFormat : gradeFormats) { if (firstFormat) { %>
					<option value="<%= gradeFormat.getId() %>" selected="selected"><%= gradeFormat.getGrades() %></option>
				<% firstFormat = false; } else {%>
					<option value="<%= gradeFormat.getId() %>"><%= gradeFormat.getGrades() %></option>
				<% } } %>
			</select>
		</label>
		</p>
		
		<p>
		<label>Location:<br>
			<input type="text" name="location" required="required">
		</label>
		</p>
		
		<p>
		<label>Cost:<br>
			<input type="text" name="cost" required="required">
		</label>
		</p>
		
		<p>Starting date and time<br>
		<label>Date:
			<input type="text" name="startDate" placeholder="YYYY-MM-DD" required="required">
		</label>
		<label>Time (in 24-hour format):
			<input type="text" name="startTime" placeholder="HH:MM:DD" required="required">
		</label>
		</p>
		
		<p>Ending date and time<br>
		<label>Date:
			<input type="text" name="endDate" placeholder="YYYY-MM-DD" required="required">
		</label>
		<label>Time (in 24-hour format):
			<input type="text" name="endTime" placeholder="HH:MM:DD" required="required">
		</label>
		</p>
		
		<p>
		<label>Description:<br>
			<textarea name="description" placeholder="Describe the event you are seeking reimbursement for." required="required"></textarea>
		</label>
		</p>
		
		<p>
		<label>Work-related justification:<br>
			<textarea name="justification" placeholder="Describe how it relates to your work." required="required"></textarea>
		</label>
		</p>
		
		<p>
		<label>Worktime to be missed:<br>
			<textarea name="worktimeToBeMissed" placeholder="Describe how it will impact your time at work."></textarea>
		</label>
		</p>
		
		<p>
		<label>Event-related attachments:<br>
			<input type="file" name="event-attachments" multiple accept=".pdf,.png,.jpg,.jpeg,.txt,.doc,.docx,application/pdf,image/png,image/jpeg,text/plain,application/msword">
		</label>
		</p>
		
		<p>Pre-approvals<br>
		<label>Supervisor pre-approval attachment:<br>
			<input type="file" name="supervisor-attachment" accept=".msg,application/vnd.ms-outlook">
		</label>
		<label>Department Head pre-approval attachment:<br>
			<input type="file" name="head-attachment" accept=".msg,application/vnd.ms-outlook">
		</label>
		</p>
		
		<input type="submit" value="Submit">
	</form>
	
	<p><a href="account">Cancel and return</a></p>
	    
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