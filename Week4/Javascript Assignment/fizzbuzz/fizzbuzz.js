$(function(){

	$('#fizzbuzz').click(function(e){
		var min = $('#min').val();
		var max = $('#max').val();

		for(var i = min; i <= max; i++ ){
			if(i%3 == 0 && i%7 != 0){
				$('#output').append("fizz! ");
			}else if(i%7 == 0 && i%3 != 0){
				$('#output').append("buzz! ");
			}else if(i%7 == 0 && i%3 == 0){
				$('#output').append("fizzbuzz! ");
			}else{
				$('#output').append(i + " ");
			}
		}

		console.log("done");


	})




})