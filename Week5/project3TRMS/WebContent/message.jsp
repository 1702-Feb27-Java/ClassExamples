<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="DAO.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="User.*"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border=100>
<thead>
	<th>Pending Reimbursements</th>
</thead>
<td>Sender</td>
<td>Reciever</td>
<td>Messages</td>
<td>Message Id</td>


<%
HttpSession helduserinfo = request.getSession();
UserInfo uinf = (UserInfo) helduserinfo.getAttribute("currentuarr");
  
	DAOimp hnad = new DAOimp();
	ArrayList<Messages> viewMessages = new ArrayList<>();
	viewMessages = hnad.viewMessages(uinf.getEmployee_id());
	//ArrayList<Messages> viewMessages = new ArrayList<>();
	for (int i = 0; i < viewMessages.size(); i++) {
		
		
		
%>
<tr>
	<td><%=viewMessages.get(i).getSender()%></td>
	<td><%=viewMessages.get(i).getReciever()%></td>
	<td><%=viewMessages.get(i).getMessages()%></td>
	<td><%=viewMessages.get(i).getMessage_id()%></td>
	<form>
	<td><button id="respond" type="submit">respond to</button></td>
			
			
			
			
		
</tr>


	</form>

	<%
			}
		%>


<table>
	<tr>
		<th></th>
		<th></th>
	</tr>
	<tr>
		<td>
	</tr>
</table>
</body>
</html>