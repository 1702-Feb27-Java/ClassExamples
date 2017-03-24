/*1)Fibonacci
 * 	Define function: fib(n) 
 *	Return the nth number in the fibonacci sequence.
 */
function sortArray(a){
		 aLen = a.length;
		 temp=0;
		for( i=0; i<=aLen; i++){
			for( j=1;j<aLen;j++){
				if(a[j-1]>a[j]){
					temp=a[j-1];
					a[j-1]=a[j];
					a[j] = temp;
				}			
			}
		}
}

/*2)Bubble Sort
 *	Define function: bubbleSort(numArray)
 *	Use the bubble sort algorithm to sort the array.
 *	Return the sorted array.
 */
function fib(num){
		iTotal=0;
		i=0,j=1; 
		 
		 if(num==1)
			 return 0;
		 else if (num==2)
			 return 1;

		 iCount=3; 
		while(iCount++<=num)
		{
			iTotal = i + j;
			i=j;
			j=iTotal;
		}
		
		return iTotal;
}


/*3)Reverse String
 *	Define function: reverseStr(someStr)
 *	Reverse and return the String.
 */
function reverseStr(someStr){
	Temp = "";
	for(i=someStr.length-1;i>=0;i--) 
	{
		Temp += someStr[i];
	}
	return Temp;
}


/*4)Factorial
 *	Define function: factorial(someNum)
 *	Use recursion to compute and return the factorial of someNum.
 */
function Factorial( iFact){
		if(iFact<=1)
			return 1;
		
		int iTot = iFact;
		while(iFact >1){
			
			iTot = iTot * (iFact-1);
			iFact--;
		}
		return iTot;
}

/*
5. Substring
Define function substring(someStr, length, offset)
Return the substring contained between offset and (offset + length) inclusively.
If incorrect input is entered, use the alert function and describe why the input was incorrect.
*/
function getSubString(sSTR, offSTR, lenSTR){
	var SizeOfSTR = sSTR.length;
	
	if(offSTR > SizeOfSTR &&  lenSTR > SizeOfSTR){
		window.alert("ERROR : Entered offset & Length is greater than given string. \nPlease enter valid offset & Length parameters.");
	}
	else if(offSTR > SizeOfSTR){
		window.alert("ERROR : Entered offset is greater than given string. Please enter valid offset value.");
	}
	else if(lenSTR > SizeOfSTR){
		window.alert("ERROR : Entered length is greater than given string. \nPlease enter valid length value.");
	}

	if((offSTR+lenSTR) >= SizeOfSTR){
		numTraverse = SizeOfSTR-1;}
	else{
		numTraverse = (offSTR+lenSTR);
    }
	Temp="";
	for(i=offSTR;i<=numTraverse;i++) 
	{
		Temp += sSTR[i];
	}
	return Temp;
}


/*6) Even Number
 * 	 Define function: isEven(someNum)
 *	 Return true if even, false if odd.
 *	 Do not use % operator.
 */
function isEven(iNum){
		var iN = (iNum/2);
		var iNf = iN.toString();
		if((iNf.indexOf(".") >= 0) || (iNum == 1))
		{
			return false;
		}
		else
		{ 
			return true;
		}
}

/*7)Palindrome
 * Define function isPalindrome(someStr)
 * Return true if someStr is a palindrome, otherwise return false
 */
function isPalind(sSTR){
	
	tRev = "";
	for(i=sSTR.length-1;i>=0;i--) 
	{
		tRev += sSTR[i];
	}
	
	if(tRev == sSTR)
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
function printShape(shape, height, charTPrint){
		var lineToPrint="";
		switch (shape){
		case "Triangle" : j=0;
						  for( i=1; i<=height;i++){		//No of rows
						   j++;
						   for( k=1;k<=j;k++){		//No of columns vary based on jth element
							 lineToPrint+=charTPrint;
							 if(k==j)
								lineToPrint+="\n";
						   }
						  }
						  console.log(lineToPrint);
						  break;
		case "Square"	: for( si=0;si<height;si++){
							for( sj=0;sj<height;sj++){
								lineToPrint+=charTPrint;
							}
							lineToPrint+="\n";
						  }
						  console.log(lineToPrint);
						  break;
		case "Diamond"  : midPoint = height/2;
						  if((midPoint.toString()).indexOf(".") > 0){
							  midPoint+=0.5;
						  }
						  console.log("Midpoint = " + midPoint);
						  start=midPoint, end=midPoint;
						  midRteached = false;
						  for( di=1;di<=height;di++){
							  for( dj=0;dj<=height;dj++){
								 if(dj>=start && dj<=end)
									 lineToPrint+=charTPrint;
								 else
									 lineToPrint+=" ";
							  }
							  if(di>=midPoint){
								  start++;
								  end--; 
							  }
							  else{
							  start--;
							  end++;
							  }
							  lineToPrint+="\n";
						  }
						  console.log(lineToPrint);
						  break;
		}
}

/*9)Object Literal .
 *	Define function traverseObject(someObj) Print every property and it's value.
 */
function traverseObject(Obj){
	for(var propertyName in Obj){
		if(Obj.hasOwnProperty(propertyName)){
			console.log( propertyName + " : " + Obj[propertyName] );
		}
	}
}


/*10)Delete Element
 *	 Define function deleteElement(someArr)
 *	 Print length
 *	 Delete the third element in the array.
 *	 Print length
 *	 The lengths should be the same.
 */
function deleteElement(sArray){
	console.log("Input Array Length  : "+sArray.length);
    console.log("Array  : "+sArray);
	var iCount=0;
	for(x in sArray){
		iCount++;
		if(iCount==3){
			delete sArray[x];
			console.log("Successful: Delete of 3rd Element");
			break;
		}
	}
    console.log("Modified Array Length  : "+sArray.length);
    console.log("Array  : "+sArray);
}
 
/*11)Splice Element
 *	 Define function spliceElement(someArr)
 *	 Print length
 *	 Splice the third element in the array.
 * 	 Print length
 *	 The lengths should be one less than the original length.
 */
function spliceElement(sArray,position){
	console.log("Input Array Length  : "+sArray.length);
    console.log("Array  : "+sArray);
	if(position == null || position==0){
		console.log("NO-UPDATES: Please enter a valid position to Splice / Delete ");
	}
	else if(position >0){
		sArray.splice(position-1,1);  // for user, 1st Position is the 0th position for developer.
		console.log("Successful: Splice of POS[ "+position+" ] Element");
 		console.log("Modified Array Length  : "+sArray.length);
    	console.log("Array  : "+sArray);
    }
}
 
/*12)Defining an object using a constructor
 *	 Define a function Person(name, age)
 * 	 The following line should set a Person object to the variable john:
 *		var john = new Person("John", 30);
 */
 function Person(name, age){
	this.name=name;
	this.age=age;
}

 
/*13)Defining an object using an object literal
 *	 Define function getPerson(name, age)
 *	 The following line should set a Person object to the variable john:
 *	  	var john = getPerson("John", 30);
 */
function getPerson(name, age){
	return {"name":name, "age":age}; // Example -> Object {name : "Lyn Pais", age:28}
}

function getPerson(name, age){
	return new Person(name,age);	//  Prereq :( Q12 ) :: Example -> Person {name : "Lyn Pais", age:28} 			
}