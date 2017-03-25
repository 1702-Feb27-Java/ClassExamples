package com.revature.pojo;

import java.util.Date;

public class Reimbursement {
	String event, time, description, grading, approvalStep, location, typeOfEvent;
	int locationId, gradingId, typeOfEventId, approvalStepId, cost, reimbId;
	Date eventDate, formDate;
	public Reimbursement(String event, String time, String description, int cost, int locationId, int gradingId,
			int typeOfEventId, int approvalStepId, Date eventDate, Date formDate, int reimbId) {
		super();
		this.event = event;
		this.time = time;
		this.description = description;
		this.cost = cost;
		this.locationId = locationId;
		this.gradingId = gradingId;
		this.typeOfEventId = typeOfEventId;
		this.approvalStepId = approvalStepId;
		this.eventDate = eventDate;
		this.formDate = formDate;
		this.reimbId = reimbId;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGrading() {
		return grading;
	}
	public void setGrading(String grading) {
		this.grading = grading;
	}
	public String getApprovalStep() {
		return approvalStep;
	}
	public void setApprovalStep(String approvalStep) {
		this.approvalStep = approvalStep;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTypeOfEvent() {
		return typeOfEvent;
	}
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getGradingId() {
		return gradingId;
	}
	public void setGradingId(int gradingId) {
		this.gradingId = gradingId;
	}
	public int getTypeOfEventId() {
		return typeOfEventId;
	}
	public void setTypeOfEventId(int typeOfEventId) {
		this.typeOfEventId = typeOfEventId;
	}
	public int getApprovalStepId() {
		return approvalStepId;
	}
	public void setApprovalStepId(int approvalStepId) {
		this.approvalStepId = approvalStepId;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getFormDate() {
		return formDate;
	}
	public void setFormDate(Date formDate) {
		this.formDate = formDate;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public int getReimbId() {
		return reimbId;
	}
	@Override
	public String toString() {
		return "Reimbursement [event=" + event + ", time=" + time + ", description=" + description + ", grading="
				+ grading + ", approvalStep=" + approvalStep + ", location=" + location + ", typeOfEvent=" + typeOfEvent
				+ ", locationId=" + locationId + ", gradingId=" + gradingId + ", typeOfEventId=" + typeOfEventId
				+ ", approvalStepId=" + approvalStepId + ", cost=" + cost + ", eventDate=" + eventDate + ", formDate="
				+ formDate + ", reimbId=" + reimbId + "]";
	}

	
}
