<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


	<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
	<%@include file="header.jsp"%>
	<body>	
		<%{
			session.setAttribute("uId", request.getAttribute("id"));
		}%>
		
		<br><br><br><br><br><br>
		
		<div class="row text-center">
			  <div class="col-md-4">
			  	<form action="CreateReimbursement.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Apply for a reimbursement</button>
			  	</form>
			  </div>
			  <div class="col-md-4">
			  	<form action="GetApprovedReimbursements.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Approved Reimbursements</button>
			  	</form>
			  </div>
			  <div class="col-md-4">
			  	<form action="ReimbursementsServlet.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Pending Reimbursements</button>
			  	</form>
			  </div>
		</div><br><br><br><br>
		<%{
			session.setAttribute("roleId", request.getAttribute("roleId"));
			session.setAttribute("deptId", request.getAttribute("deptId"));
		}%>
		<c:choose>
			<c:when test="${roleId == 2}">
				<div class="row">
					<div class="col-md-5"></div>
					<div class="col-md-2">
						<form action="PendingReimbursementsServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Pending Reimbursements</button>
					  	</form>
					</div>
					<div class="col-md-5"></div>
				</div>
			</c:when>
			<c:when test="${roleId == 3}">
				<div class="row">
					<div class="col-md-5"></div>
					<div class="col-md-2">
					  	<form action="PendingReimbursementsServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Pending Reimbursements</button>
					  	</form>
					</div>
					<div class="col-md-5"></div>
				</div>
			</c:when>
			<c:when test="${deptId == 1}">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-3">
					  	<form action="PendingReimbursementsServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Pending Reimbursements</button>
					  	</form>
					</div>
					<div class="col-md-3">
					  	<form action="FinalApprovalServlet.do" method="POST">
					  		<button type="submit" class="btn btn-default btn-lg">Final Approvals</button>
					  	</form>
					</div>
					<div class="col-md-3"></div>
				</div>
			</c:when>
		</c:choose><br><br><br><br>
		
		<div class="row text-center">
			  <div class="col-md-6">
			  	<form action="GetPaidReimbursements.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Paid Reimbursements</button>
			  	</form>
			  </div>
			  <div class="col-md-6">
			  	<form action="GetDeclinedReimbursements.do" method="POST">
			  		<button type="submit" class="btn btn-default btn-lg">Declined Reimbursements</button>
			  	</form>
			  </div>
		</div>
	
		
	
	</body>
</html>