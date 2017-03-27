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
});