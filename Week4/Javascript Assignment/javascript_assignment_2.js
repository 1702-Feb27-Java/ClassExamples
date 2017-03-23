/*
 * TITLE: JavaScript Assignment 2
 * AUTHOR: Michael Hobbs
 * DATE: 2017 March 23
 *
 */


var minIndexElement; //holds the min in the user interface
var maxIndexElement; //holds the max in the user interface
var startButtonElement; //starts the fizz buzz in the user interface
var displayElement; //displays the fizz buzz sequence in the user interface

/*
 * Initializes the user interface and the app.
 *
 */
function init() {	
	minIndexElement = document.getElementById("min-index");
	maxIndexElement = document.getElementById("max-index");
	startButtonElement = document.getElementById("start-button");
	displayElement = document.getElementById("display");
	
	minIndexElement.addEventListener('input', function() {validateInput(minIndexElement);}, false);
	maxIndexElement.addEventListener('input', function() {validateInput(maxIndexElement);}, false);
	startButtonElement.addEventListener('click', start, false);
}

/*
 * Computes the fizz buzz sequence.
 *
 */
function start() {
	let str = '';
	let min = parseInt(minIndexElement.value);
	let max = parseInt(maxIndexElement.value);
	//console.log(min + ', ' + max);
	
	if (minIndexElement.isValid && maxIndexElement.isValid) { //do the sequence only if input is valid.
		for (let i = min; i <= max; i++) { //print the numbers in the range from min to max, or 'fizz', 'buzz', or 'fizz-buzz' accordingly.
			if (i % 3 === 0 && i % 5 !== 0) { //when is a multiple of 3 but not also a multiple of 5
				str += 'FIZZ, ';
			}
			else if (i % 5 === 0 && i % 3 !== 0) { //when is a multiple of 5 but not also a multiple of 3
				str += 'BUZZ, ';
			}
			else if (i % 3 === 0 && i % 5 ===0) { //when is a multiple of both 3 and 5
				str += 'FIZZ-BUZZ, ';
			}
			else { //when neither a multiple of 3 nor 5
				str += i + ', '; //print the number
			}
		}
		str = str.slice(0, str.length-2); //remove the trailing ', '.
	
		updateDisplay(str);
	}
	else {
		alert('Input is currently not valid');
	}
}

/*
 * Validates input in the user input.
 *
 */
function validateInput(elem) {
	//let value = parseInt(elem.value);
	let value = elem.value;
	console.log(value);
	
	let validInput = new RegExp('[1-9]+[0-9]*'); //valid input is a non-zero number.
	let invalidInput = new RegExp('[^0-9]'); //invalid input is a non-digit.
	
	//console.log(validInput.test(value)); //debugging the regex statements
	//console.log(invalidInput.test(value));
	
	//if (isNaN(value) || value <= 0) {
	if (invalidInput.test(value) || !validInput.test(value) || value.length > 6) { //indicate input was invalid.
		elem.style.border = '2px solid red';
		//console.log(false);
		elem.isValid = false;
		return false;
	}
	
	elem.style.border = '2px solid green'; //indicate input was valid.
	//console.log(true);
	elem.isValid = true;
	return true;
}

/*
 * Updates the display of the fizz buzz sequence.
 *
 */
function updateDisplay(str) {
	displayElement.innerHTML = str;
}
