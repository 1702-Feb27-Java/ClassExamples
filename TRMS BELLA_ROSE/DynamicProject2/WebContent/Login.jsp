<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TRMS Portal</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JQuery, required for parts of Bootstrap that use it -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lobster Two">
<style type="text/css">

body
	{
		text-align: center;
		font-family: 'Lobster Two';
		background-image: url("http://vectorandpsd.com/vectorandpsd/background/blue-poly-background.jpg");
		background-repeat: no-repeat;
	}
	
	.button {
  padding: 15px 25px;
  font-size: 24px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

form { text-align: center;}
	
</style>

</head>
<body>
	<h1>Welcome to TRMS!</h1>
	<hr>
	<form action = "LoginServlet.do" method="POST">
	<h1>
		Username
		<br>
		<input type="text" name="uname"><br>
		<br>
		Password
		<br>
		<input type="password" name="pwd"><br>
	</h1>
		<input type="hidden" name="ConstantValue" value="example">
		<!-- <input type="submit" value="Visit">  -->
		<button class="button">Login</button>
	</form>
		
		<c:if test="${validLogin == false }">
			<div id= "LoginAlert" class="alert alert-danger" role="alert"><h3><strong>Oh snap!</strong> Wrong Login Information! Please Try Again!</h3></div>
		</c:if>
	<!--Radio Buttons<br>
		<input type="radio" name="myRadio" value="button1">Button1
		<input type="radio" name="myRadio" value="button2">Button2
		<input type="radio" name="myRadio" value="button3">Button3
		 <input type="submit" value="Login"> -->
		<br>
</body>
</html>