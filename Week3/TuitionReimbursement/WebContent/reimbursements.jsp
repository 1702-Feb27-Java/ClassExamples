<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		button {
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
		    <!-- here should go some titles... -->
		    <tr>
		        <th>Event</th>
		        <th>Time</th>
		        <th>Description</th>
		        <th>Cost</th>
		        <th>Location</th>
		        <th>Grading Scale</th>
		        <th>Type of Event</th>
		        <th>Approval Step</th>
		        <th>Event Date</th>
		        <th>Date Filled out</th>
		        <th>View Reimbursement</th>
		    </tr>
		    <c:forEach items="${reimbursements}" var="reimbursement">
		   <!--  <form action="" -->
		   <form action="EditReimbursementServlet.do" method="POST">
			    <tr id="${reimbursement.getReimbId()}">
			    	<input type="hidden" name="reimbId" value="${reimbursement.getReimbId()}" style="visibility:hidden;">
			        <td>
			            <c:out value="${reimbursement.getEvent()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getTime()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getDescription()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getCost()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getLocation()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getGrading()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getTypeOfEvent()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getApprovalStep()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getEventDate()}" />
			        </td>
			        <td>
			            <c:out value="${reimbursement.getFormDate()}" />
			        </td>
			        <td>
			        	<button type="submit">${reimbursement.getReimbId()}</button>
			       	</td>
			    </tr>
		    </form>
		    </c:forEach>
		</table>
		<p id="pid"></p>
		</div>
		<div class="col-md-2"></div>
</div>

<!-- 	<script>
	function addRowHandlers() {
	    var table = document.getElementById("tableId");
	    var rows = table.getElementsByTagName("tr");
	    for (i = 0; i < rows.length; i++) {
	        var currentRow = table.rows[i]	;
	        var createClickHandler = 
	            function(row) 
	            {
	                return function() { 
	                				
	                                        var cell = row.getElementsByTagName("td")[0];
	                                        var id = cell.innerHTML;
	                                        console.log(id);
	                                        document.getElementById(id).children[0].setAttribute("blah", "rid");
	                                        //$("#pid").append("<form action=LoginServlet.do action=post><p name='id' value=" + id + "> </p></form>");
	                                 };
	            };

	        currentRow.onclick = createClickHandler(currentRow);
	    }
	}
	window.onload = addRowHandlers();
	</script> -->
	
</body>
</html>



