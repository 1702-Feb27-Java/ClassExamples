//fizzbuzz

var fizzbuzz = function(){
	var curr = document.getElementById("min").value;
	var max = document.getElementById("max").value;
	console.log(curr +  " " + max);
	while(curr < max){
		if(curr % 15 == 0){
			var p = document.createElement("p");
			var t = document.createTextNode("fizzbuzz");
			p.appendChild(t);
			console.log(p.getChild);
			document.getElementById("fizz").appendChild(p);
		}
		else if(curr % 5 == 0){
			var p = document.createElement("p");
			var t = document.createTextNode("buzz");
			p.appendChild(t);
			console.log(p.getChild);
			document.getElementById("fizz").appendChild(p);
		}
		else if(curr % 3 == 0){
			var p = document.createElement("p");
			var t = document.createTextNode("fizz");
			p.appendChild(t);
			console.log(p.getChild);
			document.getElementById("fizz").appendChild(p);
		}
		else{
			var p = document.createElement("p");
			var t = document.createTextNode(curr);
			p.appendChild(t);
			console.log(p.getChild);
			document.getElementById("fizz").appendChild(p);
		}
		curr++;
	}
}