// problem number 1.
function fib(n)
{
	var num1 = 1, num2 = 0, temp;

  while (n >= 0){
    temp = num1;
    num1 = num1 + num2;
    num2 = temp;
    num--;
  }

  return num2;

	
}

// problem number 2
var numArray = [2,123,1,23,,1,2,3,41,2,4,123,123,4,1,2,32,4,3,1,23,1];
function bubbleSort(numArray)
{
    var temp;
    do {
        temp = false;
        for (var i=0; i < numArray.length-1; i++) {
            if (numArray[i] > numArray[i+1]) {
                var temp = numArray[i];
                numArray[i] = numArray[i+1];
                numArray[i+1] = temp;
                temp = true;
            }
        }
    } while (temp);
}
//problem 3 
	var someStr = "LOLWW";
	function reverseStr(someStr)
	{
		
		return someStr.split("").reverse().join("");
	}
	
//problem 4
var someNum = 13;
function factorial(someNum) {
  if (someNum == 0) {
    return 1;
  }
  return someNum * factorial(someNum - 1);
}
//problem 5 
var length = 10;
var someStr = "WHAT IS THIS STRING DOING HERE";
var offset = 15;
function substring(someStr, length, offset)
{

	if((length< 0 )||(length > someStr.length))
	{
		window.alert("Incorrect length");
		
	}
	else if((offset< 0) || (offset>someStr.length))
	{
		window.alert("Incorrect offset");
		
	}

else{
	var a = "";

        for ( var i = offset; i < length; i++) {
           a += someStr[i];
        }
console.log(a);
if(a === "undefined")
{
	window.alert("Incorrect !");
}else{
	
	return a;}

}
}
//problem 6
var someNum = 22;
function isEven(someNum)
{
	
if((someNum & 1)==0)
{
	
return true;	
	
}	
else
{
	
return	false;
}


}
//problem 7

var someStr = "doooooooooooooooooooood";

function isPalindrome(someStr){
	
	var temp = someStr;
	if(temp == someStr.split("").reverse().join(""))
	{
		return true;
	}
	else
	{
		
		return false;
	}
}

function reverseStr(someStr)
	{
		
		return someStr.split("").reverse().join("");

		}
		
		
//problem 8 


function printShape(someStr,someNum,someSymbol)
{
	var temp = "";
if(someStr == "Square")
{
	
for(var i = 0; i<someNum;i++)
{
	
temp += someSymbol;	
}
for(var i = 0; i<someNum;i++)
{
	console.log(temp);
	
}

}
else if(someStr == "Triangle")
{
for(var i = 0; i<someNum;i++)
{
	
temp += someSymbol;	
console.log(temp);
}
}

else if(someStr == "Diamond")
{


   
//                var limit = document.getElementById("limit").value;
var i = 0; 
var j = 0;
			var space = limit;
                for (i = 1; i <= limit; i++) {
						var spaced;
				var spaces= "";
				var appended= "";
                
                    for (j = 1; j <= space; j++) {
                         spaces+= " ";
						}
                    space--;
                    for (j = 1; j <= 2 * i - 1; j++) {
                        spaces+=someSymbol;
						
                    }
					 appended = spaces;
					console.log(appended);
                  
                }

                space = 2;
                for (i = 1; i <= limit; i++) {
				var spaced= "";
				var spaces= "";
				var appended= "";
               
                    for (j = 1; j <= space; j++) {
                        spaces+= " ";
                    }
                    space++;
                    for (j = 1; j <= 2 * (limit - i) - 1; j++) {
                       spaces+=someSymbol;
                    }
                   appended = spaces;
				console.log(appended);
                }
              
            }


  }





  
//problem 9
var someObj = {type:"Other", model:"123", color:"Transparent"};
function traverseObject(someObj)
{

for(var propName in someObj) {
    propValue = someObj[propName]

    console.log(propName,propValue);
}
	
	
}

//problem 10
someArr = [123,456,235,123,123,123];
function deleteElement(someArr)
{
	console.log("The Length is " + someArr.length);
	console.log("DELETEING 3RD ELEMENT");
	delete someArr[2];
	console.log("The Length is " + someArr.length);
	
	
}
//problem 11
someArr = [123,456,235,123,123,123];
function spliceElement(someArr)
{
	console.log("The Length is " + someArr.length);
	console.log("Splicing 3RD ELEMENT");
    someArr.splice (3, 1);
	console.log("The Length is " + someArr.length);
	
	
}
//problem 12
var Person = {
    firstName:"David",
    age : 9999,
};

function Person(name,age) {
  this.name = name;
   this.age = age;
  }
var john = new Person("John",30);
//Problem 13
function getPerson(name,age){

return new Person(name,age);

}
var john = getPerson("John", 30);