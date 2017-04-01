<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.revature.trms.*" %>
<%@ page import="com.revature.dao.DAOImpl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Details</title>
<style>
	input {text-align:center; align:center; border:none}
	th {text-align:center;border:1pt solid gray; background-color:#3199a3; color:white;}
	td {text-align:center;border:1pt solid gray}
	.button {text-align:center;border:1pt solid gray; background-color:#3199a3; 
			color:white; font-size: 16px}
</style>
</head>
<body>
	<%
	ArrayList<Event> eventList = (ArrayList<Event>)session.getAttribute("eventList");
	int eventid = Integer.parseInt(request.getParameter("eventId"));
	ArrayList<Tracking> detailsList = DAOImpl.getTrackingDetails(eventid);
	ArrayList<User> detailUser = DAOImpl.getTrackingDetails2(eventid);
	for (int i = 0; i<eventList.size();i++){
		if (eventList.get(i).getEventId() == eventid)	
			eventid=i;
	}
	session.setAttribute("eventNewId", eventid);
	%>
	
	<div align="center">
		<form = action="DeleteEvent" method="POST">
			<table width=100%>
			<tr><th style="font-size:35px">EVENT DETAILS PAGE</th></tr>
				<tr><th>EVENT #:</th></tr>
				<tr><td style="text-align: center"><input name="eventNum" value="<%=eventList.get(eventid).getEventId()%>"></td></tr>
			</table>
			<table width=100%>
				<tr><th>EVENT:</th><th>PRIORITY:</th><th>LOCATION:</th><th>COST:</th></tr>
				<tr><td><select name="EventType" style="width:300px; border:none" value="<%=eventList.get(eventid).getEventType()%>">
							<option value="1">University Course</option>
	  						<option value="2">Seminar</option>
	  						<option value="3">Certification Prep Course</option>
	  						<option value="4">Certification</option>
							<option value="5">Technical Training</option>
	  						<option value="6">Other</option>
						</select></td>
					<td><input type="text" name="Priority" value="<%=eventList.get(eventid).getPriority()%>"></td>
					<td><input type="text" name="Location" value="<%=eventList.get(eventid).getLocation()%>"></td>
					<td><input type="text" name="Cost" value="<%=eventList.get(eventid).getCost()%>"></td></tr>
				<tr><th>START DATE:</th><th>START TIME:</th><th>STOP DATE:</th><th>GRADE FORMAT:</th></tr>
				<tr><td><input type="text" name="Start" value="<%=eventList.get(eventid).getStartDate()%>"></td>
					<td><input type="text" name="STime" value="<%=eventList.get(eventid).getStartTime()%>"></td>
					<td><input type="text" name="Stop" value="<%=eventList.get(eventid).getStopDate()%>"></td>
					<td><select name="Grade" style="width:300px; border:none" value="<%=eventList.get(eventid).getGradeFormat()%>">
				<option value="1">Pass/Fail</option>
	  			<option value="2">Grades A - F</option>
	  			<option value="3">Presentation</option>
	  			<option value="4">Other</option>
			</select></td></tr>
			</table>
			<table width=100%>
			<tr><th>DESCRIPTION:</th></tr>
				<tr><td><input name="descr" size=170% style="border:none; align:left; text-align:left" value="<%=eventList.get(eventid).getDescription()%>"></td></tr>
				<tr><th>JUSTIFICATION:</th></tr>
				<tr><td><input name="jusify" size=170% style="border:none; align:left; text-align:left" value="<%=eventList.get(eventid).getJustify()%>"></td></tr>
			</table>
			<table width=100%>
			<tr><th colspan="6">ROUTING DETAILS:</th></tr>
					<tr><th>TRACKINGID:</th><th>TIMESTAMP:</th><th>STATUS:</th><th>LOCATION:</th>
					<th>PERSON:</th><th>COMMENTS</th></tr>
				<%for (int i = 0; i < detailsList.size();i++){ %>
					<tr><td><%=detailsList.get(i).getTrackingId()%></td>
						<td><%=detailsList.get(i).getEventDate()%></td>
						<td><%=detailsList.get(i).getStatus()%></td>
						<td><%=detailsList.get(i).getRoleId()%></td>
						<td><%=detailUser.get(i).getFirstName()%></td>
						<td><%=detailsList.get(i).getComments()%></td></tr>
				<%}%>
			</table><br>
			<input type="radio" name="event" value="edit">Edit&nbsp
			<input type="radio" name="event" value="delete">Delete<br><br>						
			<input class="button" type="submit" value="Submit" style="width: 100px">
		</form>
	</div>
</body>
</html>