$(function(){
		$("#addSomething").click(function(e){
			if($("#newTable").length == 0){
				var tableFormat = '<table border=1 id="newTable"></table>';
			}
			//var tableFormat = '<table border=1 id="newTable"></table>';
			$("#newStuff").append(tableFormat)
			var input = $("#input").val(); //val(), //html(), //text()
			$("#newTable").append("<tr><td>Input</td><td>" + input + "</td></tr>")
			
		});
	
})