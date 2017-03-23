//-----------------------------------------------------------------------------------
//PART I
//
//Create a single Javascript file called homework.js to answer these questions.
//Please put the question itself as a comment above each answer.
//-----------------------------------------------------------------------------------
//
//1. Fibonacci
//Define function: fib(n) 
//Return the nth number in the fibonacci sequence.
//

    function fib(n)
    {
        if ( n <2)
            return 1;
        return fib(n-1) + fib(n-2);
    }

//============================================================================================================
//2. Bubble Sort
//Define function: bubbleSort(numArray)
//Use the bubble sort algorithm to sort the array.
//Return the sorted array.
//
    function bubbleSort(numArray)
    {
        var sz = numArray.length;
        var swap;
        do
        {
            swap = false;
            for (var i =1; i <= sz; i++) 
            {
                if (numArray[i-1] > numArray[i])
                {
                    var temp = numArray[i];
                    numArray[i] = numArray[i-1];
                    numArray[i-1] = temp;
                    swap = true;
                }
            }
        }while(swap);
        console.log(numArray);
    }
    
//============================================================================================================
//3. Reverse String
//Define function: reverseStr(someStr)
//Reverse and return the String.
//
    function reverseStr(someStr)
    {
       return someStr.split("").reverse().join(""); 
    }
//============================================================================================================
//4. Factorial
//Define function: factorial(someNum)
//Use recursion to compute and return the factorial of someNum.
//
    function factorial(someNum)
    {
        if (someNum == 1)
        {
            return 1
        }
        return someNum*factorial(someNum-1);

    }
//============================================================================================================
//5. Substring
//Define function substring(someStr, length, offset)
//Return the substring contained between offset and (offset + length) inclusively.
//If incorrect input is entered, use the alert function and describe why the input was incorrect.
//
    function substring (someStr, length, offset)
    {
        var sz = someStr.length;
        if (offset > sz-1 || offset < 0)
        {
            alert("Offset is not within range of string");
        }
        else if (length < 0)
        {
            alert("Lenght of substring cant be negative");
        }
        else if ( sz - offset < length)
        {
            alert("Length exceeds possible characters");
        }
        else
        {
            var x = "";
            for ( var i = offset; i <= length; i++)
                x+=someStr[i];
           return x;
        }
    }
//============================================================================================================
//6. Even Number
//Define function: isEven(someNum)
//Return true if even, false if odd.
//Do not use % operator.
//
    function isEven(someNum)
    {
        var temp = someNum.toString();
        var num = temp[temp.length-1];
        if (num/2  == 0)
        {
            return true;
        }
        return false;
    }
//============================================================================================================
//7. Palindrome
//Define function isPalindrome(someStr)
//Return true if someStr is a palindrome, otherwise return false
//
    function isPalindrome(someStr)
    {
        return someStr == someStr.split("").reverse().join("");
    }
//============================================================================================================
//8. Shapes
//Define function: printShape(shape, height, character)
//shape is a String and is either "Square", "Triangle", "Diamond".
//height is a Number and is the height of the shape. Assume the number is odd.
//character is a String that represents the contents of the shape. Assume this String contains just one character.
//Use a switch statement to determine which shape was passed in.
//Use the console.log function to print the desired shape.
//Example for printShape("Square", 3, "%");
//%%%
//%%%
//%%%
//Example for printShape("Triangle", 3, "$");
//$
//$$
//$$$
//Example for printShape("Diamond", 5, "*");
//  *
// ***
//*****
// ***
//  *
//
    function printShape(shape, height, character)
    {
        switch(shape)
        {
            case "Triangle":
                for (var i =0; i < height; i ++)
                {
                    var bucket ="";
                    for(var x = 0; x <= i; x++)
                    {
                        bucket += character;
                    }
                    console.log(bucket);
                }
                break;
            case "Square":
                for (var i = 0; i < height; i++)
                {
                    var bucket ="";
                    for (var x = 0; x < height; x++)
                    {
                        bucket += character;
                    }
                    console.log(bucket);
                }
                break;
          case "Diamond":
                bucket ="";
                space = height - 1;
                for (k = 1; k <= height/2 +1; k++)
                {
                    for (c = 1; c <= space; c++)
                    {
                        bucket+=" ";
                    }
                    space--;
                    for (c = 1; c <= 2*k-1; c++)
                        bucket += character;
                        
                    console.log(bucket);
                    bucket ="";
                }
                space +=2;
                bucket=""
                for (k = 1; k <= (height)/2; k++)
                {
                    for (c = 1; c <= space; c++)
                        bucket += " ";
                    space++;
                    for (c = 1; c <= 2*(height/2-k); c++)
                        bucket += character;
                    
                    console.log(bucket);
                    bucket ="";
                }

                break;
        }
    }
//============================================================================================================
//9. Object literal
//Define function traverseObject(someObj)
//Print every property and it's value.
//
    function traverseObj(obj)
    {
        for ( var key in obj)
        {
            console.log(key + ":" + obj[key]);
        } 
    }
//============================================================================================================
//10. Delete Element
//Define function deleteElement(someArr)
//Print length
//Delete the third element in the array.
//Print length
//The lengths should be the same.
//
    function deleteElement(arr)
    {
        console.log(arr.length)
        delete arr[2];
        console.log(arr.length)
    }
//============================================================================================================
//11. Splice Element
//Define function spliceElement(someArr)
//Print length
//Splice the third element in the array.
//Print length
//The lengths should be one less than the original length.
//
    function spliceElement(arr)
    {
        console.log(arr.length)
        arr.splice(2,1);
        console.log(arr.length)
    }
//============================================================================================================
//12. Defining an object using a constructor
//Define a function Person(name, age)
//The following line should set a Person object to the variable john:
//	var john = new Person("John", 30);
//
    function Person(name, age)
    {
        this.name = name;
        this.age = age;
    }
//============================================================================================================
//13. Defining an object using an object literal
//Define function getPerson(name, age)
//The following line should set a Person object to the variable john:
//	var john = getPerson("John", 30);
// 
    function getPerson(name,age)
    {
       var Person =
      {
        name: name,
        age: age,

        getName: function()
        {
            return this.name;
        },
        getAge: function()
        {
            return this.age;
        }
      }
      return Person;
    }
    
