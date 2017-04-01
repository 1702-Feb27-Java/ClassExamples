<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reimbursement Complete</title>
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
				  <!-- TODO: link to LogoutServlet -->
				  <li><a href="home.jsp">Home </a></li>
				  <span class="arrow sep">►</span>
				  <li><a href="employee_menu.jsp">Menu</a></li>
				  <span class="arrow sep">►</span>
				  <li class="active">Reimbursement Applied</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
	<div class="row text-center">
        <div class="col-sm-6 col-sm-offset-3">
        <br><br> <h2 style="color:#0fad00">Success</h2>
        <img src="http://www.freeiconspng.com/uploads/success-icon-10.png" width="200" height="200">
        <h3>Congratulations, <%= session.getAttribute("firstname") %> <%= session.getAttribute("lastname")%></h3>
        <p style="font-size:20px;color:#5C5C5C;">Reimbursement Successfully Applied</p>
        <a href="employee_menu.jsp" class="btn btn-success">Back to Menu</a>
    <br><br>
        </div>
        
	</div>
</div>

</body>
</html>