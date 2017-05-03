/*
 * TITLE: JavaScript Assignment 1
 * AUTHOR: Michael Hobbs
 * DATE: 2017 March 22
 *
 */

/*
 * QUESTION 1: FIBONACCI
 *
 * Returns the nth number in the Fibonacci sequence.
 *
 * NOTE: The Fibonacci sequence here is assumed to start with 0 as the first number in the sequence.
 *
 */
 function fib(n) {
	 if (n <= 0) {
		 return undefined;
	 }
	 else if (n == 1) {
		 return 0;
	 }
	 else if (n == 2 || n == 3) {
		 return 1;
	 }
	 else { //the computation of the sequence will begin on the third index (on the second 1).
		 var first = 1;
		 var second = 1;
		 var third = null;
		 var fibIndex = 3;
		 while (fibIndex < n) {
			 // move up through the sequence.
			 third = first + second; //compute the value at the current index.
			 first = second;
			 second = third;
			 fibIndex++;
		 }
		 return third;
	 }
 }
 
/*
 * QUESTION 2: BUBBLE SORT
 *
 * Bubble-sorts an array.
 *
 */
 function bubbleSort(numArray) {
	 for (let i = 0; i < numArray.length; i++) {
		 for (let j = 0; j < numArray.length; j++) { //visit every element from the beginning everytime to allow all larger values to bubble up and small values bubble down correctly.
			 if (numArray[j] > numArray[j+1]) {
				 //move the larger value up in the array.
				 let largerValue = numArray[j];
				 numArray[j] = numArray[j+1];
				 numArray[j+1] = largerValue;
			 }
		 }
	 }
	 return numArray;
 }
 
/*
 * QUESTION 3: STRING REVERSAL
 *
 * Reverses a string.
 *
 */
 function reverseStr(someStr) {
	 let s = ''; //to hold the reversed string.
	 let reverseIndex = someStr.length-1;
	 while (reverseIndex >= 0) { //loop backward from the end of the string to get the reverse of it.
		 s += someStr[reverseIndex]; //characters in strings can be accessed using bracket notation.
		 reverseIndex--;
	 }
	 return s;
 }
 
/*
 * QUESTION 4: FACTORIAL
 *
 * Computes the nth factorial.
 *
 * NOTE: Uses recursion to do so.
 *
 */
 function factorial(someNum) {
	 if (someNum == 1) { //base case that ends recursion when someNum is 1.
		 return 1;
	 }
	 return someNum * factorial(someNum-1); //do someNum * (someNum-1) * (someNum-2) * ...
 }
 
/*
 * QUESTION 5: SUBSTRING
 *
 * Returns a substring from a given offset for the given number of the characters.
 *
 * If offset+length is greater than someStr's length then the substring returned is from offset till the string's end (someStr.length - offset computes the remainder of the string).
 *
 * NOTE: It does not use any available functions that already perform this.
 *
 */
 function substring(someStr, length, offset) {
	 if (offset >= someStr.length) {
		 alert('Offset cannot be greater or equal to length of the string');
	 }
	 if ((offset+length) > someStr.length) {
		 length = someStr.length - offset; //cap length to the remainder of the string from the offset to the actual end of the string.
	 }
	 
	 let s = '';
	 for (let count = 0; count < length; count++) { //get the substring from offset within someStr; the number of characters in the substring is length.
		 s += someStr[offset + count];
	 }
	 return s;
 }
 
/*
 * QUESTION 6: EVEN NUMBER
 *
 * Checks whether a given number is even.
 *
 * NOTE: It does use the modulus operator ( % ) in doing so.
 *
 */
 function isEven(someNum) {
	 let n = someNum;
	 
	 if (Math.floor(n / 2) * 2 === someNum) { //the number is even if twice the result of dividing it by 2 is itself again
		 return true;
	 }
	 else {
		 return false;
	 }
 }
 
/*
 * QUESTION 7: PALINDROME
 *
 * Checks whether a given string is a palindrome.
 *
 */
 function isPalindrome(someStr) {
	 for (i = 0, reverseI = someStr.length-1; i < reverseI; i++, reverseI--) {
		 if (someStr[i] != someStr[reverseI]) { //if the beginning of the string is not like the end at the current index at both ends moving inward, then the string is not a palindrome.
			 return false;
		 }
	 }
	 return true;
 }
 
