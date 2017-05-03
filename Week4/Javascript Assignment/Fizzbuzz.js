var minIndexElement; 
var maxIndexElement; 
var startButtonElement; 
var displayElement; 


function init() {	
	minIndexElement = $("#min-index");
	maxIndexElement = $("#max-index");
	startButtonElement = $("#start-button");
	displayElement = $("#display");
	
	minIndexElement.on('input', function() {validateInput(minIndexElement);});
	maxIndexElement.on('input', function() {validateInput(maxIndexElement);});
	startButtonElement.on('click', start);
}


function start() {
	let str = '';
	let min = parseInt(minIndexElement.val());
	let max = parseInt(maxIndexElement.val());
	
	
	if ((minIndexElement.attr('isValid')) && (maxIndexElement.attr('isValid'))) { 
		for (let i = min; i <= max; i++) { 
			if (i % 3 === 0 && i % 5 !== 0) { 
				str += 'FIZZ, ';
			}
			else if (i % 5 === 0 && i % 3 !== 0) { 
				str += 'BUZZ, ';
			}
			else if (i % 3 === 0 && i % 5 ===0) { 
				str += 'FIZZ-BUZZ, ';
			}
			else { 
				str += i + ', '; 
			}
		}
		str = str.slice(0, str.length-2); 
	
		updateDisplay(str);
	}
	else {
		alert('Input is currently not valid');
	}
}


function validateInput(elem) {
	
	let value = elem.val();
	console.log(value);
	
	let validInput = new RegExp('[1-9]+[0-9]*'); 
	let invalidInput = new RegExp('[^0-9]'); 
	
	
	if (invalidInput.test(value) || !validInput.test(value) || value.length > 6) {  
		elem.attr('style', 'border: 2px solid yellow');
		
		elem.attr('isValid', false);
		return false;
	}
	
	elem.attr('style', 'border: 2px solid pink'); 
	
	elem.attr('isValid', true);
	return true;
}

function updateDisplay(str) {
	displayElement.text(str);
}