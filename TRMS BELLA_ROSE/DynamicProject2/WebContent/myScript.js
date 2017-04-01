/**
 * 
 */

function Reimbursement(pCourseCost, pEndDate, pGradeTypeId, pLocation,
		pPassingGrade, pStartDate, pCourseId) {

	this.courseCost = pCourseCost;
	this.endDate = pEndDate;
	this.gradeTypeId = pGradeTypeId;
	this.location = pLocation;
	this.passingGrade = pPassingGrade;
	this.startDate = pStartDate;
	this.courseId = pCourseId;
};

function submitReq() {

	$.ajax({
		cache : false,
		url : "InsertReimServlet",
		type : "POST",
		data : {
			"reim" : JSON.stringify(new Reimbursement($("#cost").val(), $(
					"#endDate").val(), $("#gradeType").val(), $("#location")
					.val(), $("#passingGrade").val(), $("#startDate").val(), $(
					"#courseType").val()))
		},
		success : function(result, status, xhr) {
			console.log(xhr.responseText);
			responsiveVoice.speak(xhr.responseText);
		},
		error : function(xhr, status) {
			responsiveVoice.speak("Ajax error please submit again");
		},
		complete : function(xhr, status) {

		}
	});

}

function getReqs() {

	$.ajax({
		cache : false,
		url : "InsertReimServlet",
		type : "POST",
		data : {
			"myReqs" : 'true'
		},
		success : function(result, status, xhr) {
			// console.log(xhr.responseText);

			var parsed = JSON.parse(xhr.responseText);

			var arr = [];

			for ( var x in parsed) {
				arr.push(parsed[x]);
				// console.log(parsed[x]);
			}

			// $('#tableBody').innerHTML = "";
			$('#tableBody tr').remove();

			for (var x = 0; x < arr.length; x++) {
				// create tr tag and store in variable
				var trTag = document.createElement("TR");

				var y;

				switch (arr[x].courseId) {
				case 1:
					y = "University Course";
					break;
				case 2:
					y = "Seminar";
					break;
				case 3:
					y = "Cert Prep";
					break;
				case 4:
					y = "Cert";
					break;
				case 5:
					y = "Tech Training";
					break;
				case 6:
					y = "Other";
					break;
				default:
					y = "Error";

				}

				var submission;
				if (arr[x].gradeTypeId == 4) {
					submission = "Presentation Required";
				} else {
					submission = "Grade Submission Required";
				}

				var app;

				switch (arr[x].approvalId) {
				case 1:
					app = "New";
					break;
				case 2:
					app = "Approved by Supervisor";
					break;
				case 3:
					app = "Supervisor Requests More Info";
					break;
				case 4:
					app = "Approved by Department Head";
					break;
				case 5:
					app = "Department Head Requests More Info";
					break;
				case 6:
					app = "Approved by BenCo";
					break;
				case 7:
					app = "BenCo Requests More Info";
					break;
				case 8:
					app = submission;
					break;
				case 9:
					app = "Submission Under Review";
					break;
				case 10:
					app = "Completed";
					break;
				case 11:
					app = "Denied";
					break;
				default:
					app = "Error";

				}

				// set trTag.inner html = "<td>" + arr.type + "</td>" + ...
				trTag.innerHTML = "<td>" + y + "</td>" + "<td>"
						+ arr[x].location + "</td>" + "<td>" + app + "</td>"
						+ "<td>" + arr[x].reim_amnt + "</td>";

				// add trTag to $('#tableBody')
				$('#tableBody').append(trTag);
			}

		},
		error : function(xhr, status) {
			responsiveVoice.speak("Ajax error please try again");
		},
		complete : function(xhr, status) {

		}
	});

}

var myReim;

