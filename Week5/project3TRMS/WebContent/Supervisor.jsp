<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="form-horizontal" action = "appForm.jsp" method="post">
		<fieldset>
			<!-- Form Name -->
			<legend>Welcome to the supervisor page.</legend>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="newrequest" >New
					Request</label>
				<div class="col-md-4">
					<button id="newrequest" name="newrequest" class="btn btn-primary" type="submit" formaction="appForm.jsp"  value="/appForm.jsp">open</button>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="message" >Messages</label>
				<div class="col-md-4">
					<button id="message" name="message" class="btn btn-primary"  formaction="messages.jsp"  value="/messages.jsp">open</button>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="viewappstatis">View
					current applications</label>
				<div class="col-md-4">
					<button id="viewappstatis" name="viewappstatis"
						class="btn btn-primary">open</button>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="reportstoapplicants">View
					pending request</label>
				<div class="col-md-4">
					<button id="reportstoapplicants" name="reportstoapplicants"
						class="btn btn-primary"  formaction="appApproval.jsp"  value="/appApproval.jsp">view</button>
				</div>
			</div>

		</fieldset>
	</form>
</body>
</html>