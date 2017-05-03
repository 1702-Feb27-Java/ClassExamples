/*-----------------------------------------------------------------------------------
PART I

Create a single Javascript file called homework.js to answer these questions.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------
*/
/*1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.*/

function fib(n){
	if(n == 1)
		return 1;
	if(n == 0)
		return 1;
	return fib(n-1) + fib(n-2);
}

/*2. Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array.
Return the sorted array.*/

function bubbleSort(numArray) {
	var end = numArray.length-1;
	var temp;

	while(end > 0){
		for(var i = 0; i < end; i++){
			if(numArray[i] >= numArray[i+1]){
				temp = numArray[i];
				numArray[i] = numArray[i+1];
				numArray[i+1] = temp;
			}
		}
		end--;
	}
	return numArray;
}

/*3. Reverse String
Define function: reverseStr(someStr)
Reverse and return the String.*/
	
function reverseStr(someStr){
	var newString = "";
	for(var i =someStr.length-1; i >=0; i--){
		newString = newString + someStr[i];
	}

	return newString;
}

/*4. Factorial
Define function: factorial(someNum)
Use recursion to compute and return the factorial of someNum.
*/
function factorial(someNum){
	if(someNum == 1)
		return 1;
	if(someNum == 0)
		return 1;
	return someNum*factorial(someNum-1);
}

/*5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.
*/
function substring(someStr, length, offset){

	if(offset < 0 || offset >= someStr.length){
		return window.alert("OFFSET ISNT EVEN IN STRING MAN")
	}
	if((offset + length)> someStr.length){
		return window.alert("Length too long from offset");
	}
	if(length < 0){
		return window.alert("Length is negative...??? WHY?????")
	}


	var newString = someStr.substring(offset, offset+length);
	return newString;
}

/*6. Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/
function isEven(someNum){
	if(someNum/2 == Math.floor(someNum/2)){
		return true;
	}else
		return false;
}

/*7. Palindrome
Define function isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false
*/
function isPalindrome(someStr){

	for(var i = 0; i < Math.floor(someStr.length); i++){
		if(someStr[i] != someStr[someStr.length - 1 - i]){
			return false;
		}
	}
	return true;
}


/*8. Shapes
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
  *         */

function printShape(shape, height, character){

	var isEven;
	var numOfChar = 1;
	var numOfSpace;
	var breakPoint;
		if(height/2 == Math.floor(height/2)){
		isEven = true;
	}else
		isEven = false;

	if(isEven){
		numOfSpace = Math.floor((height-1)/2);
		breakPoint = height/2;
	}else{
		numOfSpace = Math.floor((height)/2);
		breakPoint = Math.floor(height/2);
	}





	if(shape == "Square"){
		for(var i = 0; i < height; i++){
			var temp = "";
			for(var j = 0; j < height; j++){
				temp = temp + character;
			}
			console.log(temp);
		}
	}
	else if(shape == "Triangle"){
		for(var i = 0; i < height; i++){
			var temp = "";
			for(var j = 0; j <= i; j++){
				temp = temp + character;
			}
			console.log(temp);
		}
	}else if(shape == "Diamond"){
		for(var i = 0; i < height; i++){
			var temp = "";
			for(var j = 0; j < numOfSpace; j++){
				temp = temp + " ";
			}
			for(var j = 0; j < numOfChar; j++){
				temp = temp + character;
			}

			if(i < breakPoint){
				if(numOfSpace>0){
					numOfSpace = numOfSpace - 1;
					numOfChar = numOfChar +2;

				}
			}else{
				numOfSpace += 1;
				numOfChar -= 2;
			}
			console.log(temp);

		}

	}
}

/*9. Object literal
Define function traverseObject(someObj)
Print every property and it's value.
*/

function traverseObject(someObj){
	for(var prop in someObj){
		console.log(someObj[prop]);
	}
}


/*10. Delete Element
Define function deleteElement(someArr)
Print length
Delete the third element in the array.
Print length
The lengths should be the same.*/

function deleteElement(someArr){
	console.log(someArr.length);
	delete someArr[3];
	console.log(someArr.length);
}

/*11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.
*/
function spliceElement(someArr) {
	
	console.log(someArr.length);
	someArr.slice(3);
	console.log(someArr.length);	
}
/*
12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
	var john = new Person("John", 30);*/

function Person(name, age) {
	return{
		personName:name,
		personAge:age
	};
}

/*13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
	var john = getPerson("John", 30);*/

function getPerson(name, age) {
	return{
		personName:name,
		personAge:age
	};
}
 