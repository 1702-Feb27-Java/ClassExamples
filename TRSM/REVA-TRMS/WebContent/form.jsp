<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
			  src="https://code.jquery.com/jquery-3.1.1.js"
			  integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
			  crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title> Reimbursement request form</title>
<style>
#second{
	color: red;
}
table, tr, th{
	border: 1px solid black;
}
.jumbotron{
	background-color: #000000;
	color: red;
}
</style>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- SEND TO INEXT -->    
    <div class="navbar-header">      
      <a class="navbar-brand" href="index.jsp">TRMS</a>
    </div>

    <!-- SEND TO REQUEST FORM OR INBOX -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="inbox.jsp">Inbox<span class="sr-only">(current)</span></a></li>
        <li><a href="form.jsp">Request Form</a></li><li><a href="status.jsp">Request Status</a></li><li><a href="Menu.jsp">Menu</a></li>       
      </ul>
     
    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<form class="form-horizontal" action="FormServ.do" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Reimbursement Request Form</legend>
<div align="center">
	<% if (session.getAttribute("formError") != null) {%>
		<%= session.getAttribute("formError") %>
		
		<%session.setAttribute("formError", null);
		} %>
</div>
<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="courseType">Course Type</label>
  <div class="col-md-4">
    <select id="courseType" name="courseType" class="form-control">
      <option value="1">University Course</option>
      <option value="2">Seminar</option>
      <option value="3">Certification Preparation Class</option>
      <option value="4">Certification</option>
      <option value="5">Technical Training</option>
      <option value="6">Other</option>
    </select>
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="grade">Grading Format</label>
  <div class="col-md-4">
    <select id="grade" name="grade" class="form-control">
      <option value="1">A-F</option>
      <option value="2">Pass/Fail</option>
      <option value="3">0-100</option>
       <option value="4">Presentation</option>      
    </select>
  </div>
</div>

<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventDescription">Description of Event</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="eventDescription" name="eventDescription"></textarea>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventDate">Event date</label>  
  <div class="col-md-4">
  <input id="eventDate" name="eventDate" placeholder="mm/dd/yyyy" class="form-control input-md" required="" type="text">
  <span class="help-block">*required</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventLength">Length of Event</label>  
  <div class="col-md-4">
  <input id="eventLength" name="eventLength" placeholder="" class="form-control input-md" type="text">
  <span class="help-block">days or hours needed for the event</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventLocation">Location</label>  
  <div class="col-md-4">
  <input id="eventLocation" name="eventLocation" placeholder="" class="form-control input-md" type="text">
  <span class="help-block">location of Event</span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventCost">Cost</label>  
  <div class="col-md-4">
  <input id="eventCost" name="eventCost" placeholder="" class="form-control input-md" required="" type="text">
  <span class="help-block">*required</span>  
  </div>
</div>


<div id="totalCost" align="center">

</div>
<br>


<!-- Textarea -->
<div class="form-group">
  <label class="col-md-4 control-label" for="eventJust">Justification</label>
  <div class="col-md-4">                     
    <textarea class="form-control" id="eventJust" name="eventJust"></textarea>
  </div>
</div>
<br>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Submit</button>
  </div>
</div>


</fieldset>
</form>
	
	
<script>
$("#totalCost").html("projected reimbursement is: ");
$("#eventCost").blur(function(){
	var x = $("#courseType").val();
	if(x == 1){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * .8))
	}
	else if(x == 2){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * .6))
	}
	else if(x == 3){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * .75))
	}
	else if(x == 4){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * 1))
	}
	else if(x == 5){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * .9))
	}
	else if(x == 6){
		$("#totalCost").html("projected reimbursement is: $" + ($("#eventCost").val() * .3))
	}
	
});
</script>	
	
</body>




</html>