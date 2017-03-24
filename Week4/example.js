$(function(){
    x = 5;
    //$('body').fadeOut(1000).fadeIn(2000);

    $('#add').click(function(){

        if ($('#newTable').length==0){
            var tableFormat = "<table border=2 id='newTable'></table>";
            $('#newStuff').append(tableFormat);
        }

        var input = $('#input').val();  //.html() .text()
        $('#newTable').append("<tr><td>Input</td><td>" + input + "</td></tr>");
    })
})