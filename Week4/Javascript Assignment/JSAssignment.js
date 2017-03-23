//Number 1: Fibonacci
function fib(n)
{
	var x = 0;
	var y = 1;
	if(n <= 2)
	{
		return n-1;
	}
	for(var i = 1; i < n; i++)
	{
		var tempY = y; y = tempY + x; x = tempY; 
	}
	return y;
}

//Number 2: Bubble Sort
var a = [34, 203, 3, 746, 200, 984, 198, 764, 9];
function bubbleSort(numArray)
{
	var swapped;
    do 
    {
        swapped = false;
        for (var i=0; i < a.length-1; i++)
        {
            if (a[i] > a[i+1]) 
            {
                var temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
}
bubbleSort(a);
console.log(a);

//Number 3: Reverse String
function reverseStr(someStr)
{
	return someStr.split("").reverse().join("");
}

//Number 4: Factorial
function factorial(someNum)
{
	temp = 1;
	for(i = someNum; i > 0; i--)
	{
		temp *= i;
	}
	return temp;
}

//Number 5: Substring
function substring(someStr, length, offset)
{
	var temp = "";
	for(i = offset; i <= offset+length; i++)
	{
		temp = temp.concat(someStr[i]);
	}
	return temp;
}

//Number 6: Even Number
function isEven(someNum)
{
	x = Math.trunc(someNum/2);
	if(someNum/x == 2)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//Number 7: Palindrome
function isPalindrome(someStr)
{
	return someStr == someStr.split("").reverse().join("");
}

//Number 8: Shapes
function printShape(shape, height, character)
{

}

//Number 9: Object Literal
function traverseObject(someObj)
{
	for(c in someObj)
	{
		console.log(typeof(someObj[c]) + " : " +someObj[c]);
	}
}

//Number 10: Delete Element
function deleteElement(someArr)
{
	console.log(someArr.length + " : " + someArr);
	for(x in someArr)
	{
		if(x == 2)
		{
			someArr[x] = null;
		}
	}
	console.log(someArr.length  + " : " + someArr);
}

//Number 11: Splice Element
function spliceElement(someArr)
{
	var temp;
	console.log(someArr.length + " : " + someArr);
	for(x in someArr)
	{
		if(x != 2)
		{
			temp.push(someArr[x]);
		}
		someArr = temp;
	}
	console.log(someArr.length + " : " + someArr);
}

//Number 12: Define object using a Constructer
function person(first, age) 
{
    this.firstName = first;
    this.age = age;
}
var john = new person("John", 30);

//Number 13: Define an object using an object literal
function getPerson(name, age) 
{
   return {
       name: name,
       age : age
   };
}