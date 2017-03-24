<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.revature.trms.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
	<%
	ArrayList<Event> eventList = (ArrayList<Event>)session.getAttribute("eventList");
	ArrayList<Tracking> trackList = (ArrayList<Tracking>)session.getAttribute("trackingList");
	Integer eventid = Integer.parseInt(request.getParameter("eventId"));
	%>
	<div align="center"><form><table>
				<tr><th>EVENT #:</th></tr>
				<tr><td><input type="text" name="eventNum" value="<%=eventList.get(eventid).getEventId()%>"></td></tr>
				<tr><th>EVENT:</th><th>PRIORITY:</th><th>LOCATION:</th><th>COST:</th></tr>
					<tr><td><input type="text" name="EventType" value="<%=eventList.get(eventid).getEventType()%>"></td>
						<td><input type="text" name="Priority" value="<%=eventList.get(eventid).getPriority()%>"></td>
						<td><input type="text" name="Location" value="<%=eventList.get(eventid).getLocation()%>"></td>
						<td><input type="text" name="Cost" value="<%=eventList.get(eventid).getCost()%>"></td></tr>
				<tr><th>START DATE:</th><th>TIME:</th><th>STOP DATE:</th><th>GRADING<br>FORMAT:</th></tr>	
				<tr><td><input type="text" name="Start" value="<%=eventList.get(eventid).getStartDate()%>"></td>
						<td><input type="text" name="STime" value="<%=eventList.get(eventid).getStartTime()%>"></td>
						<td><input type="text" name="Stop" value="<%=eventList.get(eventid).getStopDate()%>"></td>
						<td><input type="text" name="Grade" value="<%=eventList.get(eventid).getGradeFormat()%>"></td></tr>
				<tr><th>DESCRIPTION:</th></tr>
				<tr><td colspan="4"><input name="descr" size="100" style="line-height:4em"  value="<%=eventList.get(eventid).getDescription()%>"></textarea></td></tr>
				<tr><th>JUSTIFICATION:</th></tr>
				<tr><td colspan="4"><input name="jusify" size="100" style="line-height:4em" value="<%=eventList.get(eventid).getJustify()%>"></textarea></td></tr>
		</table></form></div>
	</body>
</html>