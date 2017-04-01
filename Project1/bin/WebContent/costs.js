$(document).ready(function(){
	$("#cost").blur(function(){
		var type = $("#etype").val();
		var cost = $("#cost").val();

		if (type == 1){                      
			$("#saved").html("Saved: $" + cost*.8);
			$("#oop").html("Out of Pocket: $" + cost*.2);
		}
		else if (type == 2){
			$("#saved").html("Saved: $" + cost*.6);
			$("#oop").html("Out of Pocket: $" + cost*.4);
		}
		else if  (type == 3){
			$("#saved").html("Saved: $" + cost*.75);
			$("#oop").html("Out of Pocket: $" + cost*.25);
		}
		else if  (type == 4){
			$("#saved").html("Saved: $" + cost*1);
			$("#oop").html("Out of Pocket: $" + cost*0);
		}
		else if  (type == 5){
			$("#saved").html("Saved: $" + cost*.9);
			$("#oop").html("Out of Pocket: $" + cost*.1);
		}
		else {
			$("#saved").html("Saved: $" + cost*.3);
			$("#oop").html("Out of Pocket: $" + cost*.7);
		}
	})
});