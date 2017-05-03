<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="hobbs.project01.pojo.*" %>
<%@	page import="hobbs.project01.service.ReimbursementServiceImpl" %>
<%@	page import="java.util.List" %>
<%! Employee user;
	Reimbursement reimbursement;
	List<ReimbursementAttachment> attachments; //to allow convenient access to reimbursement's attachments.
	Approval supervisorApproval, headApproval, bencoApproval;
%>
<%	user = (Employee)session.getAttribute("user");
	reimbursement = ReimbursementServiceImpl.getReimbursementService().getReimbursement(Integer.parseInt(request.getParameter("id")));
	session.setAttribute("reimbursement", reimbursement); //for later approving OR denying.
	attachments = reimbursement.getAttachments();
	supervisorApproval = ReimbursementServiceImpl.getReimbursementService().getSupervisorApproval(reimbursement);
	headApproval = ReimbursementServiceImpl.getReimbursementService().getHeadApproval(reimbursement);
	bencoApproval = ReimbursementServiceImpl.getReimbursementService().getBencoApproval(reimbursement);
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
	            <li>
	                <a href="apply">
	                    <i class="fa fa-plus fa-lg"></i> New Application
	                </a>
	            </li>
	            
	            <li class="active">
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
	
		<% if (reimbursement.getStatusId() == Reimbursement.Status.pending.getId()) { %>
			<button class="btn btn-success pull-right" data-toggle="modal" data-target=".grade-form" style="padding-top: 10px"><i class="fa fa-paperclip fa-lg">&nbsp;Add grade</i></button>
		<% } %>
	    
	    <h1>Application Details <small>(ID #<%= reimbursement.getId() %>) <% if (reimbursement.isUrgent()) { out.print("<i class=\"fa fa-clock-o\" title=\"Urgent\"></i>"); } %></small></h1>    
	    
	    <hr>
	    
	    <div class="panel panel-default">
	    	<!-- APP -->
	    	<div class="panel-heading"><h1 class="panel-title">Submission details</h1></div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>Submitted on</th>
		    		</tr>
		    		<tr>
		    			<td><%= reimbursement.getDatetimeCreated() %></td>
		    		</tr>
		    	</table>
	    	</div>
	    	<!-- /.APP -->
	    	<!-- APPROVALS -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Approvals</h1></div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>Supervisor</th>
		    			<th>Department head</th>
		    			<th>Benefits Coordinator</th>
		    		</tr>
		    		<tr>
		    			<!-- SUPERVISOR -->
		    			<td class="approval-subtable">
		    				<div class="table-responsive">
						    	<table class="table table-bordered">
						    		<tr>
						    			<th>ID</th>
						    			<th>Supervisor</th>
						    			<th>Status</th>
						    			<th>Messages</th>
						    		</tr>
						    		<tr>
						    			<td><% if (supervisorApproval != null) out.print(supervisorApproval.getId()); %></td>
						    			<td><% if (supervisorApproval != null) out.print(supervisorApproval.getApproverId()); %></td>
						    			<td><% if (supervisorApproval != null) out.print(Approval.Status.getStatus(supervisorApproval.getApprovalStatusId())); %></td>
						    			<td><i class="fa fa-inbox"></i></td><%-- TODO: implement message feature and display --%>
						    		</tr>
						    	</table>
					    	</div>
		    			</td>
		    			<!-- /.SUPERVISOR -->
		    			<!-- HEAD -->
		    			<td class="approval-subtable">
		    				<div class="table-responsive">
						    	<table class="table table-bordered">
						    		<tr>
						    			<th>ID</th>
						    			<th>Head</th>
						    			<th>Status</th>
						    			<th>Messages</th>
						    		</tr>
						    		<tr>
						    			<td><% if (headApproval != null) out.print(headApproval.getId()); %></td>
						    			<td><% if (headApproval != null) out.print(headApproval.getApproverId()); %></td>
						    			<td><% if (headApproval != null) out.print(Approval.Status.getStatus(headApproval.getApprovalStatusId())); %></td>
						    			<td><i class="fa fa-inbox"></i></td><%-- TODO: implement message feature and display --%>
						    		</tr>
						    	</table>
					    	</div>
		    			</td>
		    			<!-- /.HEAD -->
		    			<!-- BENCO -->
		    			<td class="approval-subtable">
		    				<div class="table-responsive">
						    	<table class="table table-bordered">
						    		<tr>
						    			<th>ID</th>
						    			<th>Benefits Coordinator</th>
						    			<th>Status</th>
						    			<th>Messages</th>
						    		</tr>
						    		<tr>
						    			<td><% if (bencoApproval != null) out.print(bencoApproval.getId()); %></td>
						    			<td><% if (bencoApproval != null) out.print(bencoApproval.getApproverId()); %></td>
						    			<td><% if (bencoApproval != null) out.print(Approval.Status.getStatus(bencoApproval.getApprovalStatusId())); %></td>
						    			<td><i class="fa fa-inbox"></i></td><%-- TODO: implement message feature and display --%>
						    		</tr>
						    	</table>
					    	</div>
		    			</td>
		    			<!-- /.BENCO -->
		    		</tr>
		    	</table>
	    	</div>
	    	<!-- /.APPROVALS -->
	    	<!-- EVENT -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Event</h1></div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>Type</th>
		    			<th>Start date/time</th>
		    			<th>End date/time</th>
		    			<th>Description</th>
		    			<th>Justification</th>
		    			<th>Worktime impact</th>
		    			<th>Event cost</th>
		    			<th>Percentage covered</th>
		    			<th>Projected reimbursement</th>
		    		</tr>
		    		<tr>
		    			<td><%= Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()) %></td>
		    			<td><%= reimbursement.getStartDatetime() %></td>
		    			<td><%= reimbursement.getEndDatetime() %></td>
		    			<td><%= reimbursement.getDescription() %></td>
		    			<td><%= reimbursement.getJustification() %></td>
		    			<td><%= reimbursement.getWorktimeToBeMissed() %></td>
		    			<td><%= String.format("$%.2f", reimbursement.getCost()) %></td>
		    			<td><%= Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()).getCoveragePercentage() + "%" %></td>
		    			<td><%= String.format("$%.2f", reimbursement.getCost() * Reimbursement.EventType.getEventType(reimbursement.getEventTypeId()).getCoveragePercentage() * .01) %></td>
		    		</tr>
		    	</table>
	    	</div>
	    	<!-- /.EVENT -->
	    	<!-- ATTACHMENTS -->
	    	<div class="panel-heading nested"><h1 class="panel-title">Attachments <small>(<%= attachments != null ? attachments.size() : 0 %> attachment<%= attachments != null ? (attachments.size() != 1 ? "s" : "") : "s" %>)</small></h1></div>
	    	<div class="table-responsive">
		    	<table class="table table-bordered">
		    		<tr>
		    			<th>ID</th>
		    			<th>Type</th>
		    			<th>URL</th>
		    		</tr>
		    		<% if (attachments != null) { for (ReimbursementAttachment attachment : attachments) { %>
		    			<tr>
			    			<td><%= attachment.getId() %>
			    			<td><%= ReimbursementAttachment.AttachmentType.getAttachmentType(attachment.getAttachmentTypeId()) %></td>
			    			<td><%= attachment.getUrl() %></td><%-- change to generate a navigable url (For downloading purposes) --%>
			    			<%--<td><a href="<%= ReimbursementServiceImpl.getReimbursementService().generateLink(attachment) %>">View</a></td> TODO: FIXME?: --%>
			    		</tr>
	    			<% } } %>	
		    	</table>
	    	</div>
	    	<!-- /.ATTACHMENTS -->
	    </div>
	    
	</div>
	<!-- /.CONTENT -->
	
	<!-- ADD GRADE FORM -->
	<div class="modal fade grade-form" role="dialog">
    	<div class="modal-dialog modal-sm">
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    			<h2 class="modal-title">Add Grade</h2>
	    		</div>
	    		<div class="modal-body">
	    			<div class="panel panel-default">
	    				<div class="panel-heading"><h1 class="panel-title">Grade earned</h1></div>
	    				<div class="panel-body">
			    			<input type="text" name="grade" placeholder="Grade earned" form="add-grade" required="required">
	    				</div>
	    				<div class="panel-heading nested"><h1 class="panel-title">Attachments <small>(if any)</small></h1></div>
	    				<div class="panel-body">
			    			<input type="file" name="grade-attachments" form="add-grade" multiple>
	    				</div>
	    			</div>
	    		</div>
	    		<div class="modal-footer">
	    			<form id="add-grade" action="add_grade.do?id=<%= reimbursement.getId() %>" method="POST" enctype="multipart/form-data">
	    				<input class="btn btn-primary" type="submit" value="Add to Reimbursement">
	    			</form>
	    		</div>
    		</div>
    	</div>
    </div>
    <!-- /.ADD GRADE FORM -->
	
	<!-- FOOTER -->
	
	<!-- /.FOOTER -->
	
	
	<!-- JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- /.JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>