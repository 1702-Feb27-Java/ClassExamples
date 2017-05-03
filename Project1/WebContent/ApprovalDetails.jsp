<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.revature.trms.*" %>
<%@ page import="com.revature.dao.DAOImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Details Approval</title>
<style>
	input {text-align:center; align:center; border:none; font-size:16px}
	th {text-align:center; background-color:#ff6600; color:white;}
	td {text-align:center;}
	.button {text-align:center;background-color:#ff6600; 
			color:white; font-size: 16px}
</style>
</head>
<body>
	<%
	ArrayList<Event> eventList = (ArrayList<Event>)session.getAttribute("eventPr");
	int eventId = Integer.parseInt(request.getParameter("eventId"));
	ArrayList<Tracking> detailsList = DAOImpl.getTrackingDetails(eventId);
	ArrayList<User> detailUser = DAOImpl.getTrackingDetails2(eventId);
	for (int i = 0; i<eventList.size();i++){
		if (eventList.get(i).getEventId() == eventId)	
			eventId=i;
	}
	%>
	
	<div align="center">
		<form = action="ApproveEvent" method="POST">
			<table width=100%>
				<tr><th style="font-size:35px">EVENT DETAILS APPROVAL PAGE</th></tr>
				<tr><th>EVENT #:</th></tr>
				<tr><td style="text-align: center"><input name="eventNum" value="<%=eventList.get(eventId).getEventId()%>"></td></tr>
			</table>
			<table width=100%>
				<tr><th>EVENT:</th><th>PRIORITY:</th><th>LOCATION:</th><th>COST:</th></tr>
				<tr><td><input type="text" name="EventType" value="<%=eventList.get(eventId).getEventType()%>"></td>
					<td><input type="text" name="Priority" value="<%=eventList.get(eventId).getPriority()%>"></td>
					<td><input type="text" name="Location" value="<%=eventList.get(eventId).getLocation()%>"></td>
					<td><input type="text" name="Cost" value="<%=eventList.get(eventId).getCost()%>"></td></tr>
				<tr><th>START DATE:</th><th>START TIME:</th><th>STOP DATE:</th><th>GRADE FORMAT:</th></tr>
				<tr><td><input type="text" name="Start" value="<%=eventList.get(eventId).getStartDate()%>"></td>
					<td><input type="text" name="STime" value="<%=eventList.get(eventId).getStartTime()%>"></td>
					<td><input type="text" name="Stop" value="<%=eventList.get(eventId).getStopDate()%>"></td>
					<td><input type="text" name="Grade" value="<%=eventList.get(eventId).getGradeFormat()%>"></td></tr>
			</table>
			<table width=100%>
			<tr><th>DESCRIPTION:</th></tr>
				<tr><td><input name="descr" size=170% style="border:none; align:left; text-align:left" value="<%=eventList.get(eventId).getDescription()%>"></td></tr>
				<tr><th>JUSTIFICATION:</th></tr>
				<tr><td><input name="jusify" size=170% style="border:none; align:left; text-align:left" value="<%=eventList.get(eventId).getJustify()%>"></td></tr>
			</table>
			<table width=100%>
			<tr><th colspan="6">ROUTING DETAILS:</th></tr>
					<tr><th>TRACKINGID:</th><th>DATE OF ACTION:</th><th>STATUS:</th><th>LOCATION:</th>
					<th>PERSON:</th><th>COMMENTS</th></tr>
				<%for (int i = 0; i < detailsList.size();i++){ %>
						<td><%=detailsList.get(i).getEventDate()%></td>
						<td><%=detailsList.get(i).getStatus()%></td>
						<td><%=detailsList.get(i).getRoleId()%></td>
						<td><%=detailUser.get(i).getFirstName()%></td>
						<td><%=detailsList.get(i).getComments()%></td></tr>
				<%}%>
			<tr><th>APPROVE / DISAPPROVE</th><th name="comments" colspan="7" required>APPROVAL COMMENTS:</th></tr>
			<tr><td colspan="1"><select name="approve" style="border:none;text-align:left" required>
				<option disabled selected value> -- select an option -- </option>
				<option value="1">Approve</option>
				<option value="2">Deny</option>
			</select></td>			
			<td colspan="6"><input name="comments" size="100%" style="border:none;text-align:left" required></td></tr>			
		</table><br>						
			<input class="button" type="submit" value="Submit" style="width: 100px">
		</form>
	</div>
</body>
</html>