//fizzbuzz
$(function(){

	var fizz = "fizz";
	var buzz = "buzz";
	var fizzbuzz = "fizzbuzz";

	$("#clickMe").click(function(){
		var curr = $("#min").val();
		var max = $("#max").val();
		console.log(curr);
		console.log(max);

		while(curr < max){
			if(curr % 15 == 0){
				$("#fizz").append("<p>" + fizzbuzz + "</p>");
			}
			else if(curr % 5 == 0){
				$("#fizz").append("<p>" + buzz + "</p>");
			}
			else if(curr % 3 == 0){
				$("#fizz").append("<p>" + fizz + "</p>");
			}
			else{
				$("#fizz").append("<p>" + curr + "</p>");
			}
			curr++;
		}
	})

})




