<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
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

<style>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    /* display: none; <- Crashes Chrome on hover */
    -webkit-appearance: none;
    margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
}

</style>
<title>Sign up</title>
</head>
<body>

	<form id = "signUp" class="form-horizontal" action = "Signup" method = "POST">
		<fieldset>

			<!-- Form Name -->
			<legend>TRMS Sign Up</legend>
			
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="employeeid">Employee ID</label>
				<div class="col-md-5">
					<input id="empid" name="empid" type="number"
						placeholder="Employee ID" class="form-control input-md" required>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-5">
					<input id="email" name="email" type="email" placeholder="Email"
						class="form-control input-md" required>

				</div>
			</div>
			
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="paypal">Username</label>
				<div class="col-md-5">
					<input id="username" name="username" type="text"
						placeholder="Username" class="form-control input-md" required>
				</div>
			</div>


			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-5">
					<input id="password" name="password" type="password" placeholder="******"
						class="form-control input-md" required>
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="retypepassword">Re-Enter Password</label>
				<div class="col-md-5">
					<input id="retypepassword" name="retypepassword" type="password" placeholder="******"
						class="form-control input-md" required="true">
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<label class="checkbox-inline" for="submit"> 
					<input id ="submit"
						class="btn btn-success" name="submit" id="submit"
						value="Create New Account" type ="submit">
					</label>
				</div>
			</div>

		</fieldset>
	</form>
	
	<div id ="wrongInputs" >
	<ul>
	<% if (session.getAttribute("isEmployee") != null && (boolean)session.getAttribute("isEmployee") == false){
	%>
		<li>Employee with that ID is not registered as an employee</li>
	<% session.setAttribute("isEmployee", null);
	} %>
	<% if (session.getAttribute("empidInUse") != null && (boolean)session.getAttribute("empidInUse") == true){
	%>
		<li>Employee with that ID already has an account</li>
	<% session.setAttribute("empidInUse", null);
	} %>
	<% if (session.getAttribute("userInUse") != null && (boolean)session.getAttribute("userInUse") == true){
	%>
		<li>Username is taken</li>
	<%session.setAttribute("userInUse", null);
	} %>
	<% if (session.getAttribute("emailInUse") != null && (boolean)session.getAttribute("emailInUse") == true){
	%>
		<li>Email already has an associated account</li>
	<%session.setAttribute("emailInUse", null);
	} %>
	<% if (session.getAttribute("passMatch") != null && (boolean)session.getAttribute("passMatch") == false){
	%>
		<li>Passwords do not match</li>
	<%session.setAttribute("passMatch", null);
	} %>
		
	
	
	</ul>
	</div>
	
 	<script>
<%-- 		
		$(document).ready(function(){
			
			var password;
			var retypepassword;
			var username;
			var userExists;
			username = $('#username').val();
			<%@ page import = "com.trms.dao.TrmsDao" %>
			
			userExists = <%= Trms.Dao.userExists() %>
			
			$('#submit').click(function(){
				$('#wrongInputs').empty();
				
				password = $('#password').val();
				retypepassword = $('#retypepassword').val();

				
				if(password != retypepassword){
					$('#wrongInputs').append("wrong pass");
				}else{
					$('#wrongInputs').append("goodjob");
				}
				
				
			})
			
		})
	 --%>
	
	
	</script> 



</body>
</html>