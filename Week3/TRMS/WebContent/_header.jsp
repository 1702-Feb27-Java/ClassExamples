<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" /> -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0-rc.6/js/bootstrap-datepicker.min.js" crossorigin="anonymous"></script>
<script src="resources/reimb_form.js"></script>
<script src="resources/click_row.js"></script>

<nav class="navbar navbar-default">
  <div class="container-fluid">
	  <!--  -->
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <!-- if session employee exists, employee_menu.jsp, else home -->
	      <a class="navbar-brand" href="home.jsp">
			Tuition Reimbursement Management System
	      </a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <c:if test="${sessionScope.employee != null}">
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		        <li class="dropdown" style="text-align:center">
		          <a href="#" style="width:165px" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <%= session.getAttribute("firstname") %> <%= session.getAttribute("lastname")%> 
		         
		          <span>&nbsp;</span>
		          <span class="glyphicon glyphicon-user"></span>
		          <span class="caret"></span>
		          </a>
		          <ul class="dropdown-menu" style="width:100%">
		            <li><a href="employee_menu.jsp">Profile</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="home.jsp">Log Out</a></li>
		          </ul>
		        </li>
		        <li role="presentation">
		 			<a href="#">Messages 
						<span class="badge">3</span>
					</a>
				</li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </c:if>
    <!--  -->
  </div><!-- /.container-fluid -->
</nav>