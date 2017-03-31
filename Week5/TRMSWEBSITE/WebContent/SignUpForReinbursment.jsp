<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/styleing.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script>
  
  
  
  $( function() {
	  $("#datepicker").datepicker({ dateFormat: "d-M-yy", minDate: +7 });
	  } );
	  </script>
	  <script>
	  $( function() {
		  $("#datepicker2").datepicker({ dateFormat: "d-M-yy", minDate: +7 });
		  } );
		  </script>


	  <script>
	 
	  </script>
	  
	  
	  
	  <script>
	  window.onload = function() {
	      var month = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
	      var today = new Date();
	      var dd = today.getDate();
	      var mm = today.getMonth();
	      var currentMonth = month[mm];
	      var yyyy = today.getFullYear();
	      today =  dd + "-"+ currentMonth + "-" + yyyy;
	      document.getElementById("appdate").value = today;
	    }
	  </script>
		
<title>Sign Into TRMS</title>
</head>
<body>
	<nav class="navbar navbar-light bg-faded">
			<a class="navbar-brand" href="index.html">TRMS</a>
			<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link" href="Controller">MAIN MENU</a></li>
				<li class="nav-item"><a class="nav-link" href="SignOut">SIGN OUT</a></li>
			
			</ul>

		</nav>

<h1>Sign Up for Reinbursment</h1>
<form class="form-horizontal" method = "POST" action = "SignUpFormHandler">
<fieldset>

<!-- Form Name -->
<legend>Reinbursment Signup</legend>

<div class="form-group">
  <label class="col-md-4 control-label" for="AppDate">Application Date</label>  
  <div class="col-md-4">
  <input type="text" name ="AppDate" id="appdate" placeholder="Application Date" required="" readonly>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="StartDate">Start Date Of Course</label>  
  <div class="col-md-4">
  <input type="text" name ="startdate" id="datepicker" placeholder="Click for Start Date" required="" readonly>
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="EndDate">End Date Of Course</label>  
  <div class="col-md-4">
  <input type="text" name ="enddate" id="datepicker2" placeholder="Click for End Date" required="" readonly>
  </div>
</div>

<!-- 
<div class="form-group">
  <label class="col-md-4 control-label" for="EndDate">End Date Of Course</label>  
  <div class="col-md-4">
  <input id="EndDate" name="EndDate" type="text" placeholder="Enter Date" class="form-control input-md" required="">
    
  </div>
</div>
-->

<div class="form-group">
  <label class="col-md-4 control-label" for="totalcoursetime">Total Course Time</label>  
  <div class="col-md-5">
  <input id="totalcoursetime" name="totalcoursetime" type="text" placeholder="Enter Time In Hour Format" class="form-control input-md" required="">
    
  </div>
</div>




<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Location">Location Of Course</label>  
  <div class="col-md-4">
  <input id="Location" name="Location" type="text" placeholder="Enter Location" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Description">Description Of Course</label>  
  <div class="col-md-4">
  <input id="Description" name="Description" type="text" placeholder="Enter Description" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Cost">Cost Of Course</label>  
  <div class="col-md-4">
  <input id="Cost" name="Cost" type="text" placeholder="Enter Cost" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Justify">Justification for Course</label>  
  <div class="col-md-8">
  <input id="Justify" name="Justify" type="text" placeholder="Enter why you are taking this course." class="form-control input-md" required="">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="EventType">Type Of Course</label>
  <div class="col-md-4">
    <select id="EventType" name="EventType" class="form-control">
      <option value="0"></option>   
      <option value="1">University Course</option>
      <option value="2">Seminar</option>
      <option value="3">Certification Prep</option>
      <option value="4">Certification</option>
      <option value="5">Technical</option>
      <option value="6">Other</option>
    </select>
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="gradetype">Grading Format</label>
  <div class="col-md-4">
    <select id="gradetype" name="gradetype" class="form-control">
    
      <option value="0"></option>
      <option value="1">Letter Grade</option>
      <option value="2">Grading Format</option>
      <option value="3">Percentage</option>
      <option value="4">5 Star</option>
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="approveemail">Enter Approval email</label>  
  <div class="col-md-4">
  <input id="approveemail" name="approveemail" type="text" placeholder="Enter Provided Email" class="form-control input-md">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="Submit"></label>
  <div class="col-md-4">
    <button id="Submit" name="Submit" class="btn btn-primary" >Submit Reinbursment</button>
  </div>
</div>

</fieldset>
</form>

</body>
</html>