function fillInfoModal() {
	console.log("in modal fill");

	$
			.ajax({
				cache : false,
				url : "InsertReimServlet",
				type : "POST",
				data : {
					"modalFill" : this.id
				},
				success : function(result, status, xhr) {
					console.log(xhr.responseText);

					var parsed = JSON.parse(xhr.responseText);
					
					myReim = parsed.reimId;

					console.log(parsed);

					var y;

					switch (parsed.courseId) {
					case 1:
						y = "University Course";
						break;
					case 2:
						y = "Seminar";
						break;
					case 3:
						y = "Cert Prep";
						break;
					case 4:
						y = "Cert";
						break;
					case 5:
						y = "Tech Training";
						break;
					case 6:
						y = "Other";
						break;
					default:
						y = "Error";

					}

					var format;

					switch (parsed.gradeTypeId) {
					case 1:
						format = "Letter Grade";
						break;
					case 2:
						format = "Number Grade";
						break;
					case 3:
						format = "Pass/Fail";
						break;
					case 4:
						format = "Presentation";
						break;
					default:
						format = "Error";

					}

					var submission;
					if (parsed.gradeTypeId == 4) {
						submission = "Presentation Required";
					} else {
						submission = "Grade Submission Required";
					}

					var app;

					switch (parsed.approvalId) {
					case 1:
						app = "New";
						break;
					case 2:
						app = "Approved by Supervisor";
						break;
					case 3:
						app = "Supervisor Requests More Info";
						break;
					case 4:
						app = "Approved by Department Head";
						break;
					case 5:
						app = "Department Head Requests More Info";
						break;
					case 6:
						app = "Approved by BenCo";
						break;
					case 7:
						app = "BenCo Requests More Info";
						break;
					case 8:
						app = submission;
						break;
					case 9:
						app = "Submission Under Review";
						break;
					case 10:
						app = "Completed";
						break;
					case 11:
						app = "Denied";
						break;
					default:
						app = "Error";

					}

					// console.log("Y " + y);
					// console.log("app " + app);

					// set trTag.inner html = "<td>" + arr.type + "</td>" + ...
					document.getElementById("modal-body").innerHTML = "<h2> <label for='CourseType'>Course Type</label> <input type='text' name='CourseType' value='"
							+ y
							+ "' readonly> <br> <br> <label for='GradeFormat'>Grading Format</label> <input type='text' name='Grading Format' value='"
							+ format
							+ "' readonly> <br> <br> <label for='PassingGrade'>Passing Grade</label> <input type='text' name='Passing Grade' value= '"
							+ parsed.passingGrade
							+ "' readonly> <br> <br> <label for='Location'>Location</label> <input type='text' name='Location' value= '"
							+ parsed.location
							+ "' readonly> <br> <br> <label for='StartDate'>Start Date</label> <input type='text' name='Start Date' value= '"
							+ parsed.startDate
							+ "' readonly> <br> <br> <label for='EndDate'>End Date</label> <input type='text' name='End Date' value= '"
							+ parsed.endDate
							+ "' readonly> <br> <br> <label for='Cost'>Cost</label> <input type='text' name='Cost' value= '"
							+ parsed.courseCost + "' readonly> <br> <br> </h2>";

					// add trTag to $('#tableBody')
					// /////////////$('#tableBody2').append(trTag);

				},
				error : function(xhr, status) {
					responsiveVoice.speak("Ajax error please try again");
				},
				complete : function(xhr, status) {

				}
			});

}

function getOtherReqs() {
	console.log("in other reqs");

	$
			.ajax({
				cache : false,
				url : "InsertReimServlet",
				type : "POST",
				data : {
					"otherReqs" : 'true'
				},
				success : function(result, status, xhr) {
					console.log(xhr.responseText);

					var parsed = JSON.parse(xhr.responseText);

					var arr = [];

					for ( var x in parsed) {
						arr.push(parsed[x]);
						console.log(parsed[x]);
					}

					// <th>Type</th>
					// <th>Location</th>
					// <th>Approval Stage</th>
					// <th>Estimated Amount</th>

					// <tr>
					// <td>John Doe</td>
					// <td>johndoe@example.com</td>
					// </tr>

					// $('#tableBody').innerHTML = "";
					$('#tableBody2 tr').remove();

					for (var x = 0; x < arr.length; x++) {
						// create tr tag and store in variable
						var trTag = document.createElement("TR");

						var y;

						switch (arr[x].courseId) {
						case 1:
							y = "University Course";
							break;
						case 2:
							y = "Seminar";
							break;
						case 3:
							y = "Cert Prep";
							break;
						case 4:
							y = "Cert";
							break;
						case 5:
							y = "Tech Training";
							break;
						case 6:
							y = "Other";
							break;
						default:
							y = "Error";

						}

						var submission;
						if (arr[x].gradeTypeId == 4) {
							submission = "Presentation Required";
						} else {
							submission = "Grade Submission Required";
						}

						var app;

						switch (arr[x].approvalId) {
						case 1:
							app = "New";
							break;
						case 2:
							app = "Approved by Supervisor";
							break;
						case 3:
							app = "Supervisor Requests More Info";
							break;
						case 4:
							app = "Approved by Department Head";
							break;
						case 5:
							app = "Department Head Requests More Info";
							break;
						case 6:
							app = "Approved by BenCo";
							break;
						case 7:
							app = "BenCo Requests More Info";
							break;
						case 8:
							app = submission;
							break;
						case 9:
							app = "Submission Under Review";
							break;
						case 10:
							app = "Completed";
							break;
						case 11:
							app = "Denied";
							break;
						default:
							app = "Error";

						}

						trTag.innerHTML = " <td> <span class='filelink' id="
								+ arr[x].reimId
								+ "> <em class='fa fa-cogs' style='font-size:24px; text-align: center;' data-toggle='modal' data-target='#myModal'></em> </span> </td> <td>"
								+ y + "</td>" + "<td>" + arr[x].location
								+ "</td>" + "<td>" + app + "</td>" + "<td>"
								+ arr[x].reim_amnt + "</td>";

						$('#tableBody2').append(trTag);
					}

					var classname = document.getElementsByClassName("filelink");

					for (i = 0; i < classname.length; i++) {
						classname[i].addEventListener("click", fillInfoModal,
								true);
					}

				},
				error : function(xhr, status) {
					responsiveVoice.speak("Ajax error please try again");
				},
				complete : function(xhr, status) {

				}
			});

}

