<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Approval Email</title>
</head>
<body>
	<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
		<li class="nav-item"><a class="nav-link" href="Controller">MAIN MENU</a></li>
				<li class="nav-item"><a class="nav-link" href="SignOut">SIGN OUT</a></li>
				
				
			</ul>

		</nav>


<h1>SEND APPROVAL EMAIL</h1>



<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<legend>Approve Email</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Approve Email">Approve Email</label>  
  <div class="col-md-5">
  <input id="Approve Email" name="Approve Email" type="text" placeholder="Enter Email You Wish To Approve" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submitapprove"></label>
  <div class="col-md-4">
    <button id="submitapprove" name="submitapprove" class="btn btn-primary">Submit Approval</button>
  </div>
</div>

</fieldset>
</form>



</body>
</html>