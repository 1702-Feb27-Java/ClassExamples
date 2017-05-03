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
	            <li class="active">
	                <a href="apply">
	                    <i class="fa fa-plus fa-lg"></i> New Application
	                </a>
	            </li>
	            
	            <li>
	                <a href="view_all"><i class="fa fa-folder-open fa-lg"></i> View Your Applications</a>
	            </li>
	            
	            <% if (user.getRoleId() == Employee.Role.supervisor.getId() || user.getRoleId() == Employee.Role.head.getId() || user.getDepartmentId() == Employee.Department.Benco.getId()) { %>
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
	    
	    <div class="panel panel-default">
	    	<!-- EVENT -->
	    	<div class="panel-heading"><h1 class="panel-title">Event details</h1></div>
	    	<div class="table-responsive">
	    		<table class="table table-bordered">
	    			<tr>
	    				<th>Type</th>
	    				<th>Location</th>
	    				<th>Description</th>
	    				<th>Justification</th>
	    				<th>Worktime impact</th>
	    				<th>Cost</th>
	    				<th>Projected reimbursement</th>
	    			</tr>
	    			<tr>
	    				<td>
	    					<select id="type-field" form="app-form" name="eventType">
								<% boolean firstEvent = true; for (Reimbursement.EventType eventType : Reimbursement.EventType.values()) { if (firstEvent) { %>
									<option value="<%= eventType.getId() %>" selected="selected"><%= eventType.toString() %></option>
								<% firstEvent = false; } else { %>
									<option value="<%= eventType.getId() %>"><%= eventType.toString() %></option>
								<% }} %>
							</select>
						</td>
	    				<td><input type="text" form="app-form" name="location" placeholder="Location" required="required"></td>
	    				<td><textarea form="app-form" name="description" placeholder="Describe the event you are seeking reimbursement for." required="required"></textarea></td>
	    				<td><textarea form="app-form" name="justification" placeholder="Describe how it relates to your work." required="required"></textarea></td>
	    				<td><textarea form="app-form" name="worktimeToBeMissed" placeholder="Describe how it will impact your time at work."></textarea></td>
	    				<td><input id="cost-field" type="text" form="app-form" name="cost" required="required"></td>
	    				<td><input id="projected-cost-field" type="text" readonly="readonly" disabled="disabled"></td>
	    			</tr>
	    		</table>
	    	</div>
	    	<!-- /.EVENT -->
	    	<!-- DATES -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Dates</h1></div>
	    	<div class="table-responsive">
	    		<table class="table table-bordered">
	    			<tr>
	    				<th>Start date and time</th>
	    				<th>End date and time</th>
	    			</tr>
	    			<tr>    			
	    				<td>
		    				<label>Date:<br>
								<input type="text" form="app-form" name="startDate" placeholder="YYYY-MM-DD" required="required">
							</label>
							<label>Time (in 24-hour format):<br>
								<input type="text" form="app-form" name="startTime" placeholder="HH:MM:SS" required="required">
							</label>
						</td>
	    				<td>
   							<label>Date:<br>
								<input type="text" form="app-form" name="endDate" placeholder="YYYY-MM-DD" required="required">
							</label>
							<label>Time (in 24-hour format):<br>
								<input type="text" form="app-form" name="endTime" placeholder="HH:MM:SS" required="required">
							</label>
	    				</td>
	    			</tr>
	    		</table>
	    	</div>
	    	<!-- /.DATES -->
	    	<!-- GRADING -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Grading</h1></div>
	    	<div class="table-responsive">
	    		<table class="table table-bordered">
	    			<tr>
	    				<th>Format</th>
	    			</tr>
	    			<tr>
	    				<td>
	    					<select form="app-form" name="gradeFormat">
								<%	boolean firstFormat = true; for (GradeFormat gradeFormat : gradeFormats) { if (firstFormat) { %>
									<option value="<%= gradeFormat.getId() %>" selected="selected"><%= gradeFormat.getGrades() %></option>
								<% firstFormat = false; } else {%>
									<option value="<%= gradeFormat.getId() %>"><%= gradeFormat.getGrades() %></option>
								<% } } %>
							</select>
	    				</td>
	    			</tr>
	    		</table>
	    	</div>
	    	<!-- /.GRADING -->
	    	<!-- ATTACHMENTS -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Attachments <small>(if any)</small></h1></div>
	    	<div class="table-responsive">
	    		<table class="table table-bordered">
	    			<tr>
	    				<th>Event-related</th>
	    				<th>Supervisor pre-approval</th>
	    				<th>Department Head pre-approval</th>
	    			</tr>
	    			<tr>
	    				<td><input type="file" form="app-form" name="event-attachments" multiple accept=".pdf,.png,.jpg,.jpeg,.txt,.doc,.docx,application/pdf,image/png,image/jpeg,text/plain,application/msword"></td>
	    				<td><input type="file" form="app-form" name="supervisor-attachment" accept=".msg,application/vnd.ms-outlook"></td>
	    				<td><input type="file" form="app-form" name="head-attachment" accept=".msg,application/vnd.ms-outlook"></td>
	    			</tr>
	    		</table>
	    	</div>
	    	<!-- /.ATTACHMENTS -->
	    	<!-- APP-FORM -->
	    	<div class="panel-footer">
		    	<form id="app-form" action="submit_application.do" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="employeeId" value="<%= ((Employee)session.getAttribute("user")).getId() %>">
					<input class="btn btn-primary" type="submit" value="Submit">
				</form>
				<p><a class="btn btn-danger" href="account" style="margin-top: 5px;">Cancel</a></p>
	    	</div>
	    	<!-- /.APP-FORM -->
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
		$(document).ready(function() { //TODO: ajax to java
			
			function updateCost() {
				let cost = parseFloat($('#cost-field').val()); 
				//console.log(cost);
				if (!isNaN(cost)) {
					switch (parseInt($('#type-field').val())) {
					case 1:
						$('#projected-cost-field').val('$' + (cost * .8).toFixed(2));
						break;
					case 2:
						$('#projected-cost-field').val('$' + (cost * .6).toFixed(2));
						break;
					case 3:
						$('#projected-cost-field').val('$' + (cost * .75).toFixed(2));
						break;
					case 4:
						$('#projected-cost-field').val('$' + (cost).toFixed(2));
						break;
					case 5:
						$('#projected-cost-field').val('$' + (cost * .9).toFixed(2));
						break;
					case 6:
						$('#projected-cost-field').val('$' + (cost * .3).toFixed(2));
						break;
					}
				}
				else {
					$('#cost-field').val('');
					$('#projected-cost-field').val('');
				}
			}
		
			$('#cost-field').on('blur', function() { 
				updateCost();				
			});
			
			$('#type-field').on('change', function() {
				updateCost();	
			});
		});
	</script>
</body>
</html>