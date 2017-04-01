<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.Integer" %>
<%@ page import="com.revature.dao.DAOImpl"%>
<%@ page import="com.revature.trms.UserService"%>
<%@ page import="com.revature.trms.User"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User</title>
<style>
	body {background-repeat: no-repeat;background-size:cover; 
		background-position:top center; background-attachment:fixed;}
	label{display:inline-block; width:150px; text-align: right; font-size:22px;padding-right:5px}
	input, select{display:table-cell; width:200px}
</style>
</head>
<body background="new_user_background.jpg">

	<% int userid = (int)session.getAttribute("userid");
	   User u = UserService.getUserInfo(userid);
	%>
	<%! static int count = 0;%>
	<% 	if (count == 0){ %>
		<h1 align="center">Edit TRMS User Account</h1>
		<%count++;%>
	<% } else { %>
		<h1 align="center">Edit TRMS User Account</h1>
		<h3 align="center" style="color:red"># Username is taken, please try again.</h3>
	<% } %>
	<div align="center"><form action="EditUserServlet" method="POST">
		<p><label>First Name:</label><input type="text" name="fname" value="<%=u.getFirstName()%>"></p>
		<p><label>Last Name:</label><input type="text" name="lname" value="<%=u.getLastName()%>"></p>
		<p><label>Username:</label><input type="text" name="uname" value="<%=u.getUsername()%>"></p>
		<p><label>Password:</label><input type="password" maxlength=16 name="password" id="pwd1" value="<%=u.getPassword()%>"></p>
		<p><label>Email:</label><input type="text" name="email" id="email1" value="<%=u.getEmail()%>"></p>
		<p><label>Role:</label>
		<select name="role" value="<%=u.getRole()%>">
			<option value="4">Employee</option>
  			<option value="3">Direct Supervisor</option>
  			<option value="2">Department Head</option>
  			<option value="1">Benefits Coordinator</option>
		</select></p>
		<p><label>Department:</label>
		<select name="dept" value="<%=u.getDept()%>">
			<option value="1">Development</option>
			<option value="2">Human Resources</option>
  			<option value="3">Management</option>
  			<option value="4">Projects</option>
			<option value="5">Sales</option>  			
			<option value="6">Security</option>
			<option value="7">Testing</option>
		</select></p>
		<p><label>Supervisor:</label>
		<select name="supname" value="<%=u.getFirstName()%>">
		
		<%
		Hashtable ht = DAOImpl.getSupervisorId();
		Enumeration keys;				
		Integer key;
						
		keys = ht.keys();
		while(keys.hasMoreElements()){	%>
			<option value="<%=key = (Integer)keys.nextElement()%>">
			  <%=ht.get(key)%></option>
		<%}%>	
		</select>
		<br><br>		
		<input type="submit" value="Submit Changes">
		</div>
	</form>
</body>
</html>