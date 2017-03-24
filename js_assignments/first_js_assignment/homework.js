//question 1
function fib(n){
	var fib1 = 0;
	var fib2 = 1;
	//test for first two inputs
	if(n == 0){

		return 0;
	}
	if(n == 1){
		return 1;
    }

	//run the fib sequence
	for (i = 0; i < n - 2; i++){
		var temp = fib2;
		fib2 = fib1 + fib2;
		fib1 = temp;
	}
	return fib2
}

//Question 2
function bubbleSort(numArray){
	//go through once for each element in the array
	for(j = 0; j < numArray.length; j++){
		//check the length of the array to swap
	for(i = 0; i < numArray.length - 1; i++){
		if(numArray[i] > numArray[i+1]){
			var temp = numArray[i]
			numArray[i] = numArray[i+1]
			numArray[i+1] = temp
		}
	}
    }
	return numArray

}

//Question 3
function reverseStr(someStr){
	someStr = someStr.split("").reverse().join("")
	return someStr

}

//Question 4
function factorial(someNum){
		//base case
	if(someNum == 1){
		return 1
	}
	//return number * number- 1
	return someNum * factorial(someNum - 1)
	

}



//Question 5
function substring(someStr, length, offset){
	//checks if input is right
	if(typeof someStr != "string" ){
		window.alert("someStr is not a string")
	}
	if(typeof length != "number" ){
		window.alert("length is not a number")
	}
	if(typeof offset != "number" ){
		window.alert("offset is not a number")
	}
	//returns from the offset a substring of the specified length
	return someStr.substring(offset, length+offset)
}

//question 6
function isEven(someNum){
	//ands on the ones place in binary if its zero its even
	if((someNum & 1) == 0){
		return true
	} 
	return false

}
//question 7
function isPalindrome(someStr){
	//reverse the string
	var temp = someStr.split("").reverse().join("")
	//is it equal to the given string?
	if(temp === someStr){
		return true
	}
	return false
}





function printShape(shape, height, charactor){
	if(shape === "Square"){ //prints a square
		for(i = 0; i < height; i++){
			var temp = ""
			for(j = 0; j<height; j++){
				temp += charactor
			}
			console.log(temp)
		}
	}
	if(shape === "Triangle"){ //prints a triangle increasing in number of charactors for every row
		var count = 1;
		for(i = 0; i < height; i++){
			var temp = ""
			for(j = 0; j<count; j++){
				temp += charactor
			}
			count++
			console.log(temp)
		}

	}
	if(shape === "Diamond"){ //prints a diamond increasing then decreasing in size
		var count = 1;
		var current = 1;
		var mid = height/2
		for(i = 0; i < height; i++){ 
			var temp = ""
			//add padding if needed
			var add = (height - current) / 2;
			while(add >= 1){
					temp += " "
					add--
				}
			//add charactors
			for(j = 0; j<current; j++){				
				temp += charactor				
			}
			//add padding after charactors
			var add = (height - current) / 2;
				while(add >= 1){
					temp += " "
					add--
				}
			//determine if you increase or decrease the number of charactors 
			if(count < mid){
				current += 2
			}
			else{
				current -= 2
			}
			count++
			
			console.log(temp)
		}
		
	}

}

//Question 9
function traverseObject(someObj){
	var value;
	for(var x in someObj){ //look at every element
		value = someObj[x]
		console.log(x + ": " + value)
	}
}

//Question 10
//deletes the 3rd element
function deleteElement(someArr){
	console.log(someArr.length)
	delete someArr[2]
	console.log(someArr.length)
}
//Question 11
//splices from the 3rd element
function spliceElement(someArr){
	console.log(someArr.length)
	someArr.splice(2)
	console.log(someArr.length)
}
//Question 12
function Person(name, age){
	this.name = name
	this.age = age
}



//Question 13
function getPerson(name, age){
	return{
	name: name,
	age:  age
    }
}