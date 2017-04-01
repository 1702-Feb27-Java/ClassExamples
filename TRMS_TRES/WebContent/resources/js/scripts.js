 $( document ).ready(function()
    { 
      new Date($.now());

      var dateObj = new Date();
      
      console.log(dateObj.get)
      var time = dateObj.getHours();
      if (dateObj.getMinutes() < 10)
    	  time += ":0" + dateObj.getMinutes();
      else
      {
    	  time += ":" + dateObj.getMinutes();
      }
      var date = dateObj.getMonth()+1 + "/" + dateObj.getDate() + "/" + dateObj.getFullYear();
      //var AMorPM = (dateObj.getHours() >= 12) ? "PM" : "AM";

      //$('#timepicker1').val(time+AMorPM);
      $('#timepicker1').val(time);
      $('#frmdt').val(date);
    });
//<!----------------- JAVASCRIPT -------------------------->

	//===================== FUNCTION TO CHECK IF INPUT IS NUMBER =========================================
	$(function ()
	{
		$("#cst").on('input', function (){
			if (!($.isNumeric($("#cst").val())) && $("#cst").val() != "")
			{
				console.log ($('#cst').val());
				alert("not a number fix this to not sumbit");
			}
		})
	});
	//===================== FUNCTION SETS THE TIME ========================================================= 
/*    $(function () 
    {
        $('#timepicker1').timepicker();
    });
    $(function ()
    {
    	$('#setTimeButton').on('click', function (){
    		$('#timepicker1').timepicker('setTime', new Date());
    	});
    });*/
	//===================== FUNCTION SETS DATE OF COURSE START AND END MIN(1 WEEK) ========================================================= 
    $( function() {
        var dateFormat = "mm/dd/yy",
          from = $( "#stdate" )
            .datepicker({
              changeMonth: true,
              minDate: "+1w", 
              numberOfMonths: 1
            })
            .on( "change", function() {
              to.datepicker( "option", "minDate", getDate( this ) );
            }),
          to = $( "#edate" ).datepicker({
            changeMonth: true,
            minDate: "+1w", 
            numberOfMonths: 1
          })
          .on( "change", function() {
            from.datepicker( "option", "maxDate", getDate( this ) );
          });
     
        function getDate( element ) {
          var date;
          try {
            date = $.datepicker.parseDate( dateFormat, element.value );
          } catch( error ) {
            date = null;
          }
          return date;
        }
      } );
    //===================== WHEN THEY SUBMIT THE REIBURSEMENT ==============================================================================
    $(function (){
    	$("#reimsub").submit( function (){
    								})
    });
    //=============================
//</script>