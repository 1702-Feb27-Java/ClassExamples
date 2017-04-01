package database.model;

import java.sql.Date;

import database.service.Service;

public class Feedback {
	public Feedback(Integer feedbackId, Integer fromUserId, Integer toUserId, Integer reimbursementId,
			Date dateOfMessage, String feedback) {
		super();
		this.feedbackId = feedbackId;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.reimbursementId = reimbursementId;
		this.dateOfMessage = dateOfMessage;
		this.feedback = feedback;
	}

	public Date getDateOfMessage() {
		return dateOfMessage;
	}
	public void setDateOfMessage(Date dateOfMessage) {
		this.dateOfMessage = dateOfMessage;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedbackId == null) ? 0 : feedbackId.hashCode());
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
		Feedback other = (Feedback) obj;
		if (feedbackId == null) {
			if (other.feedbackId != null)
				return false;
		} else if (!feedbackId.equals(other.feedbackId))
			return false;
		return true;
	}



	private Integer feedbackId;
	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(Integer reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public Integer getToUserId() {
		return toUserId;
	}
	public User getFromUser(){
		return Service.getInstance().getUserFromId(this.fromUserId);

	}
	
	public User getToUser(){
		return Service.getInstance().getUserFromId(this.toUserId);
	}


	private Integer fromUserId;
	private Integer toUserId;
	private Integer reimbursementId;
	private Date dateOfMessage;
	private String feedback;
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", fromUserId=" + fromUserId + ", toUserId=" + toUserId
				+ ", reimbursementId=" + reimbursementId + ", dateOfMessage=" + dateOfMessage + ", feedback=" + feedback
				+ "]";
	}
	
	
	
}
