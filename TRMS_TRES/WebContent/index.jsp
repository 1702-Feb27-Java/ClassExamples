<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="head.html" %>
		<title>Login Page</title>
	</head>
	<body>
		<% session.invalidate(); %>
		<div class="container">
		    <div class="row vertical-offset-100">
		        <div class="col-md-4 col-md-offset-4">
		    		<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h2 class="panel-title">Tuition Reimbursement Management System</h2>
					 	</div>
					  	<div class="panel-body">
							<form action="Login" method="POST" >
		                    <fieldset>
					    	  	<div class="form-group">
					    		    <input class="form-control" placeholder="Username" name="uname" class="form-control" type="text">
					    		</div>
					    		<div class="form-group">
					    			<input class="form-control" placeholder="Password" name="password" type="password">
					    		</div>
					    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
					    	</fieldset>
					      	</form>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>