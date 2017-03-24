$(document).ready(function(){
	$(function(){
			
		$("#fizzbuzz").click(function(){
			
			var min = $("#min").val();
			var max = $("#max").val();
					
			for (min; min <= max; min++){
				if (min%3 == 0 && min%5==0 && min%15==0)
					$("#results").append("FizzBuzz <br>");
				else if (min%5 == 0)
					$("#results").append("Buzz <br>");
				else if (min%3 == 0)
					$("#results").append("Fizz <br>");
				else
					$("#results").append(min + "<br>");
			}
		})
	})
});	
		