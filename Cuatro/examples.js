//4 Ways to write to a client =========================================================
console.log ("Msg"); // Writes to the browser console
window.alert ("Msg"); // Pops an intrusive alert messae taht requires clicking
document.write ("Msg"); //Writes a brand new page dynamically (Think print writer)
document.getElementById ("Id").innerHTML = "NEW INNER"; //replaces innerhtml

//DATA TYPES =================================================================
//Number (including floats)
    //NaN
//String

//Boolean

//Object
     var x =  
     {
          name: "x",
          age: 5
     };
//
// ACCCESS VARIABLES =============================================================
 x.name
 x["name"] //gets value of whatever is inside usually going through it in a loop
//
//
 x.nestedObject = {
                      type: "Object,
                      height: 5
                  };
//LOOPS =============================================================================
for (i in x)
{
    if(x[i] instanceOf Object)
    {
        for ( j in x )
        {
            console.log(x[j];
        }
    }
    else
    {
        console.log(x[i]);
    }
}
//
//ARRAYS ============================================================================
var x = [];
var x = [ 1, null, undefined, "Stuff", {}];
for ( i in x )
{
    console.log(x[i]);
}

var arr = ["adam", "charles", "bobbert"];
arr.sort(); //sort
arr.sort().reverse() // changes the arr so its reversed when you call arr
// sorts by turning values into strings
//
//arr2.sort(function(A,b){return a-b});
//
//FUNCTION ==============================================================================
function truthyFalsey(input)
{
    if (input)
    {
        console.log("TRUE");
        return true;
    }
    else
    {
        console.log("FALSE");
        return false;
    }
}

var x = function (a,b,c)
{
    return a+b+c;
}
x(1,2,3)
//PARAMETERS CAN BE GRATER THAN WHAT WE PASS IT IN



//SCOPES ============================================================================
// LOCALLY IF YOU USE VAR WITHIN A FUNCTION
//
// CONSTRUCTOR =====================================================================
function Employee (name. age)
{
    this.name = name;
    this.age = age;
}
var x = new Employee("bobbert", 76);

x.name = "Tommy";


Employee.prototype.name = "newName";

//ALL EMPLOYEES NOW HAVE LAST NAME TOMPSON EVEN ONES WE ALREADY HAD
//PROTOTIPICAL INHERITANCE
Employee.prototype.lastname = "Tompson";


function person(fnam, lnam)
{
    var fname = fnam;
    var lname= lnam;
    return 
    {
        getFirst: function ()
        {
            console.log(fname);
        }
        ,
        getLast: function()
        {
            console.log(lname);
        }
    }
}
