function fizzbuzz(min, max){

	
	var arr = [];
	for (var n = min; n <= max; n++){
		var s = "";
		if (n % 5 != 0 && n % 3 != 0){
			s += n;
		}
		
		if (n % 3 == 0){
			s += "fizz";
		}
		if (n % 5 == 0){
			s += "buzz";
		}
		
		arr.push(s);
	}
	
	for (var i in arr){
		console.log(arr[i]);
		var p = document.createElement("p");
		var t = document.createTextNode(arr[i]);
		p.appendChild(t);
		//console.log(p);
		console.log(document.getElementById("result"))
		document.getElementById("result").appendChild(p);
		
	}
}

function onButtonClick(){
	var minValue = document.getElementById("minValue").value;
	
	var maxValue = document.getElementById("maxValue").value;
	
	//console.log("Min Value: " + minValue);
	
	//console.log("Max Value: " + maxValue);
	if (minValue < 1){
		alert("Min Value be greater then 0");
	} else if (maxValue > 200){
		alert("Max Value must be less then 200")
	} else {
		fizzbuzz(minValue, maxValue);
	}
}