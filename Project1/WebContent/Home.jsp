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
	.left {text-align: left; font-size: 15px; padding: 10px;}
	.center {text-align: center; font-size: 15px; padding: 10px;}
	th {font-weight: bold}

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
		To view / change / delete the details of any request listed,<br>select from the drop down menu:
		<%ArrayList<Event> getEventIds = DAOImpl.getEventNumbers(userid);%> 
		<select name="eventId" required>
		<option disabled selected value> -- select an option -- </option>
		<%for(Event item:getEventIds){	%>
			<option value="<%=item.getEventId()%>">
			  <%=item.getEventId()%></option>
		<%}%>
		</select>
		<br><input type="submit" value="Details">
	</form>
	<br><hr>
</body>
</html>