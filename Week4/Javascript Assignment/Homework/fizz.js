//================================ JS FILE THAT WILL COMPUTE FIZZ BUZZ ================================

function validateform()
{
    //event.preventDefault();
    var min = document.getElementById("min").value;
    var max = document.getElementById("max").value;
    if (min > 0)
    {
        if (max < 201)
        {
            fizzbuzz(min,max);
            return true;
        }
        alert("Enter max less than or equal to 200");
        return false;
    }
    alert("Enter min greater than 0");
    return false;

}

function fizzbuzz(min, max)
{
    for ( i = min; i <= max; i++)
    {
        var node = document.createElement("LI");
        var textnode;
        if ( i % 5 == 0 && i % 3 == 0)
        {
            textnode = document.createTextNode("FizzBuzz");
            console.log("FizzBuzz");
        }
        else if ( i % 3 == 0)
        {
            textnode = document.createTextNode("Fizz");
            console.log("Fizz");
        }
        else if ( i % 5 == 0 )
        {
            textnode = document.createTextNode("Buzz");
            console.log("Buzz");
        }
        else
        {
            textnode = document.createTextNode(i);
            console.log(i);
        }
        node.appendChild(textnode);
        document.getElementById("num").appendChild(node);
    }
}
