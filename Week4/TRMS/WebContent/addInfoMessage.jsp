<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	src = "https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js">
</script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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

<form class="form-horizontal" action = "AddInfoMessage" method ="POST">
		<fieldset>

			<!-- Form Name -->
			<legend>Request Additional Info</legend>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="addinfo">Reason for additional info</label>
				<div class="col-md-4">
					<textarea class="form-control" id="addinfo" name="addinfo" required
						placeholder="Explain why additional information is needed. Max 2000 characters."></textarea>
				</div>
			</div>
			
			<input id="addinfotoid" type ="hidden" value = "<%=request.getParameter("additonalinfo") %>" name = "addinfotoid">
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit">Submit
					Request </label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary" type ="submit">Submit</button>
				</div>
			</div>

		</fieldset>
	</form>

</body>
</html>