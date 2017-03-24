<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<%@include file="header.jsp" %>
		
		<body>
		
		<% if(request.getParameter("uname")!=null){
			session.setAttribute("username", request.getParameter("uname"));	
		}%>
		<% if(request.getParameter("pword")!=null){
			session.setAttribute("password", request.getParameter("pword"));	
			request.setAttribute("id", null);
		}%>
		<br><br>
		<h1>Tuition Reimbursement System</h1>
		<br><br><br><br><br><br><br><br><br><br><br>
		<div class="container" >
		  <form action="LoginServlet.do" method="POST">
			<div class="form-group row">
			  <label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
			  <div class="col-sm-10">
				<input class="form-control" type="text" placeholder="Username" name="uname">
			  </div>
			</div>
			<div class="form-group row">
			  <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
			  <div class="col-sm-10">
				<input class="form-control" type="text" placeholder="Password" name="pword">
			  </div>
			<br><br><br>
			<div class="form-group row">
			  <div class="offset-sm-2 col-sm-10">
				<button type="submit" class="btn btn-primary button" name="Submit login">Sign in</button>
			  </div>
			</div>
		  </form>
		 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:if test="${sessionScope.uId == 0}">
			<c:if test="${sessionScope.uId != null}">
			    <div class="alert alert-danger alert-dismissable fade in">
				    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				    <strong>Login failed!</strong> Incorrect login information.
				  </div>
			</c:if>
		</c:if>
		</div>

		  
	
	
		
	
	</body>
</html>