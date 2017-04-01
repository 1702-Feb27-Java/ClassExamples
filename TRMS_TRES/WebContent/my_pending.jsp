<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tres.objs.Reimbursement" %>
<%@ page import="com.tres.objs.Message" %>
<html>
	<%@ include file="head.html" %>
	<%@ include file="navbar.jsp" %>
		<title>Pending Reimbursements</title>
	</head>
	<body>	
		<% ArrayList<Reimbursement> pend = (ArrayList<Reimbursement>)session.getAttribute("mypending"); %>
		<ul class="nav nav-pills nav-stacked col-md-2">
		<% String btnType="";for (int i =0; i < pend.size(); i++)
			{
				Reimbursement x = pend.get(i);
				switch (x.getSid())
				{
					case 1:
						btnType ="info";
						break;
					case 2:
						btnType ="danger";
						break;
					case 3:
						btnType ="success";
						break;
				}
				if (i ==0)
				{
					%>				
	  				<li class="active">
	  					<a href="#<%=x.getCoursedes()%>" data-toggle="pill"><%=x.getCoursedes()%> 
	  						<button type="button" class="btn btn-<%=btnType%>" > <%=x.getStatus()%></button>
	  					</a> 
	  				</li>
					<% 
				}
				else
				{
				%>
  					<li>
  						<a href="#<%=x.getCoursedes()%>" data-toggle="pill"><%=x.getCoursedes()%>
	  						<button type="button" class="btn btn-<%=btnType%>" > <%=x.getStatus()%></button>
  						</a>
  					</li>
				<% 
				}
			}
		%>
		</ul>
		<div class="tab-content col-md-10">
		<% for (int i =0; i < pend.size(); i++)
			{
				Reimbursement x = pend.get(i);
				if (i ==0)
				{
					%>				
	  				<div class="tab-pane active" id="<%=x.getCoursedes()%>">
             			<h4><%=x.getCoursedes()%></h4>
					<% 
				}
				else
				{
					%>
	        		<div class="tab-pane" id="<%=x.getCoursedes()%>">
             			<h4><%=x.getCoursedes()%></h4>
				<% 
				}
				%>
					<table class="table">
  						<thead>
  							<tr>
  								<th>Course Description</th>
  								<th>Course Location</th>
  								<th>Cost </th>
  								<th>Attachment </th>
  								<th>Status </th>
  							</tr>
  						</thead>
  						<tbody>
  							<tr>
             					<td> <%=x.getCoursedes() %></td>
             					<td> <%=x.getLoc() %></td>
             					<td> <%=x.getCost() %></td>
             					<td> <%=x.getAttch() %></td>
             					<td> <%=x.getStatus() %></td>
  								</tr>
  						</tbody>
  					</table>
					<!-- Indicates a successful or positive action -->
					<button type="button"  data-toggle="modal" data-target="#MSGmodal<%=x.getReimid() %>" class="btn btn-primary">Messages</button>
					<!-- Modal -->
					<div class="modal fade" id="MSGmodal<%=x.getReimid()%>" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									 	<h4 class="modal-title">List of Messages</h4>
								</div>
						    	<div class="modal-body">
						    		<%if(x.getMsgs().isEmpty()){%>
									<div class="alert alert-warning" role="alert">
										<h5>No messages</h5>
									</div>
									<%}else{
										for( Message m : x.getMsgs()){%>
										<div class="panel panel-<%=btnType%>">
      										<div class="panel-heading"><strong><%=m.getSndname() %></strong></div>
      										<div class="panel-body"><%=m.getMsg()%></div>
    									</div>
									<%}}%>
								</div>
					    	</div>
					  	</div>
					</div>
					</div>
				<% 
			}
		%>
	</body>
</html>