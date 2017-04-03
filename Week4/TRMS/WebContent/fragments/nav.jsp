<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">TRMS</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	<c:if test="${loggedInUser != null}">
      <ul class="nav navbar-nav">
        <li class=""><a href="Reimbursement">List Reimbursements</a></li>
      	<li class=""><a href="ListPendingReimbursements">Approve Reimbursements</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li><a href ="NewReimbursement">Apply For Reimbursement</a></li>
      	<li><a href = "Logout">Logout</a></li>
      </ul>
      </c:if>
      <c:if test="${loggedInUser == null}">
      <ul class="nav navbar-nav navbar-right">
      	<li><a href = "Login">Login</a></li>
      </ul>
      </c:if>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>