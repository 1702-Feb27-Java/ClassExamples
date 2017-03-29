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
	input {text-align:center; align:center; border:none}
	th {text-align:center;border:1pt solid gray; background-color:#3199a3; color:white;}
	td {text-align:center;border:1pt solid gray}
	.button {text-align:center;border:1pt solid gray; background-color:#3199a3; 
			color:white; font-size: 16px}
</style>
</head>
<body>
	<%
	ArrayList<Event> eventList = (ArrayList<Event>)session.getAttribute("eventPr");
	int eventid = Integer.parseInt(request.getParameter("eventIdApproval"));
	ArrayList<Tracking> detailsList = DAOImpl.getTrackingDetails(eventid);
	ArrayList<User> detailUser = DAOImpl.getTrackingDetails2(eventid);
	for (int i = 0; i<eventList.size();i++){
		if (eventList.get(i).getEventId() == eventid)	
			eventid=i;
	}
	%>
	
	<div align="center"><h1>Event Details Approval Page</h1>
		<form = action="" method="POST">
			<table width=100%>
				<tr><th>EVENT #:</th></tr>
				<tr><td style="text-align: center"><input name="eventNum" value="<%=eventList.get(eventid).getEventId()%>"></td></tr>
			</table>
			<table width=100%>
				<tr><th>EVENT:</th><th>PRIORITY:</th><th>LOCATION:</th><th>COST:</th>
				<th>START DATE:</th><th>TIME:</th><th>STOP DATE:</th><th>GRADING FORMAT:</th></tr>
				<tr><td><input type="text" name="EventType" value="<%=eventList.get(eventid).getEventType()%>"></td>
				<td><input type="text" name="Priority" value="<%=eventList.get(eventid).getPriority()%>"></td>
				<td><input type="text" name="Location" value="<%=eventList.get(eventid).getLocation()%>"></td>
				<td><input type="text" name="Cost" value="<%=eventList.get(eventid).getCost()%>"></td>
				<td><input type="text" name="Start" value="<%=eventList.get(eventid).getStartDate()%>"></td>
				<td><input type="text" name="STime" value="<%=eventList.get(eventid).getStartTime()%>"></td>
				<td><input type="text" name="Stop" value="<%=eventList.get(eventid).getStopDate()%>"></td>
				<td><input type="text" name="Grade" value="<%=eventList.get(eventid).getGradeFormat()%>"></td></tr>
				<tr><th colspan="8">DESCRIPTION:</th></tr>
				<tr><td colspan="8"><input name="descr" size="200%" style="border:none;text-align:left"  value="<%=eventList.get(eventid).getDescription()%>"></textarea></td></tr>
				<tr><th colspan="8">JUSTIFICATION:</th></tr>
				<tr><td colspan="8"><input name="jusify" size="200%" style="border:none;text-align:left" value="<%=eventList.get(eventid).getJustify()%>"></textarea></td></tr>
			</table>
			<table width=100%>
				<%for (int i = 0; i < detailsList.size();i++){ %>
					<tr><th colspan="8">ROUTING DETAILS:</th></tr>
					<tr><th>TRACKINGID:</th><th>TIMESTAMP:</th><th>STATUS:</th><th>LOCATION:</th>
					<th>PERSON:</th><th>COMMENTS</th></tr>
					<tr><td><%=detailsList.get(i).getTrackingId()%></td>
						<td><%=detailsList.get(i).getEventDate()%></td>
						<td><%=detailsList.get(i).getStatus()%></td>
						<td><%=detailsList.get(i).getRoleId()%></td>
						<td><%=detailUser.get(i).getFirstName()%></td>
						<td><%=detailsList.get(i).getComments()%></td></tr>
				<%}%>
			<tr><th name="comments" colspan="8">APPROVAL COMMENTS:</th></tr>
			<tr><td colspan="8"><input name="comments" size="200%" style="border:none;text-align:left"></td></tr>			
		</table><br>						
			<input class="button" type="submit" value="Approve" style="width: 100px">
			<input class="button" type="submit" value="Deny" style="width: 100px">
			<input class="button" type="submit" value="Return to Employee" style="width: 200px">
		</form>
	</div>
</body>
</html>