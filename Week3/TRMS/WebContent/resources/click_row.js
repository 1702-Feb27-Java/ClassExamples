/**
 * 
 */
$(document).ready(function(){
	$('.table').on('click', '.clickable-row', function(event) {
	  $(this).addClass('active').siblings().removeClass('active');
	});
});