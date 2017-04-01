<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="DAO.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approval</title>
</head>
<body>
	<table border=100>
		<thead>
			<th>Pending Reimbursements</th>
		</thead>
	    <td>Employee Id</td>
		<td>Course location</td>
		<td>Course duration</td>
		<td>Add date</td>
		<td>Start date</td>
		<td>End date</td>
		<td>course cost</td>
		<td>reimbursement amount</td>
		<td>Reimbursement Id</td>
		<td>Approval Id</td>
		<td>Course Id</td>
		<td>Grade Id</td>
		<td>Final Grade</td>
		
		<%
			DAOimp hnad = new DAOimp();
			ArrayList<appinfo> applications = new ArrayList<>();
			applications = hnad.getAllInfoBenCo();

			for (int i = 0; i < applications.size(); i++) {
		%>
		<tr>
			<td><%=applications.get(i).getEmployee_id()%></td>
			<td><%=applications.get(i).getLocation()%></td>
			<td><%=applications.get(i).getCourseduration()%></td>
			<td><%=applications.get(i).getAddDate()%></td>
			<td><%=applications.get(i).getStartdate()%></td>
			<td><%=applications.get(i).getEnddate()%></td>
			<td><%=applications.get(i).getCoursecost()%></td>
			<td><%=applications.get(i).getReim_id()%></td>
			<td><%=applications.get(i).getReim_id()%></td>
			<td><%=applications.get(i).getApproval_id()%></td>
			<td><%=applications.get(i).getCourse_id()%></td>
			<td><%=applications.get(i).getGrade_id()%></td>
			<td><%=applications.get(i).getGrade()%></td>
			<form>
			<td><button id="approve" type="submit">Approve</button></td>
			<td><button id="denied" type="submit">Denied</button></td>
			<td><button id="requestinfo" type="submit">Request
					information</button></td>
		</tr>


			</form>



		<%
			}
		%>

	</table>


</body>
</html>