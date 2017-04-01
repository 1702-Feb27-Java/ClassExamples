<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Reimbursements</title>
</head>
<body>

	<h1>YOUR PENDING REQUESTS</h1>
	<%
		if ((int) session.getAttribute("listsize") > 0) {
	%>
	<table border=2>
		<tr>
			<th>Request ID</th>
			<th>Submit date</th>
			<th>Course Type</th>
			<th>Request Amount</th>
			<th>Status</th>
			<th>Award Amount</th>
		</tr>

		<%
			for (int i = 0; i < (int) session.getAttribute("listsize"); i++) {
		%>
		<tr>
			<td><%=session.getAttribute("reqid" + i)%></td>
			<td><%=session.getAttribute("submitdate" + i)%></td>
			<td><%=session.getAttribute("coursetype" + i)%></td>
			<td><%=session.getAttribute("reqamount" + i)%></td>
			<td><%=session.getAttribute("status" + i)%></td>
			<td><%=session.getAttribute("award" + i)%></td>
			<%
			if((int)session.getAttribute("addinfoemp"+i) == 2){
			%>
				<td>
				<form action="GenerateAttachFiles" method="POST">
					<input id="addinfo" type="hidden" name="addinfo"
						value="<%=session.getAttribute("reqid" + i)%>">
					<button id="addmoreinfo" type="submit" name="addmoreinfo">Addition info required</button>
				</form>				
				</td>		
			<%	
			}
			%>
			
		</tr>
		<%
			}
		%>

	</table>
	<%
		} else {
	%>
	<h2>NONE</h2>
	<%
		}
	%>

	<%
		if ((boolean) session.getAttribute("isDepHead")) {
	%>
	<h1>PENDING REQUESTS FOR DEPARTMENT HEAD ${username} TO APPROVE</h1>
	<%
		if ((int) session.getAttribute("listsizeDepHead") > 0) {
	%>
	<table border=2>
		<tr>
			<th>Request ID</th>
			<th>Submit date</th>
			<th>Course Type</th>
			<th>Request Amount</th>
			<th>Status</th>
			<th>Award Amount</th>
			<th>Urgency</th>
		</tr>
		<%
			for (int i = 0; i < (int) session.getAttribute("listsizeDepHead"); i++) {
				if((int)session.getAttribute("addinfo"+i) == 2){
					continue;
				}
		%>
		<tr>
			<td><%=session.getAttribute("reqiddephead" + i)%></td>
			<td><%=session.getAttribute("submitdatedephead" + i)%></td>
			<td><%=session.getAttribute("coursetypedephead" + i)%></td>
			<td><%=session.getAttribute("reqamountdephead" + i)%></td>
			<td><%=session.getAttribute("statusdephead" + i)%></td>
			<td><%=session.getAttribute("awarddephead" + i)%></td>
			<td><%=session.getAttribute("urgency" + i)%></td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="approveinput" type="hidden" name="approveinput"
						value="<%=session.getAttribute("reqiddephead" + i)%>">
					<button id="approve" type="submit" name="approve">APPROVE</button>
				</form>
			</td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="rejectinput" type="hidden" name="rejectinput"
						value="<%=session.getAttribute("reqiddephead" + i)%>">
					<button id="reject" type="submit" name="reject">REJECT</button>
				</form>
			</td>
			<td>
				<form action="addInfoMessage.jsp" method="POST">
					<input id="additonalinfo" type="hidden" name="additonalinfo"
						value="<%=session.getAttribute("reqiddephead" + i)%>">
					<button id="getmoreinfo" type="submit" name="getmoreinfo">REQUEST
						MORE INFO</button>
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	<h2>NONE</h2>
	<%
		}
	%>

	<%
		} else if ((boolean) session.getAttribute("isSuper")) {
	%>
	<h1>PENDING REQUESTS FOR SUPERVISOR ${username} TO APPROVE</h1>
	<%
		if ((int) session.getAttribute("listsizeSuper") > 0) {
	%>
	<table border=2>
		<tr>
			<th>Request ID</th>
			<th>Submit date</th>
			<th>Course Type</th>
			<th>Request Amount</th>
			<th>Status</th>
			<th>Award Amount</th>
			<th>Urgency</th>
		</tr>
		<%
			for (int i = 0; i < (int) session.getAttribute("listsizeSuper"); i++) {
				if((int)session.getAttribute("addinfo" + i) == 2){
					continue;
				}
		%>
		<tr>
			<td><%=session.getAttribute("reqidsuper" + i)%></td>
			<td><%=session.getAttribute("submitdatesuper" + i)%></td>
			<td><%=session.getAttribute("coursetypesuper" + i)%></td>
			<td><%=session.getAttribute("reqamountsuper" + i)%></td>
			<td><%=session.getAttribute("statussuper" + i)%></td>
			<td><%=session.getAttribute("awardsuper" + i)%></td>
			<td><%=session.getAttribute("urgency" + i)%></td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="approveinput" type="hidden" name="approveinput"
						value="<%=session.getAttribute("reqidsuper" + i)%>">
					<button id="approve" type="submit" name="approve">APPROVE</button>
				</form>
			</td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="rejectinput" type="hidden" name="rejectinput"
						value="<%=session.getAttribute("reqidsuper" + i)%>">
					<button id="reject" type="submit" name="reject">REJECT</button>
				</form>
			</td>
			<td>
				<form action="addInfoMessage.jsp" method="POST">
					<input id="additonalinfo" type="hidden" name="additonalinfo"
						value="<%=session.getAttribute("reqidsuper" + i)%>">
					<button id="getmoreinfo" type="submit" name="getmoreinfo">REQUEST
						MORE INFO</button>
				</form>
			</td>
		</tr>
		<%
			}
		%>

	</table>

	<%
		} else {
	%>
	<h2>NONE</h2>

	<%
		}
	%>
	<%
		}
	%>
	
	<%
		if ((boolean) session.getAttribute("isBenCo")) {
	%>
	<h1>PENDING REQUESTS FOR BENIFITS COORDINATOR ${username} TO APPROVE</h1>
	<%
		if ((int) session.getAttribute("listsizeBenCo") > 0) {
	%>
	<table border=2>
		<tr>
			<th>Request ID</th>
			<th>Submit date</th>
			<th>Course Type</th>
			<th>Request Amount</th>
			<th>Status</th>
			<th>Award Amount</th>
			<th>Urgency</th>
		</tr>
		<%
			for (int i = 0; i < (int) session.getAttribute("listsizeBenCo"); i++) {
		%>
		<tr>
			<td><%=session.getAttribute("reqidbenco" + i)%></td>
			<td><%=session.getAttribute("submitdatebenco" + i)%></td>
			<td><%=session.getAttribute("coursetypebenco" + i)%></td>
			<td><%=session.getAttribute("reqamountbenco" + i)%></td>
			<td><%=session.getAttribute("statusbenco" + i)%></td>
			<td><%=session.getAttribute("awardbenco" + i)%></td>
			<td><%=session.getAttribute("urgency" + i)%></td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="approveinput" type="hidden" name="approveinput"
						value="<%=session.getAttribute("reqidbenco" + i)%>">
					<button id="approve" type="submit" name="approve">APPROVE</button>
				</form>
			</td>
			<td>
				<form action="UpdateStatus" method="POST">
					<input id="rejectinput" type="hidden" name="rejectinput"
						value="<%=session.getAttribute("reqidbenco" + i)%>">
					<button id="reject" type="submit" name="reject">REJECT</button>
				</form>
			</td>
			<td>
				<form action="addInfoMessage.jsp" method="POST">
					<input id="additonalinfo" type="hidden" name="additonalinfo"
						value="<%=session.getAttribute("reqidbenco" + i)%>">
					<button id="getmoreinfo" type="submit" name="getmoreinfo">REQUEST
						MORE INFO</button>
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	<h2>NONE</h2>
	<%
		}
	%>
	<%} %>



</body>
</html>