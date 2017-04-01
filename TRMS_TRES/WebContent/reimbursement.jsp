<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.tres.servlets.Login" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tres.objs.Employee" %>
<%@ page import="com.tres.objs.CourseType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<style type="text/css">
		.control-label:after { 
   			content:"*";
   			color:red;
   		}
	</style>
	<%@ include file="head.html" %>
		<script src="resources/js/scripts.js"> </script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Reimbursement</title>
	</head>
	<body>
		<%@ include file="navbar.jsp" %>
		<div class="row">
			<div class="col-md-4 col-md-offset-1">
				<p>Fields marked with * are required </p>
 		 	</div>
 		 </div>
		<form id="form1" action="Reimbur" method="POST" enctype="multipart/form-data" >
            <!---------------- BASIC USER INFO (Username) ----------------------->
<!--==
ID: UNAME 
==-->
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label class="control-label" for="uname">Username</label>
    					<input type="text" required="required" class="form-control" name="uname" id="uname" value="<%= session.getAttribute("uname")%>" disabled>
 		 			</div>
 		 		</div>
            	<!---------------- ATTACHMENT INFO ----------------------->
<!--==
ID: InputFile 
==-->
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label for="InputFile">File input</label>
    					<input type="file" name="InputFile" id="InputFile">
    					<p class="help-block">Example block-level help text here.</p>
  					</div>
  				</div>
 		 	</div>
            <!---------------- BASIC USER INFO (FirstName) ----------------------->
<!--==
ID: FNAME 
==-->
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label class="control-label" for="fname">First Name</label>
    					<input type="text" required="required" class="form-control" name="fname" id="fname" value="<%= session.getAttribute("fname")%>" disabled>
  					</div>
 		 		</div>
            	<!---------------- TIME MISSED ----------------------->
<!--==
ID: TMMISS 
==-->
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label for="tmmiss">Time missed</label>
    					<input type="text" class="form-control" name="tmmiss" id="tmmiss" placeholder="time">
  					</div>
  				</div>
  			</div>
            <!---------------- BASIC USER INFO (Email) ----------------------->
<!--==
ID: EML
==-->
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label class="control-label" for="eml">Email</label>
    					<input type="email" class="form-control" name="eml" id="eml" value="<%= session.getAttribute("email")%>" disabled>
  					</div>
 		 		</div>
  			</div>
            <!---------------- TIME ----------------------->
<!--==
ID: TIMEPICKER1
==-->
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
                	<div class="form-group">
                        <label class="control-label" for="timepicker1"> Time </label>
                		<input required="required" type="time" name="timepicker1" id="timepicker1" disabled>
                		<!-- <button type="button" id="setTimeButton">Set Current Time</button> -->
                	</div>
  				</div>
  			</div>
  			 <!---------------- FORM DATE ------------------------->
<!--==
ID: FRMDT
==-->
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
                	<div class="form-group">
                        <label class="control-label" for="frmdt"> Form Date: </label>
                		<input required="required" type="date" name="frmdt" id="frmdt" disabled> 
                	</div>
  				</div>
  			</div>
            <!---------------- CRS DATE ------------------------->
<!--==
ID: STDATE
ID: EDATE
==-->
			<div class="row">
				<div class="col-md-3 col-md-offset-1">
                	<div class="form-group">
                        <label class="control-label" for="stdate"> Start of Course </label>
                		<input required="required" type="date" name="stdate" id="stdate">
                		<br>
                		<label class="control-label" for="edate"> End of Course </label>
                		<input required="required" type="date" name="edate" id="edate">
                	</div>
  				</div>
  			</div>
            <!---------------- COURSE LOCATION ----------------------->
<!--==
ID: LOC
==-->
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
  					<div class="form-group">
    					<label class="control-label" for="loc">Location</label>
    					<input required="required" type="text" class="form-control" name="loc" id="loc"> 
  					</div>
 		 		</div>
  			</div>
            <!---------------- COURSE DESCR ----------------------->
<!--==
ID: CRSDES
==-->
            <div class="row">
                <div class="col-md-4 col-md-offset-1">
                    <div class="form-group">
                        <label class="control-label" for="comment">Course Description:</label>
                        <textarea required="required" class="form-control" rows="2" name="crsdes" id="crsdes"></textarea>
                    </div>
                </div>
            </div>
            <!---------------- COURSE COST ----------------------->
<!--==
ID: CST
==-->
            <div class="row">
                <div class="col-md-2 col-md-offset-1">
                    <div class="form-group">
    					<label class="sr-only" for="cst">Amount (in dollars)</label>
    						<div class="input-group">
      							<div class="input-group-addon">$</div>
      							<input type="text" required="required" class="form-control" name="cst" id="cst" placeholder="Amount">
    						</div>
  					</div>
                </div>
            </div>
            <!---------------- GRADING FORMAT ----------------------->
<!--==
ID: GRDFORMAT
==-->
            <div class="row">
                <div class="col-md-4 col-md-offset-1">
                    <div class="form-group">
                        <label class="control-label" for="grdformat">Grading Format:</label>
						<div class="btn-group">
							<%! ArrayList<String> grdF = (Login.serve).getGradingTypes(); %>
  							<select name="grdformat" id="grdformat" class="btn btn-default">
    						<% 
    							for(int i =0; i < grdF.size(); i++)
    							{
    						%>
    							<option value="<%=i+1 %>"> <%=grdF.get(i) %> </option>	
    						<% 	
    							}
    						%>
  							</select>
						</div>
                    </div>
                </div>
            </div>
            <!---------------- TYPE OF EVENT ----------------------->
<!--==
ID: COURSES
==-->
            <div class="row">
                <div class="col-md-4 col-md-offset-1">
                    <div class="form-group">
                        <label class="control-label" for="courses">Type of Event:</label>
						<div class="btn-group">
							<%! ArrayList<CourseType> crs = (Login.serve).getCourseTypes(); %>
  							<select name="courses" id="courses" class="btn btn-default">
    						<% 
    							for(int i =0; i < crs.size(); i++)
    							{
    						%>
    							<option value="<%=i+1 %>"> <%=crs.get(i).getName() %> </option>	
    						<% 	
    							}
    						%>
  							</select>
						</div>
                    </div>
                </div>
            </div>            
            <!---------------- WORK JUSTT ----------------------->
<!--==
ID: WRKJUS
==-->
            <div class="row">
                <div class="col-md-4 col-md-offset-1">
                    <div class="form-group">
                        <label class="control-label" for="wrkjus">Work Justification:</label>
                        <textarea required="required" class="form-control" rows="2" name="wrkjus" id="wrkjus"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-md-offset-5">
		  			<button type="submit" class="btn btn-default" id="reimsub">Submit</button>
		  		</div>
		  	</div>
		</form>
	</body>
</html>
