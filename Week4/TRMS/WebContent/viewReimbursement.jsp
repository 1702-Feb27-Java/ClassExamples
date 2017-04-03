<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.model.Reimbursement"%>
<%@ page import="database.model.User"%>
<%@ page import="database.service.Service"%>
<%@ page import="java.util.List"%>
<%@ page import="database.model.Feedback"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%@ include file="fragments/cdn.jsp"%>
</head>


<body>
<%@ include file="fragments/nav.jsp" %>
<div class ="container">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#reimburse" data-toggle="tab">View
				Reimbursement</a></li>
		<li class=""><a href="#reimburse-upload" data-toggle="tab">View
				Attachments</a></li>
		<li class=""><a href="#reimburse-feedbacks" data-toggle="tab">View
				Messages</a></li>
	</ul>

	<!-- View Reimbursement Details -->
	<div class="tab-content">
		<div class="tab-pane active" id="reimburse">
			<form action="EditReimbursement" method="POST">
				<div class="form-group">
					<label>Description</label><input type="text" class="form-control"
						id="reimburse-desc" value="${reimbursement.getDescription()}"
						readonly />
				</div>
				<div class="form-group">
					<label>Location</label><input type="text" class="form-control"
						id="reimburse-loc" value="${reimbursement.getLocation()}" readonly />
				</div>
				<div class="form-group">
					<label>Cost Of Event</label><input type="number"
						class="form-control" id="reimburse-cost"
						value="${reimbursement.getCostOfEvent()}" readonly />
				</div>
				<div class = "row">
				<div class="form-group col-sm-6">
					<label>Grade Format</label><select disabled="true"
						class="form-control" id="gradeFormat"><option><c:out
								value="${reimbursement.getGradeFormat().getFormatName()}" /></option></select>
				</div>
				<div class="form-group col-sm-6">
						<label>Event Type</label><select disabled="true"
							class="form-control"><option><c:out
									value="${reimbursement.getEventType().getTypeName()}" /></option></select>
				</div>
				</div>
				<%--Show custom grading option, only when Other is given --%>
				<c:if test="${reimbursement.getGradeFormatId() == 4}">
					<div class="form-group" id="customFormatDiv">
						<label>Custom Grading Format</label><input type="text"
							class="form-control" name="custom-grading-format"
							id="reimburse-custom-format"
							value="${reimbursement.getCustomGradingFormat()  != null ? reimbursement.getCustomGradingFormat()  : ''}"
							readonly />
					</div>
				</c:if>
				<div class ="row">
					<div class="form-group col-sm-6">
						<label>Start Date</label><input type="date" class="form-control"
							id="reimburse-startDate" value="${reimbursement.getStartDate()}"
							readonly />
					</div>
					<div class="form-group col-sm-6">
						<label>End Date</label><input type="date" class="form-control"
							id="reimburse-endDate" value="${reimbursement.getEndDate()}"
							readonly />
					</div>
				</div>
				
				<div id="grades" class ="row">
					<%--Show Grades only when needed --%>
					<c:if test="${reimbursement.getStatus().getStatusId() >= 4}">
						<c:if test="${canEditGrade}">
							<div class="form-group col-sm-6">
								<label>Grade</label><input type="text" class="form-control"
									id="reimburse-grade" name="reimburse-grade"
									value="${reimbursement.getGrade()}" />
							</div>
							<div class="form-group col-sm-6">
								<label>Passing Grade</label><input type="text"
									class="form-control" id="reimburse-passingGrade"
									name="reimburse-passingGrade"
									value="${reimbursement.getPassingGrade()}" />
							</div>
						</c:if>
						<c:if test="${!canEditGrade}">
							<div class="form-group col-sm-6">
								<label>Grade</label><input type="text" class="form-control"
									id="reimburse-grade" value="${reimbursement.getGrade()}"
									readonly />
							</div>
							<div class="form-group col-sm-6">
								<label>Passing Grade</label><input type="text"
									class="form-control" id="reimburse-passingGrade"
									value="${reimbursement.getPassingGrade()}" readonly />
							</div>
						</c:if>
					</c:if>
					
				</div>

				<input type="hidden" name="userId"
					value="${loggedInUser.getUserId()}" /> <input type="hidden"
					name="reimburseId" value="${reimbursement.getReimbursementId()}" />

				<%-- allows reimbursement field to be editable for BenCo --%>
				<c:if test="${canEditReimbursement}">
					<div class="form-group">
						<label>Reimbursement</label><input type="number"
							class="form-control" name="reimburse-reimburse"
							id="reimburse-reimburse"
							value="${reimbursement.getReimbursement()}" />
					</div>
				</c:if>
				<c:if test="${!canEditReimbursement}">
					<div class="form-group">
						<label>Reimbursement</label><input type="number"
							class="form-control" id="reimburse-reimburse"
							value="${reimbursement.getReimbursement()}" readonly />
					</div>
				</c:if>
				<div class="statusChangeButton form-group">

					<c:if test="${canCancel}">
						<input type="submit" name="statusChange" value="Cancel" />
					</c:if>
					<c:if test="${canApprove}">
						<input type="submit" name="statusChange" value="Approve" />
					</c:if>
					<c:if test="${canReject}">
						<button id="reject-button" type="button" onclick="confirmRejectOption()">Reject</button>
						<!-- <input type="submit" name="statusChange" value = "Reject"/>  -->
					</c:if>
					<c:if test="${canEditGrade}">
						<input type="submit" name="statusChange" value="Edit" />
					</c:if>
				</div>

				<!-- Rejection message to show up when person presses Reject -->
				<c:if test="${canReject}">
					<div class="confirmRejection" id="hiddenConfirmRejection">
						<label>Reason For Rejection</label>

						<textarea rows="3" class="form-control" name="feedback-feedback"></textarea>
						<input type="hidden" name="feedback-user"
							value="${reimbursement.getUserId()}" /> <input type="hidden"
							name="reimburse-id" value="${reimbursement.getReimbursementId()}" />
						<input type="submit" name="statusChange" value="Confirm Reject" />
					</div>
				</c:if>
				<c:if
					test="${reimbursement.getRejectionFeedbackId() != null && reimbursement.getRejectionFeedbackId() != 0}">
					<div class="confirmRejection">
						<label>Reason For Rejection</label>
						<textarea rows="3" class="form-control" name="feedback-feedback"
							readonly><c:out
								value="${reimbursement.getRejectionFeedback().getFeedback()}" /></textarea>
					</div>
				</c:if>

			</form>
			<label>Status </label> <span id="reimburse-status"> <c:out
					value="${reimbursement.getStatus().getStatusName()}" /></span>


		</div>






		<div id="reimburse-upload" class="tab-pane">
			<c:if test="${canSendAttachments}">
				<form action="Attachment" enctype="multipart/form-data"
					method="POST">
					<div class="form-group">
						<label>File Upload</label><input type="file" name="file"
							class="form-control">
					</div>


					<div class="form-group">
						<label>File Description </label><input type="text"
							class="form-control" name="attachmentType">
					</div>
					<input type="hidden" name="reimburse-id"
						value="${reimbursement.getReimbursementId()}" /> <input
						type="submit" value="Submit File" />
				</form>
			</c:if>


			<!-- View Attachments -->
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Attachment Description</th>
						<th>Date uploaded</th>
						<th></th>
					</tr>
				<thead>
				<tbody>
					<c:forEach items="${attachments}" var="attachment">
						<tr>
							<td><span class="reimburse-attachment-desc"><c:out
										value="${attachment.getAttachmentType()}" /></span></td>
							<td><span class="reimburse-attachment-date"><c:out
										value="${attachment.getTimeUploaded()}" /></span></td>
							<td><form action="Attachment" method="GET">
									<input type="hidden" name="id"
										value="${attachment.getAttachmentId()}"><input
										type="submit" value="download">
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<!-- Create Messages -->
		<div id="reimburse-feedbacks" class="tab-pane">


			<form id="form-feedback" action="NewFeedback" method="POST">
				<div class="form-group">

					<Label>User To Send</Label> <select name="feedback-user"
						class="form-control">
						<c:forEach items="${usersToMessage}" var="user">
							<option value="${user.getUserId()}"><c:out
									value="${user.getUsername()}" /></option>
						</c:forEach>


					</select>
				</div>

				<div class="form-group">
					<label>Message</label> <input type="text" class="form-control"
						name="feedback-feedback" /> <input type="hidden"
						name="reimburse-id" value="${reimbursement.getReimbursementId()}" />
				</div>
				<input type="submit" value="Submit" />
			</form>
			<hr>

			<!-- View messages -->
			<div class="feedbacks">
				<c:forEach items="${feedbacks}" var="feedback">
					<div class="reimburse-feedback">
						<div class="container" style="text-align: center;">
							<div class="row" style="text-align: center;" >
								<span class="reimburse-feedback-user col-sm-4">From: <c:out
										value="${feedback.getFromUser().getUsername()}" />
								</span> <span class="reimburse-feedback-user col-sm-4">To: <c:out
										value="${feedback.getToUser().getUsername()}" />
								</span> <span class="reimburse-feedback-date col-sm-4">Date of Message:<c:out
										value="${feedback.getDateOfMessage()} " />

								</span>
							</div>
							<br/>
							<div class="row" style="text-align: left;">
								<span class="reimburse-feedback-feedback col-sm-12">Message: <c:out
										value="${feedback.getFeedback()}" />
								</span>
							</div>
							<br/>
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="js/viewReimbursement.js"></script>
</body>
</html>