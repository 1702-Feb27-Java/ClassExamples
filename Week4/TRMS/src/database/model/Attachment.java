package database.model;

import java.time.LocalDate;
import java.sql.Date;

public class Attachment {

	public Attachment(Integer attachmentId, String attachmentType, Integer reimbursementId, Date timeUploaded,
			String attachmentLoc) {
		super();
		this.attachmentId = attachmentId;
		this.attachmentType = attachmentType;
		this.reimbursementId = reimbursementId;
		this.timeUploaded = timeUploaded;
		this.attachmentLoc = attachmentLoc;
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
		Attachment other = (Attachment) obj;
		if (reimbursementId == null) {
			if (other.reimbursementId != null)
				return false;
		} else if (!reimbursementId.equals(other.reimbursementId))
			return false;
		return true;
	}
	Integer attachmentId;
	String attachmentType;
	Integer reimbursementId;
	Date timeUploaded;
	String attachmentLoc;

	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public Integer getReimbursement() {
		return reimbursementId;
	}
	public void setReimbursementId(Integer reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public Date getTimeUploaded() {
		return timeUploaded;
	}
	public void setTimeUploaded(Date timeUploaded) {
		this.timeUploaded = timeUploaded;
	}
	public String getAttachmentLoc() {
		return attachmentLoc;
	}
	public void setAttachmentLoc(String attachmentLoc) {
		this.attachmentLoc = attachmentLoc;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", attachmentType=" + attachmentType + ", reimbursementId="
				+ reimbursementId + ", timeUploaded=" + timeUploaded + ", attachmentLoc=" + attachmentLoc + "]";
	}
	
}
