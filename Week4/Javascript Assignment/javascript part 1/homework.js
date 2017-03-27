// problem 1

function fib(n){
	if (n == 1 || n == 2){
		return 1;
	}  else {
		var f1 = 1;
		var f2 = 1;
		var f = 2;
		for(i = 2; i < n; i++){
			//gets current fib sequence
			f = f1 + f2;
			//sets the previous 
			f2 = f1;
			f1 = f;
		}
		
		return f;
	}
}


// problem 2

function bubbleSort(ar){
	var arr = ar;
	if(arr.length < 2){
		return arr
	} else {
		var isNotSorted = true;
		while(isNotSorted){
			// assume it is sorted unles proven otherwise
			isNotSorted = false;
			for(i = 1; i < arr.length; i++){
				//swap any number that is out of order
				if (arr[i-1] > arr[i]){
					var temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
					isNotSorted = true;
				}
			}
		}
		return arr;
	}
}

//problem 3

function reverseStr(str){
	//turns string to an array of characters
	var charArray = str.split("");
	//reverses said array;
	charArray = charArray.reverse();
	//rejoins every character as a string
	return charArray.join("");
	
}

// problem 4
function factorial(someNum){
	if (someNum == 1){
		return 1;
	} else {
		return someNum * factorial(someNum - 1);
	}
}

//problem 5
function substring(str, offset, length){
	if(typeof(str) != typeof("")){
		alert("str must be a string");
	}else if ((offset + length) > str.length){
		alert("bad offset and length for str");
	} else {
		var result = "";
		for(var i = 0; i < length; i++){
			result = result + str[i+offset];
		}
		return result;
	}
}

//problem 6
function isEven(someNum){
	var n = 0;
	//needs different operation if number is negative or positive
	if (someNum > 0) {
		n = Math.floor(someNum/2);
	} else if (someNum < 0){
		n = Math.ceil(someNum/2);
	}
	if(someNum == n*2){
		return true;
	} else {
		return false;
	}
}

//problem 7
function isPalindrome(someStr){
	//skips the middle element, and only needs to check each pair once
	var n = Math.floor(someStr.length / 2);
	for(var i = 0; i < n; i++){
		//compares each side of the string to see if it is a paliddrome
		if(someStr[i] != someStr[someStr.length - i]){
			return false;
		}
	}
	return true;
}

//problem 8
function printShape(shape, height, character){
	var printSquare = function(height, character){
		for(var i = 0; i < height; i++){
			console.log(character.repeat(height));
		}
	};
	
	var printTriangle = function(height, character){
		for(var i = 1; i <= height; i++){
			console.log(character.repeat(i))
		}
	};
	
	var printDiamond = function(height, character){
		for(var i = 1; i < height; i+=2){
			var spaces = " ".repeat(Math.floor((height - i)/2));
			var chars = character.repeat(i);
			var s = spaces + chars;
			console.log(s)
        }
		console.log(character.repeat(height));
		
		for(var i = height - 2; i >= 1; i-=2){
			var spaces = " ".repeat(Math.floor((height - i)/2));
			var chars = character.repeat(i);
			var s = spaces + chars;
			console.log(s)
        }
	};
	
	switch(shape){
		case "square":
			printSquare(height, character);
			break;
		case "triangle":
			printTriangle(height, character);
			break;
		case "diamond":
			printDiamond(height, character);
			break;
	}
}

//problem 9
function traverseObject(o){
	for(var name in o){
		var value = o[name]
		console.log(name + ": "+ value);
	}
}


//problem 10
function deleteElement(arr){
	console.log(arr.length);
	delete(arr[2]);
	console.log(arr.length);
}

//problem 11
function spliceElement(arr){
	console.log(arr.length);
	arr.splice(2);
	console.log(arr.length);
}

//problem 12
function Person(name, age){
	this.name = name;
	this.age = age;
}

//problem 13
function getPerson(n, a){
	return{
		name: n,
		age: a
	};
}