package database.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import database.dao.Dao;
import database.service.Service;

public class Reimbursement {
	private Date startDate;
	private Date endDate;
	private String customGradingFormat;
	private Integer rejectionFeedbackId;
	public Integer getRejectionFeedbackId() {
		return rejectionFeedbackId;
	}

	public void setRejectionFeedbackId(Integer rejectionFeedbackId) {
		this.rejectionFeedbackId = rejectionFeedbackId;
	}
	
	public Feedback getRejectionFeedback(){
		return Service.getInstance().getFeedbackById(this.rejectionFeedbackId);
	}

	public String getCustomGradingFormat() {
		return customGradingFormat;
	}

	public void setCustomGradingFormat(String customGradingFormat) {
		this.customGradingFormat = customGradingFormat;
	}

	public Reimbursement(Integer reimbursementId, Integer userId, Date dateOfRequest, String description,
			String location, Double costOfEvent, Integer gradeFormat, Integer eventType,
			Date startDate, Date endDate, Date dateOfLastStatusChange, Integer status, String grade,
			String passingGrade, Double reimbursement, String customGradingFormat, Integer rejectionFeedbackId) {
		super();
		this.reimbursementId = reimbursementId;
		this.userId = userId;
		this.dateOfRequest = dateOfRequest;
		this.description = description;
		this.location = location;
		this.costOfEvent = costOfEvent;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
		//this.workTimeMissed = workTimeMissed;
		this.dateOfLastStatusChange = dateOfLastStatusChange;
		this.statusId = status;
		this.grade = grade;
		this.passingGrade = passingGrade;
		this.reimbursement = reimbursement;
		this.customGradingFormat = customGradingFormat;
		this.rejectionFeedbackId = rejectionFeedbackId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbursementId == null) ? 0 : reimbursementId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (reimbursementId == null) {
			if (other.reimbursementId != null)
				return false;
		} else if (!reimbursementId.equals(other.reimbursementId))
			return false;
		return true;
	}

	Integer reimbursementId;
	Integer userId;
	Date dateOfRequest;
	String description;
	String location;
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGradeFormatId() {
		return gradeFormat;
	}
	
	public GradeFormat getGradeFormat(){
		return Service.getInstance().getGradeFormats().get(gradeFormat);
	}

	public void setGradeFormatId(Integer gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public Integer getEventTypeId() {
		return eventType;
	}
	
	public EventType getEventType(){
		return Service.getInstance().getEventType().get(eventType);
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Integer getStatusId() {
		return statusId;
	}
	
	public Status getStatus(){
		return Service.getInstance().getStatuses().get(statusId);
	}
	
	public User getUser(){
		return Dao.getInstance().getUserById(userId);
	}

	public void setStatusId(Integer status) {
		//update time of last status change
		if(!status.equals(statusId)){
			this.dateOfLastStatusChange = Date.valueOf(LocalDate.now());
		}
		
		
		this.statusId = status;
	}

	Double costOfEvent;
	Integer gradeFormat;
	Integer eventType;
	Date dateOfLastStatusChange;
	Integer statusId;
	String grade;
	String passingGrade;
	Double reimbursement;
	
	
	public Double getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Double reimbursement) {
		this.reimbursement = reimbursement;
	}

	public Date getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(Date dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public Double getCostOfEvent() {
		return costOfEvent;
	}

	public void setCostOfEvent(Double costOfEvent) {
		this.costOfEvent = costOfEvent;
	}

	public boolean isUrgent(){
		
		LocalDate startDate = this.getStartDate().toLocalDate();
		LocalDate dateNow = LocalDate.now();
		Period p = Period.between(dateNow, startDate);
		return !p.isNegative() && (p.getDays() <= 14 && p.getMonths() == 0 && p.getYears() == 0);
	}



	public Date getDateOfLastStatusChange() {
		return dateOfLastStatusChange;
	}

	public void setDateOfLastStatusChange(Date dateOfLastStatusChange) {
		this.dateOfLastStatusChange = dateOfLastStatusChange;
	}



	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public Integer getReimbursementId() {
		return reimbursementId;
	}


	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	

}
