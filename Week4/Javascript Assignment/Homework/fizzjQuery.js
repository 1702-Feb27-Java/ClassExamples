//================================ JS FILE THAT WILL COMPUTE FIZZ BUZZ ================================

function validateform()
{
    //event.preventDefault();
    var min = $("#min").val(); 
    var max = $("#max").val(); 
    if (min > 0)
    {
        if (max < 201)
        {
            fizzbuzz(min,max);
            return true;
        }
        alert("Enter max less than or equal to 200");
        console.log(max);
        return false;
    }
    alert("Enter min greater than 0");
    return false;

}

function fizzbuzz(min, max)
{
    for ( i = min; i <= max; i++)
    {
        var node = $("<li></li>") 
        var textnode;
        if ( i % 5 == 0 && i % 3 == 0)
        {
            node.text("FizzBuzz")
            console.log("FizzBuzz");
        }
        else if ( i % 3 == 0)
        {
            node.text("Fizz")
            console.log("Fizz");
        }
        else if ( i % 5 == 0 )
        {
            node.text("Buzz")
            console.log("Buzz");
        }
        else
        {
            node.text(i)
            console.log(i);
        }
        $("#num").append(node);
    }
}
