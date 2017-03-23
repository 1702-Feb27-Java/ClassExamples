//JAVASCRIPT HOMEWORK

//1. Fibonacci
var fib = function(n){
	var x = 0;		
	var y = 1;
   for(var i = 0; i < n; i++){
		var z = y + x;
		var y = x;
		var x = z;
   }
    return z;  
}

//2. Bubble Sort
var bubbleSort = function(numArray){
   	var swapped;
    do {
        swapped = false;
        for (var i=0; i < numArray.length-1; i++) {
            if (numArray[i] > numArray[i+1]) {
                var temp = numArray[i];
                numArray[i] = numArray[i+1];
                numArray[i+1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
	return numArray;
}
//3. Reverse String
var reverseStr = function(someStr){
    var splitString = someStr.split("");
    var reverseArray = splitString.reverse();
    var joinArray = reverseArray.join("");
     return joinArray;
}
//4. Factorial
var factorial = function(someNum){
    if(someNum == 0) {
        return 1
    } else {
        return someNum * factorial(someNum - 1);
    }
}
//5. Substring
var substring = function(someStr, length, offset){
	if(typeof(someStr) != "string"){
		window.alert("Not a string input");
    }

	var subStr = someStr.substring(length, offset+1);
	return subStr;
}
//6. Even number
var isEven = function(someNum){
	if(someNum % 2 == 0){
		return true;
    }else{	
		return false;	
    }
}
//7. Palindrome
var isPalindrome = function(someStr){
    var len = someStr.length;
    var back = someStr.substring(len/2, len);
	var newString = "";
    for (var i = back.length - 1; i >= 0; i--) {
        newString += back[i];
    }
	if(len%2 == 0){
		var front = someStr.substring(0, len/2);
		if(front == newString){
			return true;
        }else{
			return false;
        }
    }else{
		var front = someStr.substring(0, (len/2)+1);
		if(front == newString){
			return true;
        }else{
			return false;
        }
    }
}
//8. Shapes
var printShape = function(shape, height, character){
	switch(shape){
        case "Square": 
		for(var h = 0; h < height; h++){
			for(var w = 0; w < height; w++){
       			 console.log(character);
			}
			console.log('\n');
   		 }
		break;
        case "Triangle":
			for(var t = 0; t < height + 1; t++){
				for(var line = 0; line < t; line++){
					console.log(character);
                }
				console.log('\n');
            }
		break;
        case "Diamond":
			for(var d = 1; d < height+1; d++){
				if(d <= height/2+1){
					for(var l = 1; l <= d*2-1; l++){
						console.log(character);
                	}
                }else{
					if(d == (height)){
							console.log(character);
                       }
					else{
                        for(var l2 = 1; l2 < height - (height - d); l2++){
                            console.log(character);
                        }
                    }
                }
				console.log('\n');
            }
			break;
        default: 
			break;
    }

}
//9. Object Literal
var traverseObject = function(object){
 	console.log(object); 
}
//10. Delete Element
var deleteElement = function(someArr){
	console.log(someArr.length);
	delete someArr[2];
    console.log(someArr.length);
}
//11. Splice Element
var spliceElement = function(someArr){
	console.log(someArr.length);
	someArr.splice(2,1);
    console.log(someArr.length);
}
//12. Defining an object using a constructor 
var Person = function(name, age) {
    this.name = name;
    this.age = age;
}
var john = new Person("John", 30)
//13. Defining an object using an object literal
function getPerson(name, age) { 
    return { 
        name: name, 
        age : age
    }; 
} 


