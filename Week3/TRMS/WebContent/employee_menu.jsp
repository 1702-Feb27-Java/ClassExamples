<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				  <!-- TODO: link to Logout Servlet -->
				  <li><a href="#">Home</a></li>
				  <span class="arrow sep">►</span>
				  <li class="active">Menu</li>
				  <span class="arrow sep">►</span>
				</ul>
			</div>
		</div>
	</div>
	<br>
	<!-- Nav Container -->
 	<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<div>
		
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
		    <li role="presentation"><a href="#pending" aria-controls="pending" role="tab" data-toggle="tab">Pending Reimbursements</a></li>
		    <li role="presentation"><a href="#awarded" aria-controls="awarded" role="tab" data-toggle="tab">Awarded Reimbursements</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="profile">	<!-- See Employee Details / Available reimbursements / date -->
				<!-- Employee details and apply for reimbursement button --> 
				<div class="container-fluid">
			
						<a href="ReimbursementServlet" class="btn btn-default">Apply for a New Reimbursement</a></div>
			
				</div> <!-- end of Employee Profile--> <!-- use %include file instead of jsp:include because of request scope -->
		    <div role="tabpanel" class="tab-pane" id="pending"><jsp:include page="pending_reimbursements.jsp"></jsp:include><!-- JSP INCLUDE PENDING REIMB --></div>
		    <div role="tabpanel" class="tab-pane" id="awarded"><jsp:include page="awarded_reimbursements.jsp"></jsp:include><!-- JSP INCLUDE AWARDED REIMB --></div>
		  </div>
		
		</div>
	</div>
	<div class="col-md-1"></div>
	</div>

	<br><br><br>

	<!-- Space reserved for footer -->
	<footer>
	<hr>
	<div class="container">
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
	</footer>
</body>
</html>