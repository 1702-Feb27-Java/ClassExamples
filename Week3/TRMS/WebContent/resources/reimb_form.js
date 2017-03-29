/**
 * 
 */
$(document).ready(function(){
	$("#sel1").change(function() {
	    var val = $(this).val();
	    if (val == 0) {
	       $("#other-event-input").show();
	    }
	    else {
		   $("#other-event-input").hide();
		}
	});
	
	$("#sel2").change(function() {
	    var val = $(this).val();
	    if (val == 0) {
	       $("#other-grade-input").show();
	    }
	    else {
		   $("#other-grade-input").hide();
		}
	});	
	
    $('#dateRangePicker')
    .datepicker({
        format: 'mm/dd/yyyy',
        startDate: '01/01/2010',
        endDate: '12/30/2020'
    });

});