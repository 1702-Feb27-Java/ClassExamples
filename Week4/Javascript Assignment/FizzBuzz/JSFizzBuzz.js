$(document).ready(function () {

    $("#fizzbuzz").submit(function (event) {
        var start = parseInt($("#start").val());
        var finish = parseInt($("#finish").val());

        // check that the input values are numbers
        if (!isNaN(start) && !isNaN(finish)) {
            // loop through numbers
            var i;
            for (i = start; i <= finish; i++) {

                if (i % 3 === 0 && i % 5 === 0) {
                    $("#output").append("<span class='fizzbuzz'>FizzBuzz</span>");
                } else if (i % 3 === 0) {
                    $("#output").append("<span class='fizz'>Fizz</span>");
                } else if (i % 5 === 0) {
                    $("#output").append("<span class='buzz'>Buzz</span>");
                } else {
                    $("#output").append("<span>" + i + "</span>");
                }
            }
            $("#output span").fadeIn(400);
        } else {
            // if the inputs are not valid numbers
            $("#error").append("<p class='error'>Please enter a valid number in both boxes</p>");
        }
        // prevent form submission
        return false;
    });

    // empty the output & error divs
    $('input').on('focus', function () {
        $("#output span").fadeOut(400, function () {
            $("#output").empty();
        });
        $("#error").empty();
    });

}); // end ready