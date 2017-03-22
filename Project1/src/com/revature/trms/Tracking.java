package com.revature.trms;

public class Tracking {
	private int trackingId = 0;
	private long eventDate = 0;
	private String roleId;
	private String status = "1";
	private String comments = null;
	
	public Tracking() {

	}
	
	public Tracking(String status) {
		super();
		this.status = status;
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

	public long getEventDate() {
		return eventDate;
	}

	public void setEventDate(long eventDate) {
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
