<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Reimbursement Form</title>
</head>
<body>
	<!-- Space reserved for header -->
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		
	</div>
	<!-- Breadcrumb container -->	
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<ul class="breadcrumb">
				  <!-- TODO: link to LogoutServlet -->
				  <li><a href="home.jsp">Home </a></li>
				  <span class="arrow sep">►</span>
				  <li><a href="employee_menu.jsp">Menu</a></li>
				  <span class="arrow sep">►</span>
				  <li class="active">Reimbursement Form</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form class="form-horizontal" action="ApplyReimbursementServlet" method="POST">
				<!-- Event Title -->
			    <div class="form-group">
					<label for="eventTitle">Event Title:</label>
					<select name="event-type" class="form-control" id="eventTitle">
						<!-- forEach jstl to generate options -->
						<option id="filler-option" value="-1">Please select an option...</option>
						<c:forEach var="eventTitle" items="${requestScope.eventTitles}" varStatus="loop">
							<option id="${loop.count}" value="${loop.count}"><c:out value="${eventTitle}"></c:out></option>
						</c:forEach>
						<option id="other-option" value="0">Other</option>
					</select>
					
					
					<div id="other-event-input" style="display:none;">
						<input type="text" class="form-control" name="otherEventTitle" maxlength="50" placeholder="Event Title" aria-describedby="basic-addon2"/>
					</div>
					
				</div>
				<!-- Location -->
			    <div class="form-group">
					<label for="location">Location:</label>
					<div id="location-div">
						<input type="text" class="form-control" name="location" maxlength="50" placeholder="Location" aria-describedby="basic-addon2"/>
					</div>
					
				</div>
				<!-- Grade Format -->
			    <div class="form-group">
					<label for="gradeFormat">Grade Format:</label>
					<select name="grade-format" class="form-control" id="gradeFormat">
						<!-- forEach jstl to generate options -->
						<option id="filler-option" value="-1">Please select an option...</option>
						<c:forEach var="gradeString" items="${requestScope.gradeStrings}" varStatus="loop">
							<option id="${loop.count}" value="${loop.count}">${gradeString}</option>
						</c:forEach>
						<option id="other-option" value="0">Other</option>
					</select>
					
					
					<div id="other-grade-input" style="display:none;">
						<input type="text" class="form-control" name="otherGradeFormat" maxlength="50" placeholder="Grade Format" aria-describedby="basic-addon2">
						<input type="text" class="form-control" name="otherGradePassingGrade" maxlength="50" placeholder="Passing Grade" aria-describedby="basic-addon2">
						<input type="checkbox" class="form-control" name="otherGradePresReq" />
					</div>
				</div>
				
			    <div class="form-group">
			    	<div class="row">
			    		<!-- Date -->
				    	<div class="col-md-6">
				        	<label for="dateRangePicker">Event Date:</label>
	
				            <div class="input-group input-append date" id="dateRangePicker">
				                <input type="text" class="form-control" name="eventDate" />
				                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				            </div>
			            </div>
			            <!-- Cost -->
			            <div class="col-md-6">
			            	<label for="cost-div">Event Cost:</label>
		      				<div class="input-group" id="cost-div">
							  <span class="input-group-addon">$</span>
							  <input type="text" name="costOfEvent" class="form-control" aria-label="Amount (to the nearest dollar)"/>
							  <span class="input-group-addon">.00</span>
							</div>
						</div>
					</div>
			    </div>
			    <!-- Event Description -->
			    <div class="form-group">
				  <label for="eventDesc">Event Description:</label>
				  <textarea class="form-control" rows="5" id="eventDesc" name="eventDesc" maxlength="250"></textarea>
			    </div>
			    <!-- Work Justification -->
  			    <div class="form-group">
				  <label for="workJust">Work Justification:</label>
				  <textarea class="form-control" rows="5" id="workJust" name="workJust" maxlength="250"></textarea>
			    </div>
				
				<br>
				<!-- Read Only Projected Reimbursement -->
				<label for="basic-url">Projected Reimbursement:</label>
				<div class="input-group">
				  <span class="input-group-addon">$</span>
				  <span class="form-control" id="basic-addon3">Read Only Field</span>
				  <span class="input-group-addon">.00</span>	
				</div>
				
				
				<input type="submit" value="Save and Submit"/>
			</form>
		</div>
		<div class="col-md-3"></div>
		</div>
	</div>
	<br>
	<!-- Optional Form Fields -->
	<hr>
	<br>
	<div class="container">
		<div class="row">
			<!-- <form action="ApplyReimbursementServlet" method="POST" enctype="multipart/form-data">
				<!-- Event Attachments --> <!-- 
				<div class="col-md-3"></div>
				<div class="col-md-3">
					<label class="btn btn-primary" for="my-file-selector">
					    <input id="my-file-selector" type="file" style="display:none;" onchange="$('#upload-file-info').html($(this).val());">Choose File</label>
					<span class='label label-info' id="upload-file-info"></span>
				</div>
				<!-- Approval Attachments --> <!-- 
				<div class="col-md-3">
					<label class="control-label">Select File</label>
					<input id="appAttach" name="appAttach[]" multiple type="file" class="file file-loading" data-allowed-file-extensions="['msg']">
				</div>
			</form>	-->
		</div>
	</div>
	<br>
	<hr>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>