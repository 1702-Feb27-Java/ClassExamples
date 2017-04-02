<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


	<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
	<%@include file="header.jsp"%>
	<body>	
		<%{
			session.setAttribute("uId", request.getAttribute("id"));	
			session.setAttribute("roleId", request.getAttribute("roleId"));
			session.setAttribute("deptId", request.getAttribute("deptId"));
			
		}%>
		
		<br><br><br><br><br><br>
		
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<div class="btn-group-vertical">
				View Reimbursements 
				  <br><form action="ReimbursementsServlet.do" method="POST">
			  			<button type="submit" class="btn btn-default btn-lg">Pending</button>
			  	  </form><br>
				  <form action="GetApprovedReimbursements.do" method="POST">
				  		<button type="submit" class="btn btn-default btn-lg">Approved</button>
				  </form><br>
				  <form action="GetDeclinedReimbursements.do" method="POST">
			  			<button type="submit" class="btn btn-default btn-lg">Declined</button>
			  	  </form><br>
				  <form action="GetPaidReimbursements.do" method="POST">
			  			<button type="submit" class="btn btn-default btn-lg">Processed and Paid</button>
			  	  </form>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				Apply for Reimbursement
				<form action="CreateReimbursement.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Apply</button>
			  	</form>
			</div>
			<div class="col-md-4">
				<c:choose>
					<c:when test="${roleId == 2}">
						Welcome Supervisor
						<form action="PendingReimbursementsServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Pending</button>
					  	</form>
					</c:when>
					<c:when test="${roleId == 3}">
						Welcome Department Head
						<form action="PendingReimbursementsServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Pending</button>
					  	</form>
					</c:when>
					<c:when test="${deptId == 1}">
						<div class="btn-group-vertical">
						Welcome Benefits Coordinator
						  <br><form action="PendingReimbursementsServlet.do" method="POST">
						  		<button type="submit" class="btn btn-default btn-lg">Pending</button>
						  </form><br>
						  <form action="FinalApprovalServlet.do" method="POST">
						  		<button type="submit" class="btn btn-default btn-lg">Final Approval</button>
						  </form>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		
	
		
	
	</body>
</html>