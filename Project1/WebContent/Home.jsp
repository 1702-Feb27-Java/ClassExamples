<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.trms.*" %>
<%@ page import="com.revature.dao.DAOImpl" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	body {background-color:#ebebe0; color:#002266}
	table {width:100%;}
	.left {text-align: left; font-size: 15px; padding: 10px;}
	.center {text-align: center; font-size: 15px; padding: 10px;}
	th {font-weight: bold}
	hr{height:2px;border:solid;color:#002266; background-color:#002266}
	
</style>
<title>Home</title>
</head>
<body>
	<div align="right" style="padding-right:50px">
		<a href="UnderConstruction.html">Edit Personal Settings</a>
	</div>
	<%
	int userid = (int)session.getAttribute("userid");
	User u = UserService.getUserInfo(userid);
	String role = u.getRole();
	%>
	<h1>Welcome <%=" " + u.getFirstName() + " " + u.getLastName()%></h1>
	<hr><h2>Amount Available: $	 
	<%=u.getAmount()%><br></h2>
	<hr>
	<h2>REQUESTS:</h2><hr>
	<% 
		ArrayList<Event> eventList = DAOImpl.getEventStats(userid);
		ArrayList<Tracking> tList = DAOImpl.getTrackingStats(userid);
		ArrayList<String> sList = DAOImpl.getSupervisorStats(userid);
		session.setAttribute("eventList", eventList);
		session.setAttribute("trackingList", tList);
		session.setAttribute("suplist", sList);
	%>
	<form action="Details.jsp" method="POST">
		<table>
			<tr><th = class="center">EVENT-ID:</th><th class="left">EVENT:</th><th class="center">START DATE:</th><th class="center">START TIME:</th><th class="center">STOP DATE:</th>
				<th>LOCATION:</th><th class="center">COST:</th><th class="left">STATUS:</th><th class="left">CURRENTLY AT:</th></tr>
				<% for (int i = 0; i < eventList.size();i++){%>
				<tr>
					<td class="center"><%=eventList.get(i).getEventId()%></td>
					<td class="left"><%=eventList.get(i).getEventType()%></td>
					<td class="center"><%=eventList.get(i).getStartDate()%></td>
					<td class="center"><%=eventList.get(i).getStartTime()%></td>
					<td class="center"><%=eventList.get(i).getStopDate()%></td>
					<td class="left"><%=eventList.get(i).getLocation()%></td>
					<td class="center">$<%=eventList.get(i).getCost()%></td>
					<td class="left"><%=tList.get(i).getStatus()%></td>
					<td class="left"><%=sList.get(i)%></td>
				</tr>
			<%}%>
		</table><br><br>
		To view / delete the details of any request listed, select from the drop down menu:
		<select name="eventId" required>
		<option disabled selected value> -- select an option -- </option>
		<%for(Event item:eventList){	%>
			<option value="<%=item.getEventId()%>">
			  <%=item.getEventId()%></option>
		<%}%>
		</select>
		<input type="submit" value="Details"><hr>
	</form>
	
	<div align="center"><form action="EventForm.jsp">
		<input type="submit" value="Add New Event">
	</form></div><hr>
	
	<form action="ApprovalDetails.jsp" method="POST">
	<%
	ArrayList<Event> prList = DAOImpl.getPendingRequests(userid);
	ArrayList<User> prList2 = DAOImpl.getPendingRequests2(userid);
	session.setAttribute("eventPr", prList);
	session.setAttribute("userPr", prList2);
	
	if(!role.equals("4")){ %>
	<h2>PENDING REQUESTS:</h2><hr>
		<table>
			<tr><th = class="center">EVENT-ID:</th><th class="left">NAME:</th><th class="left">TYPE:</th><th class="center">COST:</th><th class="center">START DATE:</th>
				<th>STOP DATE:</th><th class="center">PRIORITY:</th></tr>
				<% for (int i = 0; i < prList.size();i++){%>
				<tr>
					<td class="center"><%=prList.get(i).getEventId()%></td>
					<td class="left"><%=prList2.get(i).getFirstName()%></td>
					<td class="left"><%=prList.get(i).getEventType()%></td>
					<td class="center">$<%=prList.get(i).getCost()%></td>
					<td class="center"><%=prList.get(i).getStartDate()%></td>
					<td class="center"><%=prList.get(i).getStopDate()%></td>
					<td class="center"><%=prList.get(i).getPriority()%></td>
				</tr>
				<%}%>
		</table>
		<br><br>
		To view and approve the request listed, select from the drop down menu:
		<select name="eventId" required>
		<option disabled selected value> -- select an option -- </option>
		<%for(Event item:prList){	%>
			<option value="<%=item.getEventId()%>">
			  <%=item.getEventId()%></option>
		<%}%>
		</select>
		<input type="submit" value="Details"><hr>
	<%}%>
	</form>
</body>
</html>
	