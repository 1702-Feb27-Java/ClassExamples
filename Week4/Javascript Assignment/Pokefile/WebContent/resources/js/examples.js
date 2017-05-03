$(function(){
	
	//$("body").fadeOut(4000).fadeIn(2000);
	
	$("#addSomething").click(function()
		{
			if($("#newTable").length == 0)
			{
				var tableFormat = "<table border=2 id='newTable'></table>"
				$("#newStuff").append(tableFormat);
			}
			var input = $("#inputSomething").val(); //.html() val plus tags .text()
			$("#newTable").append("<tr><td> Input </td><td>" + input + "</td></tr>");
		}	
	
	)
})


/*
		TRAVERSAL
		parent() //Parent of current tag
			$("#addSomething").parent().fadeOut();
		parents()	//All parents
			//list
		parentsUtil() //All parents until reahing a tag
			//parent up til a p tag
			
		children()
			//Return all direct children of node //can set full selectors to return
		find()
			//Return all children of a specific type	//note: *
		
		siblings()
			//All siblings can filter
		next()
			//Next Sibling
		nextAll()
			// list of next siblings
		nextUtil()
			// every sibling until a tag
		prev()
			//
		prevAll()
			//
		prevUntil()
			//
		
		first()
			//
		last()
			//
		eq()
			//return speciffig elemenet with an index number
		not()
			//opposite


*/