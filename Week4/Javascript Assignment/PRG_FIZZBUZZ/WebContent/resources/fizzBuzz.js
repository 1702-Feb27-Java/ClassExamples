function checkFizzBuzz(min,max){
	var doc = document.getElementById('print').contentWindow.document;
	doc.open();
	if(min<1 || max>200){
		alert("Please enter (MIN,MAX) values in range [1-200]");
		
		document.getElementById('min').value="";
		document.getElementById('max').value="";
		doc.writeln("");
		doc.close();
		
		return false;
	}
	
	for(i=min;i<=max;i++){
		if(i%3==0 && i%5==0){
			doc.writeln("<span style='color:red'>Fizz</span><span style='color:blue'>Buzz</span><br/>");
			console.log("FizzBuzz");
		}
		else if(i%3==0){
			doc.writeln("<span style='color:red'>Fizz</span><br/>");
			//console.log("Fizz");
		}else if(i%5==0){
			doc.writeln("<span style='color:blue'>Buzz</span><br/>");
			//console.log("Buzz");
		}else{
			doc.writeln(i+"<br/>");
			//console.log(i);
		}
	}
	doc.close();
}