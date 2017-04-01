<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reimbursement Application</title>
</head>
<body>

	<form class="form-horizontal" action="AppFormServlet" method="POST">
		<fieldset>

			<!-- Form Name -->
			<legend>Reimbursement Application </legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="firstname">First
					Name</label>
				<div class="col-md-4">
					<input id="firstname" name="firstname" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="lastname">Last
					Name</label>
				<div class="col-md-4">
					<input id="lastname" name="lastname" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="location">Course
					Location</label>
				<div class="col-md-4">
					<input id="location" name="location" type="text" placeholder=""
						class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="enddate">End Date</label>
				<div class="col-md-4">
					<input id="enddate" name="enddate" type="text"
						placeholder="ex: 2017/12/29" class="form-control input-md"
						required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="startdate">Start
					Date</label>
				<div class="col-md-4">
					<input id="startdate" name="startdate" type="text"
						placeholder="ex: 2017/12/29" class="form-control input-md"
						required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="coursecost">Course
					Cost</label>
				<div class="col-md-4">
					<input id="coursecost" name="coursecost" type="text"
						placeholder="ex: 500.00" class="form-control input-md">

				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="courseduration">Course
					Duration (in hours)</label>
				<div class="col-md-4">
					<input id="courseduration" name="courseduration" type="text"
						placeholder="ex: 50" class="form-control input-md">

				</div>
			</div>
			<!-- Button Drop Down -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="selectcoursetype">Select
					course type</label>
				<div class="col-md-4">
					<select id="selectcoursetype" name="selectcoursetype"
						class="form-control">
						<option value="100">University course</option>
						<option value="101">Seminar</option>
						<option value="102">Certification prep course</option>
						<option value="103">Certification</option>
						<option value="104">Technical training</option>
						<option value="105">other</option>
					</select>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="coursetypetext">If
					other explain</label>
				<div class="col-md-4">
					<textarea class="form-control" id="coursetypetext"
						name="coursetypetext"></textarea>
				</div>
			</div>

			<!-- Button Drop Down -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="gradeformat">Select
					grading format</label>
				<div class="col-md-4">
					<select id="gradeformat" name="gradeformat" class="form-control">
						<option value="100">A-F</option>
						<option value="101">Pass fail</option>
						<option value="102">presentation</option>
						<option value="103">1-5</option>
						<option value="104">other</option>
					</select>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="gradeformattext">If
					other explain</label>
				<div class="col-md-4">
					<textarea class="form-control" id="gradeformattext"
						name="gradeformattext"></textarea>
				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="reason">Reason
					for application</label>
				<div class="col-md-4">
					<textarea class="form-control" id="reason" name="reason"></textarea>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="apply"></label>
				<div class="col-md-8">
					<button id="apply" name="apply" class="btn btn-success">Apply</button>

					<button id="cancel" name="cancel" class="btn btn-danger">Cancel</button>
				</div>
			</div>

		</fieldset>
	</form>


</body>
</html>