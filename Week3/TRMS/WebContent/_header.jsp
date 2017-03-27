<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<nav class="navbar navbar-default">
  <div class="container-fluid">
	  <!--  -->
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <a class="navbar-brand" href="login.jsp">
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
		            <li><a href="#">Action</a></li>
		            <li><a href="#">Another action</a></li>
		            <li><a href="#">Something else here</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">Separated link</a></li>
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