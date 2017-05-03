/*
----------------------------------------------------------------------------------
PART I

Create a single Javascript file called homework.js to answer these questions.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------


1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.
*/

function fib(n){
	var first = 0;
	var second = 0;
	var nextNum;
	
	for (i = 0; i < n;i++){
		if (i <= 1){
			nextNum = i;
		} else {
			nextNum += (first + second);
			first = second;
			second = nextNum;
		}
	}
	return nextNum;
}

/*
2. Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array.
Return the sorted array.
*/
function bubbleSort(numArray){
	
	if (numArray.length <=1)
		return numArray;
	else{
		for (i = 0; i < numArray.length-1;i++){
			for (j = i+1; j < numArray.length;j++){
				if (numArray[i] > numArray[j]){
					temp = numArray[i];
					numArray[i] = numArray[j];
					numArray[j] = temp;
					i++;
				}
			}
		}
	}
	return numArray;
}
/*
3. Reverse String
Define function: reverseStr(someStr)
Reverse and return the String.
*/
function reverseStr(someStr){
	newString = "";
	for (i=someStr.length-1;i>=0;i--){
		newString += someStr[i];
	}
	return newString;
}
/*
4. Factorial
Define function: factorial(someNum)
Use recursion to compute and return the factorial of someNum.
*/
function factorial(someNum){
	total = 0;
	if (someNum==1)
		return someNum;
	else {
	total += someNum * factorial(someNum-1);
	}
	return total;
}
/*
5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.
*/
function substring(someStr, length, offset){
	newString = "";
	if (someStr.length !=length)
		return window.alert("You did not enter in the correct length of the string");
	if (someStr.length < offset)
		return window.alert("You entered an offset less than the string length");
	for (offset; offset<length;offset++){
		newString += someStr[offset];
	}
	return newString;
}
/*
6. Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
*/
function isEven(someNum){
	if(Math.pow(-1, someNum)==1{
		return true;
	else
		return false;
	}
}
/*	
7. Palindrome
Define function isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false
*/
function isPalindrome(someStr){
	newString = "";
	for (i=someStr.length-1;i>=0;i--){
		newString += someStr[i];
	}
	if (someStr==newString)
		return true; 
	else 
		return false;
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
			var result = "\n";
			for (i = 0; i < height;i++){
				for (j = 0; j < height;j++)
					result+=character;
				result += "\n";
			}
			console.log(result);
		case "Triangle":
			var result = "\n";
			for (i = 0; i < height;i++){
				for (j = 0; j < i+1;j++)
					result+=character;
				result += "\n";
			}
			console.log(result);
		case "Diamond":
			var result = "\n";
			space = height - 1;
			for (k = 1; k <= height; k++){
				for (c = 1; c <= space; c++)
					result+=" ";
				space--;
				
				for (c = 1; c <= 2*k-1; c++)
					result+= character;
				result+="\n";
			}
 
			space = 1;
 
			for (k = 1; k <= height - 1; k++){
				for (c = 1; c <= space; c++)
					result+=" ";
				space++;
 
				for (c = 1 ; c <= 2*(height-k)-1; c++)
					result+=character;
				result+="\n";
			}	
			console.log(result);
		default:
			window.alert("You did not enter a valid item");
	}
}
/*
9. Object literal
Define function traverseObject(someObj)
Print every property and it's value.
*/
function traverseObject(someObj){
	var result="";
	for (property in someObj)
		console.log("someObj[" + property + "]: " + someObj[property]);
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
	console.log(someArr.length);
	delete someArr[2];
	console.log(someArr.length);
/*
11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.
*/
function spliceElement(someArr){
	console.log(someArr.length);
	someArr.splice(2, 1);
	console.log(someArr.length);
}
/*
12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
	var john = new Person("John", 30);
*/
var Person = function(name, age) {
    this.name = name;
    this.age = age;
}
/*
13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
	var john = getPerson("John", 30);
*/
Person.prototype = {
    getName: function() {
        return this.name;
    },

    setName: function(name) {
        this.name = name;
    }
}
 