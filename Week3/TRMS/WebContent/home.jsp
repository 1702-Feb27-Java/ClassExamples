<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home Page</title>
</head>
<body>
	<% HttpSession currSession = request.getSession();
		currSession.invalidate(); %>
	<!-- Space reserved for header -->
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
	</div>
	<!-- Breadcrumb container -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<ul class="breadcrumb">
					<li class="active">Home</li>
				</ul>
			</div>
		</div>
	</div>
	<br>
	<!-- Main Page container -->
	<div class="container">
		<div class="row fluid">
			<!-- Creating a login form -->
			<div class="col-md-5">
				<div class="loginpanel" style="padding-left: 30px">
					<div class="wrapper" style="width: 400px">
						<form class="form-signin" action="LoginServlet" method="POST">
							<h2 class="form-signin-heading">Login</h2>
							<input type="text" class="form-control" name="username"
								placeholder="Username" maxlength="25" /> <input type="password"
								class="form-control" name="password" placeholder="Password"
								maxlength="25" /> <label class="checkbox"> <input
								type="checkbox" value="remember-me" id="rememberMe"
								name="rememberMe"> Remember me
							</label>
							<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<div class="signuppanel">
					<h2>Reimbursement Workflow Overview</h2>
					<ol
						style="padding: 0px; margin: 0px 0px 10px 2.5em; color: rgb(51, 51, 51); font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 14px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 20px; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255);">
						<li>Log in with proper credentials</li>
						<li>Choose options from login menu</li>
						<li>Each employee views related reimbursements information</li>
						<li>Supervisors see reimbursement details in their queue</li>
						<li>Supervisor can approve/decline reimbursements</li>
						<li>Reimbursement details then sent up to further
							supervisors/department heads (if necessary)</li>
						<li>Applicant may view/add case details depending on
							reimbursement status</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<br>
	<!-- Space reserved for footer -->
	<footer>
	<hr>
	<div class="container">
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
	</footer>
</body>


</html>