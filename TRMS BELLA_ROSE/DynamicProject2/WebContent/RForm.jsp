<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Request Form</title>

<!-- JQuery, required for parts of Bootstrap that use it -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Lobster Two">

<style type="text/css">
body {
	text-align: center;
	font-family: 'Lobster Two';
	background-image:
		url("http://vectorandpsd.com/vectorandpsd/background/blue-poly-background.jpg");
	background-repeat: no-repeat;
}

.button {
	padding: 15px 25px;
	font-size: 24px;
	text-align: center;
	cursor: pointer;
	outline: none;
	color: #fff;
	background-color: #4CAF50;
	border: none;
	border-radius: 15px;
	box-shadow: 0 9px #999;
}

.button:hover {
	background-color: #3e8e41
}

.button:active {
	background-color: #3e8e41;
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}

form {
	text-align: center;
}

.panel-table .panel-body {
	padding: 0;
}

.panel-table .panel-body .table-bordered {
	border-style: none;
	margin: 0;
}

.panel-table .panel-body .table-bordered>thead>tr>th:first-of-type {
	text-align: center;
	width: 100px;
}

.panel-table .panel-body .table-bordered>thead>tr>th:last-of-type,
	.panel-table .panel-body .table-bordered>tbody>tr>td:last-of-type {
	border-right: 0px;
}

.panel-table .panel-body .table-bordered>thead>tr>th:first-of-type,
	.panel-table .panel-body .table-bordered>tbody>tr>td:first-of-type {
	border-left: 0px;
}

.panel-table .panel-body .table-bordered>tbody>tr:first-of-type>td {
	border-bottom: 0px;
}

.panel-table .panel-body .table-bordered>thead>tr:first-of-type>th {
	border-top: 0px;
}

.panel-table .panel-footer .pagination {
	margin: 0;
}

/*
used to vertically center elements, may need modification if you're not using default sizes.
*/
.panel-table .panel-footer .col {
	line-height: 34px;
	height: 34px;
}

.panel-table .panel-heading .col h3 {
	line-height: 30px;
	height: 30px;
}

.panel-table .panel-body .table-bordered>tbody>tr>td {
	line-height: 34px;
}
</style>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="form-group col-md-4">
				<h2><strong>Dept: ${sessionScope.CurrEmp.getDept() }</strong></h2>
			</div>
			<div class="form-group col-md-4">
				<h1><strong id="WelcomeMessage">Welcome ${sessionScope.CurrEmp.getFirstName() }</strong></h1>
			</div>
			<div class="form-group col-md-4">
				<h2><strong>Position: ${sessionScope.CurrEmp.getRole() }</strong></h2>
			</div>
		</div>
		<button class="button"
			onclick="$('#field2').slideUp(500); $('#field1').slideDown(500);">Request
			Form</button>
		<button id="MyReqButt" class="button"
			onclick="$('#field1').slideUp(500); $('#field2').slideDown(500);">My
			Requests</button>
		<hr />

		<fieldset id="field1" style="display: none">

			<!-- Form Name -->
			<h1>Reimbursement Request Form</h1>
			<hr />

<form>
			<!-- Select Basic -->

			<div class="row">
				<div class="form-group col-md-6">
					<h2>
						<label for="courseType">Reimbursement Type</label>
					</h2>
					<div class="form-group">
						<h2>
							<select id="courseType" class="img-rounded btn-default"
								name="courseType">
								<option value="1">University Course</option>
								<option value="2">Seminar</option>
								<option value="3">Certification Prep Classes</option>
								<option value="4">Certification</option>
								<option value="5">Technical Training</option>
								<option value="6">Other</option>
							</select>
						</h2>
					</div>
				</div>


				<!-- Select Basic -->
				<div class="form-group col-md-6">
					<h2>
						<label for="gradeType">Grading Format</label>
					</h2>
					<div class="form-group">
						<h2>
							<select id="gradeType" class="img-rounded btn-default"
								name="gradeType">
								<option value="1">Letter Grade (A-F)</option>
								<option value="2">Number Grade (0-100)</option>
								<option value="3">Pass/Fail</option>
								<option value="4">Presentation</option>
							</select>
						</h2>
					</div>
				</div>
			</div>
			<!-- end row div -->

			<!-- Text input-->
			<div class="row">
				<div class="form-group col-md-6">
					<h2>
						<label for="passingGrade">Passing Grade</label>
					</h2>
					<div class="form-group">
						<h2>
							<input id="passingGrade" name="passingGrade" type="text"
								placeholder="" class="btn-default" value="C" required>
						</h2>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group col-md-6">
					<h2>
						<label for="location">Location</label>
					</h2>
					<div class="form-group">
						<h2>
							<input id="location" name="location" type="text" placeholder=""
								class="btn-default" value="Revature" required>
						</h2>
					</div>
				</div>
			</div>
			<!-- end row div -->

			<!-- Text input-->
			<div class="row">
					<div class="form-group col-md-6">
						<h2>
							<label for="meeting">Start Date : </label>
						</h2>
						<div class="form-group">
							<h2>
								<input id="startDate" required type="date" value="2017-03-01"
									class="btn-default" name="startDate" />
							</h2>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group col-md-6">
						<h2>
							<label for="meeting">End Date : </label>
						</h2>
						<div class="form-group">
							<h2>
								<input id="endDate" required type="date" value="2017-03-01"
									class="btn-default" name="endDate" />
							</h2>
						</div>
					</div>
			<!-- end row div -->

			<!-- Text input-->
			<div class="row">
				<div class="form-group col-md-12">
					<h2>
						<label for="cost">Cost</label>
					</h2>
					<div class="form-group">
						<h2>
							<input id="cost" name="cost" type="number" placeholder=""
								class="btn-default" value="100" required>
						</h2>
					</div>
				</div>
			</div>
			<!-- end row div -->
			<input type="submit" style="display: none" />
</form>
			<button id="SubmitButt" class="button">Submit</button>
		</fieldset>

		<fieldset id="field2" style="display: none">

			<!-- Form Name -->
			<h2>Submitted Reimbursements</h2>
			<hr />

			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="panel panel-default panel-table">
						<div class="panel-heading">
							<div class="row">
								<div class="col col-xs-6">
									<h3 class="panel-title">Your Reimbursement Requests</h3>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-list">
								<thead>
									<tr>
										<th>Type</th>
										<th>Location</th>
										<th>Approval Stage</th>
										<th>Estimated Amount</th>
									</tr>
								</thead>
								<tbody id="tableBody">
								</tbody>
							</table>

						</div>
						<div class="panel-footer">
							<div class="row">
								<div class="col col-xs-4">Page 1 of 5</div>
								<div class="col col-xs-8">
									<ul class="pagination hidden-xs pull-right">
										<li><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
									</ul>
									<ul class="pagination visible-xs pull-right">
										<li><a href="#">«</a></li>
										<li><a href="#">»</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



		</fieldset>



		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
		<script src="myScript.js"></script>
		<script src='https://code.responsivevoice.org/responsivevoice.js'></script>
	</div>
</body>
</html>