var min = document.getElementsByTagName("Min");
var max = document.getElementsByTagName("Max");

for (var i= min; i <= max; i++)
{
    if (i % 15 == 0)
    {
       //document.write("FizzBuzz\n");
       var p = document.createElement("p");
       var t = document.createTextNode("FizzBuzz\n");
       p.appendChild(t);
       document.getElementsByTagName('body').appendChild(p);
    }
    else if (i % 3 == 0)
    {
    	//document.write("Fizz\n");
    	var p = document.createElement("p");
       	var t = document.createTextNode("Fizz\n");
       	p.appendChild(t);
       	document.getElementsByTagName('body').appendChild(p);
    }
    else if (i % 5 == 0)
    {
    	//document.write("Buzz\n");
    	var p = document.createElement("p");
       	var t = document.createTextNode("Buzz\n");
       	p.appendChild(t);
       	document.getElementsByTagName('body').appendChild(p);
    }
    else
    {
    	//document.write(i + "\n");
    	var p = document.createElement("p");
       	var t = document.createTextNode(i + "\n");
       	p.appendChild(t);
       	document.getElementsByTagName('body').appendChild(p);
    }
}