function setEndDate() {
	if (document.getElementById('endDate') != null) {
		document.getElementById('endDate').setAttribute('min',
				$("#startDate").val());
	}
}

function RequestMore() {
	
	swal({
		  title: "You Want More Information.",
		  text: "What information are you requesting?",
		  type: "input",
		  showCancelButton: true,
		  confirmButtonColor: "#32CD32",
		  closeOnConfirm: false,
		  inputPlaceholder: "", 
		  animation: "slide-from-top"
		}, function (inputValue) {
		  if (inputValue === false) return false;
		  if (inputValue === "") {
		    swal.showInputError("You need to write something!");
		    return false
		  }
		  swal("Thank you!", "", "success");
		  $.ajax({
				cache : false,
				url : "InsertReimServlet",
				type : "POST",
				data : {"request": inputValue, "id" : myReim},
				success : function(result, status, xhr) {
					
				},
				error : function(xhr, status) {
					responsiveVoice.speak("Ajax error please submit again");
				},
				complete : function(xhr, status) {

				}
			});
		});

	
}

function Reject() {
	
	swal({
		  title: "Rejection!",
		  text: "Why are you rejecting this?",
		  type: "input",
		  showCancelButton: true,
		  confirmButtonColor: "#32CD32",
		  closeOnConfirm: false,
		  inputPlaceholder: "", 
		  animation: "slide-from-top"
		}, function (inputValue) {
		  if (inputValue === false) return false;
		  if (inputValue === "") {
		    swal.showInputError("You need to write something!");
		    return false
		  }
		  swal("Thank you!", "", "success");
		  $.ajax({
				cache : false,
				url : "InsertReimServlet",
				type : "POST",
				data : {"reject": inputValue, "id" : myReim},
				success : function(result, status, xhr) {
					
				},
				error : function(xhr, status) {
					responsiveVoice.speak("Ajax error please submit again");
				},
				complete : function(xhr, status) {

				}
			});
		});

		
	
}

function getNow() {
	var maxDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	var date = new Date();
	var day = date.getDate();
	var month;
	var year;

	month = date.getMonth() + 1;
	year = date.getFullYear();

	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;

	return year + "-" + month + "-" + day;
}

function getAWeekFromNow() {
	var maxDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
	var date = new Date();
	var day = date.getDate();
	var month;
	var year;

	if ((day + 7) > maxDays[date.getMonth()]) {
		month = date.getMonth() + 2;
		day = day + 7 - maxDays[date.getMonth()];
	} else {
		month = date.getMonth() + 1;
	}

	if (month > 12) {
		year = date.getFullYear() + 1;
		month -= 12;
	} else {
		year = date.getFullYear();
	}

	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;

	return year + "-" + month + "-" + day;
}

$('form').on('submit', function(event) {
	console.log("Submit");
	event.preventDefault();
	submitReq();
});

$('#SubmitButt').on('click', function() {
	$('input[type=submit]').click();
});

$(document).ready(
		function() {
			
			
			//swal('Congratulations!', 'Your message has been succesfully sent', 'success');
			
			responsiveVoice.speak(document.getElementById("WelcomeMessage").innerHTML);

			document.getElementById("MyReqButt").addEventListener("click",
					getReqs, true);
			document.getElementById("MyReqButt2").addEventListener("click",
					getOtherReqs, true);

			document.getElementById("Approve").addEventListener("click",
					getOtherReqs, true);
			document.getElementById("RequestMore").addEventListener("click",
					RequestMore, true);
			document.getElementById("Reject").addEventListener("click", Reject,
					true);

			$('#field1').slideUp(1);
			$('#field2').slideUp(1);

			if (document.getElementById('startDate') != null) {

				document.getElementById('startDate').setAttribute('min',
						getAWeekFromNow());
				document.getElementById('endDate').setAttribute('min',
						getAWeekFromNow());
				document.getElementById('startDate').addEventListener("change",
						setEndDate, false);

			}
		});