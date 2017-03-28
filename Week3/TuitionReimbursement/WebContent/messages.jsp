<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style>
		table {
	    	width: 100%;
	    	border: 1px solid black;
		}
		
		th {
		    height: 50px;
		    border: 1px solid black;
		}
		td {
			border: 1px solid black;	
		}
		#subButton {
			border-radius: 25px;
    		border: 2px solid #73AD21;
    		width: 100%;
		}
	</style>
</head>
<body>

	<%@include file="header.jsp" %>
		
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<table id="tableId">
		    <tr>
		        <th>From</th>
		        <th>Message</th>
		        <th>Edit Reimbursement</th>
		    </tr>
		    <c:forEach items="${messageList}" var="message">	
		   <form action="" method="POST">
			    <tr id="${message.getReimbId()}">
			    	<input type="hidden" name="reimbId" value="${message.getReimbId()}" style="visibility:hidden;">
			        <td>
			            <c:out value="${message.getMessager()}" />
			        </td>
			        <td>
			            <c:out value="${message.getMessage()}" />
			        </td>
			        <td>
			        	<button id="subButton" type="submit">${message.getReimbId()}</button>
			       	</td>
			       
			    </tr>
		    </form>
		    </c:forEach>
		</table>
		</div>
<%-- 	==================================================================================	

		<h1>Messages</h1>
	
		    <ul>
			      <c:forEach items="${messageList}" var="message">
			      	<option value=${message}>
			      		<li>From: ${message.messager} --- ${message.message}</li>
			      </c:forEach>
		     </ul>
		====================================================================== --%>
</div>
	
</body>
</html>