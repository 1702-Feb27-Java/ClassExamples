package com.revature.trms;

public class Tracking {
	private int trackingId = 0;
	private String eventDate;
	private String roleId;
	private String status = "1";
	private String comments = null;
	
	public Tracking() {

	}
	
	public Tracking(String status) {
		super();
		this.status = status;
	}
		
	public Tracking(int trackingId, String eventDate, String roleId, String status, String comments) {
		super();
		this.trackingId = trackingId;
		this.eventDate = eventDate;
		this.roleId = roleId;
		this.status = status;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "EventService [trackingId=" + trackingId + ", eventDate=" + eventDate + ", roleId=" + roleId
				+ ", statusId=" + status + ", comments=" + comments + "]";
	}

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String statusId) {
		this.status = statusId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