/*
 * QUESTION 8: SHAPES
 *
 * Prints a Square, Triangle, or Diamond with the given height and character.
 *
 */
 function printShape(shape, height, character) {
	 switch (shape.toLowerCase()) {
		 case 'square':
			let s = '';
			for (let i = 0; i < height; i++) {
				for (let j = 0; j < height; j++) {
					s += character;
				}
				s += '\n';
			}
			console.log(s);
			break;
		 case 'triangle':
			let t = '';
			let currentWidth = 0;
			let maxWidth = 1;
			for (let i = 0; i < height; i++) {
				for (currentWidth = 0; currentWidth < maxWidth; currentWidth++) {
					t += character;
				}
				maxWidth++;
				t += '\n';
			}
			console.log(t);
			break;
		 case 'diamond':
			let d = '';
			let numberSpaces = Math.floor(height/2);
			let numberChars = 1;
			
			// top-half iteration
			for (let i = 0; i < Math.floor(height/2); i++) {
				for (let currentNumberSpaces = 0; currentNumberSpaces < numberSpaces; currentNumberSpaces++) {
					d += ' ';
				}
				for (let currentNumberChars = 0; currentNumberChars < numberChars; currentNumberChars++) {
					d += character;
				}
				for (let currentNumberSpaces = 0; currentNumberSpaces < numberSpaces; currentNumberSpaces++) {
					d += ' ';
				}
				d += '\n';
				numberSpaces--;
				numberChars += 2;
			}
			
			//middle line
			for (let i = 0; i < height; i++) {
				d += character;
			}
			d += '\n';
			
			//bottom-half iteration
			numberSpaces = 1;
			numberChars = height-2;
			
			for (let i = 0; i < Math.floor(height/2); i++) {
				for (let currentNumberSpaces = 0; currentNumberSpaces < numberSpaces; currentNumberSpaces++) {
					d += ' ';
				}
				for (let currentNumberChars = 0; currentNumberChars < numberChars; currentNumberChars++) {
					d += character;
				}
				for (let currentNumberSpaces = 0; currentNumberSpaces < numberSpaces; currentNumberSpaces++) {
					d += ' ';
				}
				d += '\n';
				numberSpaces++;
				numberChars -= 2;
			}
			
			console.log(d);
			break;
		 default:
			alert('Unknown shape');
			break;
	 }
 }
 
/*
 * QUESTION 9: OBJECT LITERAL
 *
 * Prints all the key-value pairs of a given object.
 */
 function traverseObject(someObj) {
	 for (let prop in someObj) {
		 console.log(prop + ': ' + someObj[prop]);
	 }
 }
 
/*
 * QUESTION 10: DELETE ARRAY ELEMENT
 *
 * Deletes the element of an array at the given index.
 *
 * NOTE: It hardcodes a deletion of the 3rd element.
 *
 */
 function deleteElement(someArr) {
	 console.log(someArr + " - Length before deletion: " + someArr.length);
	 delete someArr[2];
	 console.log(someArr + " - Length after deletion: " + someArr.length);
 }
 
/*
 * QUESTION 11: SPLICE ELEMENT
 *
 * Removes the element of an array at the given index.
 *
 */
 function spliceElement(someArr) {
	 console.log(someArr + " - Length before splice: " + someArr.length);
	 someArr.splice(2, 1);
	 console.log(someArr + " - Length after splice: " + someArr.length);
 }
 
/*
 * QUESTION 12: DEFINING AN OBJECT USING A CONSTRUCTOR
 *
 * Defines a constructor to return an object.
 *
 */
 function Person(name, age) {
	 return {
		 name: name,
		 age: age
	 };
 }
 
/*
 * QUESTION 13: DEFINING AN OBJECT USING AN OBJECT LITERAL
 *
 * Defines a function to return an object.
 *
 */
 function getPerson(name, age) {
	 return {
		 name: name,
		 age: age
	 };
 }
 
 