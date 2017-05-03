-----------------------------------------------------------------------------------
PART I

Create a single Javascript file called homework.js to answer these questions.
Please put the question itself as a comment above each answer.
-----------------------------------------------------------------------------------

1. Fibonacci
Define function: fib(n) 
Return the nth number in the fibonacci sequence.

function fib(n){
  var a = 1, b = 0, temp;

  while (n >= 0){
    temp = a;
    a = a + b;
    b = temp;
    n--;
  }

  return b;
}

2. Bubble Sort
Define function: bubbleSort(numArray)
Use the bubble sort algorithm to sort the array.
Return the sorted array.

function bubbleSort(numArray)
{
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
}

3. Reverse String
Define function: reverseStr(someStr)
Reverse and return the String.

function reverseStr(someStr){
    return someStr.split("").reverse().join("");
}

4. Factorial
Define function: factorial(someNum)
Use recursion to compute and return the factorial of someNum.

function factorial(someNum) {
  if (someNum === 0) {
    return 1;
  }
  
  return n * factorial(n - 1);
}

5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.

substring(someStr, length, offset){
	return someStr.substring(offset, offset + length + 1);
}

6. Even Number
Define function: isEven(someNum)
Return true if even, false if odd.
Do not use % operator.
function isEven(someNum){
	if( (number&1) == 0){
		return true;
	}
	else{
		return false;
	}
}


7. Palindrome
Define function isPalindrome(someStr)
Return true if someStr is a palindrome, otherwise return false

function isPalindrome(someStr) {
    var len = someStr.length;
    for ( var i = 0; i < Math.floor(len/2); i++ ) {
        if (someStr[i] !== someStr[len - 1 - i]) {
            return false;
        }
    }
    return true;
}

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
			let tri = '';
			let currentWidth = 0;
			let maxWidth = 1;
			for (let i = 0; i < height; i++) {
				for (currentWidth = 0; currentWidth < maxWidth; currentWidth++) {
					tri += character;
				}
				maxWidth++;
				tri += '\n';
			}
			console.log(tri);
			break;
		 case 'diamond':
			let diamond = '';
			let numberSpaces = Math.floor(height/2);
			let numberChars = 1;
			
			// top-half iteration
			for (let i = 0; i < Math.floor(height/2); i++) {
				for (let currSpace = 0; currSpace < numberSpaces; currSpace++) {
					diamond += ' ';
				}
				for (let currentNumberChars = 0; currentNumberChars < numberChars; currentNumberChars++) {
					diamond += character;
				}
				for (let currSpace = 0; currSpace < numberSpaces; currSpace++) {
					diamond += ' ';
				}
				diamond += '\n';
				numberSpaces--;
				numberChars += 2;
			}
			
			//middle line
			for (let i = 0; i < height; i++) {
				diamond += character;
			}
			diamond += '\n';
			
			//bottom-half iteration
			numberSpaces = 1;
			numberChars = height-2;
			
			for (let i = 0; i < Math.floor(height/2); i++) {
				for (let currSpace = 0; currSpace < numberSpaces; currSpace++) {
					diamond += ' ';
				}
				for (let currentNumberChars = 0; currentNumberChars < numberChars; currentNumberChars++) {
					diamond += character;
				}
				for (let currSpace = 0; currSpace < numberSpaces; currSpace++) {
					diamond += ' ';
				}
				diamond += '\n';
				numberSpaces++;
				numberChars -= 2;
			}
			
			console.log(d);
			break;
		 default:
			alert('Error Try Again');
			break;
	 }
 }

9. Object literal
Define function traverseObject(someObj)
Print every property and it's value.

function traverseObject(someObj) {
	 for (let temp in someObj) {
		 console.log(temp + ': ' + someObj[temp]);
	 }
 }

10. Delete Element
Define function deleteElement(someArr)
Print length
Delete the third element in the array.
Print length
The lengths should be the same.

function deleteElement(someArr) {
	 console.log(someArr + " - Length Before: " + someArr.length);
	 delete someArr[2];
	 console.log(someArr + " - Length After: " + someArr.length);
 }

11. Splice Element
Define function spliceElement(someArr)
Print length
Splice the third element in the array.
Print length
The lengths should be one less than the original length.

function spliceElement(someArr) {
	 console.log(someArr + " - Length Before: " + someArr.length);
	 someArr.splice(2, 1);
	 console.log(someArr + " - Length After: " + someArr.length);
 }

12. Defining an object using a constructor
Define a function Person(name, age)
The following line should set a Person object to the variable john:
	var john = new Person("John", 30);

	function Person(name, age) {
	 return {
		 name: name,
		 age: age
	 };
 }

13. Defining an object using an object literal
Define function getPerson(name, age)
The following line should set a Person object to the variable john:
	var john = getPerson("John", 30);

	function getPerson(name, age) {
	 return {
		 name: name,
		 age: age
	 };
 }
 