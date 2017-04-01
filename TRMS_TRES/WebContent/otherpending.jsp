<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.tres.objs.Reimbursement" %>
<html>
	<%@ include file="head.html" %>
	<body>
	<%@ include file="navbar.jsp" %>
		<title>Pending Reimbursements</title>
		<% ArrayList<Reimbursement> pend = (ArrayList<Reimbursement>)session.getAttribute("mypending"); %>
		<% Set<String> us_pend = (Set<String>)session.getAttribute("mybelows"); %>
		<ul class="nav nav-tabs">
		
		<%int i=0; for (String s : us_pend)
			{
			%>
	  			<li><a data-toggle="tab" href="#<%=s%>"><%=s%></a></li>
			<% 
			}
		%>
		</ul>
		<div class="tab-content">
  			<%for(String s : us_pend)
  				{
					//Reimbursement x = pend.get(i);
  			%>
  					<div id="<%=s%>" class="tab-pane fade" >

  					<ul class="nav nav-pills nav-stacked col-md-2">
						<% int c = 0; for (int j =0; j < pend.size(); j++)
							{
								Reimbursement y = pend.get(j);
								if ( y.getCreator().equals(s))
								{
									if (c ==0)
									{
									%>				
	  									<li class="active"><a href="#<%=y.getReimid()%>" data-toggle="pill"><%=y.getCoursedes()%></a></li>
									<% 
	  									c++;
									}
									else
									{
									%>
  										<li><a href="#<%=y.getReimid()%>" data-toggle="pill"><%=y.getCoursedes()%></a></li>
									<%
						
									}
								}
							}
						%>
					</ul>
					<div class="tab-content col-md-10">
						<%c =0;for (int j =0; j < pend.size(); j++)
							{
								Reimbursement y = pend.get(j);
								if ( y.getCreator().equals(s))
								{
									if (c ==0)
									{
									%>				
	  									<div class="tab-pane active" id="<%=y.getReimid()%>">
											<h4><%=y.getCoursedes()%></h4>
             						
									<% 
									c++;
									}
									else
									{
									%>
	        							<div class="tab-pane" id="<%=y.getReimid()%>">
											<h4><%=y.getCoursedes()%></h4>
									<% 
									}
									%>
             						<table class="table">
  										<thead>
  											<tr>
  												<th>Username</th>
  												<th>Course Description</th>
  												<th>Course Location</th>
  												<th>Cost </th>
  												<th>Attachment </th>
  												<th>Status </th>
  											</tr>
  										</thead>
  										<tbody>
  											<tr>
             								<td> <%=y.getCreator() %></td>
             									<td> <%=y.getCoursedes() %></td>
             									<td> <%=y.getLoc() %></td>
             									<td> <%=y.getCost() %></td>
             									<td> <%=y.getAttch() %></td>
             									<td> <%=y.getStatus() %></td>
  											</tr>
  										</tbody>
  									</table>
  													
		        					<%if ( y.getSid() !=2) {%>
										<!-- Indicates a successful or positive action -->
										<form action="<%=path%>Pending/approve" method="POST">
											<input type="submit" class="btn btn-success" value="Accept">
											<input type="hidden" name="reimid" value="<%=y.getReimid()%>">
											<input type="hidden" name="apprid" value="<%=y.getApprod()%>">
										</form>
										<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
										<button type="button"  data-toggle="modal" data-target="#AddInfoModal<%=y.getReimid() %>" class="btn btn-primary">Request Info</button>
												
										<!-- Indicates caution should be taken with this action -->
										<button type="button"  data-toggle="modal" data-target="#DeclineModal<%=y.getReimid() %>" class="btn btn-danger">Decline</button>
									<%} %>
										<!-- Modal -->
										<div class="modal fade" id="DeclineModal<%=y.getReimid() %>" role="dialog">
											<div class="modal-dialog">
											<!-- Modal content-->
												<div class="modal-content">
											    	<div class="modal-header">
											        	<button type="button" class="close" data-dismiss="modal">&times;</button>
											          	<h4 class="modal-title">Why Decline?</h4>
											        </div>
											       	<div class="modal-body">
											        	<form action="<%=path%>Pending/decline" method="POST">
											  				<div class="form-group">
											  					<label for="WhyDecline">Reason for decline: </label>
											  					<textarea required="required" name="reason" class="form-control" rows="3" id="WhyDecline"></textarea>
											  				</div>
											  				<input type="hidden" name="reimid" value="<%=y.getReimid()%>">
											  				<input type="hidden" name="rcvid" value="<%=y.getEmid()%>">
											        		<div class="modal-footer">
											  					<button type="submit" class="btn btn-danger">Decline</button>
													          	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											        		</div>
														</form>
											        </div>
											      </div>
											</div>
										</div>
										<!-- Modal -->
										<div class="modal fade" id="AddInfoModal<%=y.getReimid() %>" role="dialog">
											<div class="modal-dialog">
												<!-- Modal content-->
											    <div class="modal-content">
											    	<div class="modal-header">
											        	<button type="button" class="close" data-dismiss="modal">&times;</button>
											          	<h4 class="modal-title">Request More Info</h4>
											        </div>
											        <div class="modal-body">
											        	<form action="<%=path%>Pending/infoo" method="POST">
											  				<div class="form-group">
											  					<label for="exampleInputFile">File input</label>
											  					<input type="file" id="exampleInputFile">
											  				</div>
											  				<div class="form-group">
											  					<label for="infoComment">What you want:</label>
											  					<textarea class="form-control" rows="3" id="infoComment"></textarea>
											  				</div>
											        		<div class="modal-footer">
											  					<button type="submit" class="btn btn-info">Submit</button>
													          	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											        		</div>
														</form>
											        </div>
												</div>
											</div>
										</div>
										</div>
								<% 
								}
							}
							%>
						</div>		
					</div>
				<%}%>
		</div>
	</body>
</html>
