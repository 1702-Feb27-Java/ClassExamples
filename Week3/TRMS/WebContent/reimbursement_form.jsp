<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- SCRIPT BELOW DOESN'T WORK 
	resource not found error OR
	GET http://localhost:8085/TRMS/WebContent/resources/reimb_form.js 404 ()
	-->




<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Reimbursement Form</title>
<script src="resources/reimb_form.js"></script>
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
				  <li><a href="#">Home </a></li>
				  <span class="arrow sep">►</span>
				  <li><a href="#">Login</a></li>
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
			<form class="form-horizontal" action="LoginServlet" method="POST">
				<!-- Event Title -->
			    <div class="form-group">
					<label for="sel1">Event Title:</label>
					<select name="event-type" class="form-control" id="sel1">
						<!-- forEach jstl to generate options -->
						<option id="filler-option" value="-1">Please select an option...</option>
						<option id="other-option" value="0">Other</option>
					</select>
					
					
					<div id="other-event-input" style="display:none;">
						<input type="text" class="form-control" name="otherEventTitle" maxlength="50" placeholder="Event Title" aria-describedby="basic-addon2">
					</div>
					
				</div>
				<!-- Grade Format -->
			    <div class="form-group">
					<label for="sel2">Grade Format:</label>
					<select name="grade-format" class="form-control" id="sel2">
						<!-- forEach jstl to generate options -->
						<option id="filler-option" value="-1">Please select an option...</option>
						<option id="other-option" value="0">Other</option>
					</select>
					
					
					<div id="other-grade-input" style="display:none;">
						<input type="text" class="form-control" name="otherGradeFormat" maxlength="50" placeholder="Grade Format" aria-describedby="basic-addon2">
					</div>
					
				</div>
				<!-- Date -->
				<div class="form-group">
					<div class="input-append date form_datetime" data-date="2012-12-21T15:25:00Z">
					    <input size="16" type="text" name="reimbDate" value="" readonly>
					    <span class="add-on"><i class="icon-remove"></i></span>
					    <span class="add-on"><i class="icon-th"></i></span>
					</div>
					 
					<script type="text/javascript">
					    $(".form_datetime").datetimepicker({
					        format: "dd MM yyyy - HH:ii P",
					        showMeridian: true,
					        autoclose: true,
					        todayBtn: true
					    });
					</script>  
				</div>
				<!-- Time -->
				<div class="input-group">
				  <span class="input-group-addon">$</span>
				  <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
				  <span class="input-group-addon">.00</span>
				</div>
				<br>
				<label for="basic-url">Your vanity URL</label>
				<div class="input-group">
				  <span class="input-group-addon" id="basic-addon3">https://example.com/users/</span>
				  <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">
				</div>	
				<button class="btn btn-lg btn-primary btn-block" type="submit">Apply</button>
			</form>
		</div>
		<div class="col-md-3"></div>
		</div>
	</div>
	
	<!-- Javascript code -->

	
	
	<br>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>