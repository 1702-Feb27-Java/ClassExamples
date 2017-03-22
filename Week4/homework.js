document.onload = document.write("Success");

//1. Fibonacci
//Define function: fib(n) 
//Return the nth number in the fibonacci sequence.

function fib(n){
	var first = 0;
	var second = 1;
	var sum = 0;
	for (i = 0; i < n-2; i++){
		sum = first + second;
		first = second;
		second = sum;
	}
	return sum;
}

//2. Bubble Sort
//Define function: bubbleSort(numArray)
//Use the bubble sort algorithm to sort the array.
//Return the sorted array.

function bubbleSort(numArray){

	for (j = 0; j < numArray.length-1; j++){
		var swapped = false;
		for (i = 0; i < numArray.length-1; i++){
			if (numArray[i] > numArray[i+1]){
				var temp = numArray[i];
				numArray[i] = numArray[i+1];
				numArray[i+1] = temp;
				swapped = true;
			}
		}
		if (swapped == false){
			break;
		}	
	}
	return numArray;
}

//3. Reverse String
//Define function: reverseStr(someStr)
//Reverse and return the String.

function reverseStr(someStr){
	var splitStr = someStr.split("");
	var reverse = splitStr.reverse();
	var joinStr = reverse.join("");

	return joinStr;
}

//4. Factorial
//Define function: factorial(someNum)
//Use recursion to compute and return the factorial of someNum.

function factorial(someNum){
	if (n==0){
		return 1;
	}
	else{
		var prod = someNum * factorial(someNum - 1);
		return prod;
	}
}

//5. Substring
//Define function substring(someStr, length, offset)
//Return the substring contained between offset and (offset + length) inclusively.
//If incorrect input is entered, use the alert function and describe why the input was incorrect.

function substring(someStr, length, offset){
	if (offset > someStr.length){
		window.alert("you've entered a number larger than the length of the string");

	}
	else {
	var subStr = someStr.substring(length, offset);
	return subStr;
	}
}

//6. Even Number
//Define function: isEven(someNum)
//Return true if even, false if odd.

function isEven(someNum){
	if ((someNum & 1) == 0) {   // performs bitwise AND operator
			return true;		// true -> num is even
		}
		else {
			return false;		// false -> num is odd
		}
}

//7. Palindrome
//Define function isPalindrome(someStr)
//Return true if someStr is a palindrome, otherwise return false

function isPalindrome(someStr){

	function reverseStr(toRev){
	var splitStr = toRev.split("");
	var reverse = splitStr.reverse();
	var joinStr = reverse.join("");
	return joinStr;
	}

	var reversed = reverseStr(someStr);

	if (someStr === reversed){
		return true;
	}
	else {
		return false;
	}
}

/*
8. Shapes
Define function: printShape(shape, height, character)
shape is a String and is either "Square", "Triangle", "Diamond".
height is a Number and is the height of the shape. Assume the number is odd.
character is a String that represents the contents of the shape. Assume this String contains just one character.
Use a switch statement to determine which shape was passed in.
Use the console.log function to print the desired shape.
Example for printShape("Square", 3, "%");
%%%
%%%
%%%
Example for printShape("Triangle", 3, "$");
$
$$
$$$
Example for printShape("Diamond", 5, "*");
  *
 ***
*****
 ***
  *
 */

 function printShape(shape, height, character){
 	switch(shape){
 		case "Square":
 			var line = "";
 			for (i = 0; i < (height); i++){
 				line += character;
	 		}
 			for (j = 0; j < height; j++){
 				console.log(line);
	 		}
 		break;
 		case "Triangle":
 			var line = "";
 			for (i = 0; i < (height); i++){
 				line += character;
 				console.log(line);
	 		}
 		break;
 		case 'Diamond':  // still need to work on this part
 			var line = "";
 			var half = Math.ceil((height)/2);

 			while (line.length < height){
 				line = " " + character + " ";
 				console.log(line.length);
 			}
 			console.log(line);
	 		//for ()
 		break;
 	}
 }

//9. Object literal
//Define function traverseObject(someObj)
//Print every property and it's value.

function traverseObject(someObj){
	for (var n in someObj){
		if (someObj.hasOwnProperty(n)){
			console.log(someObj[n]);
		}
	}
}

/* 
10. Delete Element
Define function deleteElement(someArr)
Print length
Delete the third element in the array.
Print length
The lengths should be the same.
*/ 

function deleteElement(someArr){
	delete someArr[2];
	return someArr;
}

array = [1,3,6,1,23,3];
console.log(array.length);

deleteElement(array);
console.log(array.length);

/*
11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.
*/

function spliceElement(someArr){
	someArr.splice(2, 1);
	return someArr;
}

array = [1,3,6,1,23,3];
console.log(array.length);

spliceElement(array);
console.log(array.length);

/*
12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
    var john = new Person("John", 30);
*/

function Person(name, age){
	this.name = name;
	this.age = age;
}

var john = new Person("John", 30);
console.log(john)

/*
13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
    var john = getPerson("John", 30);
*/

function getPerson(name, age){
	var person = {
		name: name, age: age
	};
	return person;
}

var john = getPerson("John", 30);
console.log(john)