<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Logged In</title>
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
		
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
		<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>




		<style>
			body {
				background-image: url("Images/gradient.png");
				    -webkit-background-size: cover;
				    -moz-background-size: cover;
				    -o-background-size: cover;
				    background-size: cover;
			}
			h1{
				text-align: center;
			}
			#dateRangeForm .form-control-feedback {
			    top: 0;
			    right: -15px;
			}
		</style>
		
	</head>
	
	<body>

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
					<li><a href="#">View your Reimbursements</a></li>
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
		
			<%--
			
			System.out.println(serveEmp.applyForReimbursement(emp_id, event, eventDate, time, location, formDate, description, cost, 
				grading_id, typeOfEventId, urgentId, approval_step_id, approval_cutoff));
			
			 --%>	
		<h1>Logged in as: <%= session.getAttribute("uId") %></h1><br><br><br><br><br><br>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<h1>Reimbursement Form</h1><br>
		<div class="row text-center">
			<div class="col-sm-4"></div>
			<div class="col-md-4">
				<form action="SubmitReimbursement.do" method="POST">
				  <div class="form-group">
				    <label for="event">Event Name</label>
				    <input type="text" required class="form-control" id="event" placeholder="Event" name="event">
				  </div>
				  
				  
		<div class="row">
			    <div class="form-group">
			    	<div class="row">
			    		<div class="col-sm-3"></div>
			        	<label class="col-md-6 control-label">Event Date</label>
			        	<div class="col-sm-3"></div>
			        </div>
			        <div class="row">
			    		<div class="col-sm-3"></div>
			            <div class="col-md-6 input-group input-append date" id="dateRangePicker">
			                <input type="text" required class="form-control" name="eventDate" />
			                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
			            <div class="col-sm-3"></div>
			        </div>
			    </div>	
		</div>
			
			<script>
			$(document).ready(function() {
			    $('#dateRangePicker')
			        .datepicker({
			            format: 'mm/dd/yyyy',
			            startDate: '01/01/2010',
			            endDate: '12/30/2020'
			        })
			        .on('changeDate', function(e) {
			            // Revalidate the date field
			            $('#dateRangeForm').formValidation('revalidateField', 'date');
			        });
			});
			</script>
		
				  <div class="form-group">
				    <label for="time">Event time</label>
				    <input type="text" required class="form-control" id="time" placeholder="Time" name="time">
				  </div>
				  <div class="form-group">
				    <label for="location">Event Location</label>
				    <select class="form-control" id="locationSelect" name="location">
				      <c:forEach items="${locationsList}" var="location">
				      	<option value=${location}>
				      		${location}
				      </c:forEach>
				      	<option value="none" id="none">none of the above</option>
				    </select>
				  </div>
				  <div class="row" id="newLocation" style="display:none">
				  	<div class="col-sm-3">New location: </div>
				  	<div class="col-lg-9">
				  		<div class="form-group">
				    		<input type="text" class="form-control" placeholder="Location" name="location2">
				    	</div>
				  	</div>
				  </div>
				  
			
				  
				  <script type="text/javascript">
					   $('#locationSelect').bind('change', function(event) {
									
						           var i= $('#locationSelect').val();
						           //var e = document.getElementById("locationSelect");
						           //var strUser = e.options[e.selectedIndex].text
						         
						           $('#newLocation').hide();
						           console.log(i);
						           
						            if(i=="none") // equal to a selection option
						             {
						                 $('#newLocation').show();
						              	 
						             }
						           else if(i!="none")
						             {
						               $('#newLocation').hide(); // hide the first one
						              }
						});
				   </script>
				  <div class="form-group">
				    <label for="description">Event description</label>
				    <input type="text" required class="form-control" id="description" placeholder="Description" name="description"> 
				  </div>
				  <div class="form-group">
				    <label for="cost">Event cost</label>
				    <input type="number" required class="form-control" id="cost" placeholder="Cost" name="cost">
				  </div>
				  <div class="form-group">
				    <label for="gradingType">Grading Scale</label>
				    <select class="form-control" id="gradingType"  name="gradingId">
				      <c:forEach items="${gradingTypes}" var="gradingType">
				      	<option value="${gradingType}">
				      		${gradingType}
				      </c:forEach>
				      	<option value="none">none of the above</option>
				    </select>
				  </div>
				  <div class="row">
				  	<div class="col-sm-3">New Grading Scale: </div>
				  	<div class="col-lg-9">
				  		<div class="form-group">
				    		<input type="text" class="form-control" id="newGradingInput" placeholder="Grading Scale"  name="gradingId2">
				    	</div>
				  	</div>
				  </div>
				  	<div class="form-group">
				    <label for="eventType">Event Type</label>
				    <select class="form-control" required id="eventType" name="typeOfEvent">
				      <c:forEach items="${eventTypes}" var="eventType">
				      	<option value="${eventType}">
				      		${eventType}
				      </c:forEach>
				    </select>
				  </div>
				  <div class="form-group">
				    <label for="exampleInputFile">File input</label>
				    <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
				    <small id="fileHelp" class="form-text text-muted">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
				  </div>
				  <button type="submit" class="btn btn-primary">Submit</button>
				</form><br><br><br><br><br>
				</div>
			<div class="col-sm-4"></div>
		</div>
		
	
	</body>
</html>