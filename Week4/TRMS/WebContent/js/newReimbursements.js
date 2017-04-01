"use strict"

function updateReimbursementField(){
	var cost = document.getElementById('reimburse-cost').value;
	//console.log("Cost is " + cost);
	var dropBox = document.getElementById("reimburse-event-type");
	var coverage = dropBox.options[dropBox.selectedIndex].dataset.coverage;
	//console.log("Coverage is " + coverage);
	
	var maxReimbursement = document.getElementById("reimburse-reimbursement").dataset.maxAmount;
	
	var uncappedReimbursement = cost * coverage;
	
	var reimbursement = 0;
	
	if (uncappedReimbursement < maxReimbursement){
		reimbursement = uncappedReimbursement;
	} else {
		reimbursement = maxReimbursement;
	}
	document.getElementById("reimburse-reimbursement").value = reimbursement;
}


function showCustomGradingFormat(){
	var v = $("#reimburse-grade-format").val();
	console.log(v);
	var customFormatInput = $("#customGradeFormatDiv");
	if(v == 4){
		customFormatInput.show(250);
	} else {
		customFormatInput.hide(250);
	}
}


function docStart(){
	$("#customGradeFormatDiv").hide();
	
}

$(document).ready(docStart);