$(document).ready(function(){
	$("#pwd2").blur(function(){
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();

		if (pwd1 != pwd2){                      
			window.alert("Passwords do not match, try again")
		}
	})
	
	$("#email2").blur(function(){
		var email1 = $("#email1").val();
		var email2 = $("#email2").val();

		if (email1 != email2){                      
			window.alert("Emails do not match, try again")
		}
	})
});