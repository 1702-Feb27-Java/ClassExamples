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
	table {width:100%;}
	th {text-align: center; font-weight:bold; font-size: 20px; padding: 10px;}
	td {text-align: left;padding: 10px;}

</style>
<title>Home</title>
</head>
<body>
	<%
	int userid = (int)session.getAttribute("userid");
	User u = UserService.getUserInfo(userid); 
	%>
	<h1>Welcome <%=" " + u.getFirstName() + " " + u.getLastName()%></h1>
	<hr><h2>Amount Available: $	 
	<%=u.getAmount()%><br></h2><hr>

	<form action="EventForm.jsp">
	<input type="submit" value="Add Event">
	</form>
	<h2>REQUESTS:</h2><hr>
			
	<% 
		ArrayList<Event> eventList = DAOImpl.getEventStats(userid);
		ArrayList<Tracking> tList = DAOImpl.getTrackingStats(userid);
		session.setAttribute("eventList", eventList);
		session.setAttribute("trackingList", tList);
	%>
	<form action="Details.jsp" method="POST">
		<table>
			<tr><th>EVENT-ID:</th><th style="text-align:left">EVENT:</th><th>START DATE:</th><th>START TIME:</th><th>STOP DATE:</th>
				<th>LOCATION:</th><th>COST:</th><th>STATUS:</th></tr>
				<% for (int i = 0; i < eventList.size();i++){%>
				<tr>
					<td style="text-align: center"><%=eventList.get(i).getEventId()%></td>
					<td><%=eventList.get(i).getEventType()%></td>
					<td style="text-align: center"><%=eventList.get(i).getStartDate()%></td>
					<td style="text-align: center"><%=eventList.get(i).getStartTime()%></td>
					<td style="text-align: center"><%=eventList.get(i).getStopDate()%></td>
					<td><%=eventList.get(i).getLocation()%></td>
					<td>$<%=eventList.get(i).getCost()%></td>
					<td><%=tList.get(i).getStatus()%></td>
				</tr>
			<%}%>
		</table>
		To view / change / delete the details of any request listed,<br>select from the drop down menu:
		<%ArrayList<Event> getEventIds = DAOImpl.getEventNumbers(userid);%> 
		<select name="eventId" required>
		<option disabled selected value> -- select an option -- </option>
		<%for(Event item:getEventIds){	%>
			<option value="<%=item.getEventId()%>">
			  <%=item.getEventId()%></option>
		<%}%>
		
		<br><td><input type="submit" value="Details"></td>
	</form>
	<br><hr>
</body>
</html>