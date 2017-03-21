package com.revature.trms;

public class Tracking {
	private int trackingId = 0;
	private long eventDate = 0;
	private int roleId;
	private int statusId = 1;
	private String comments = null;
	
	public Tracking() {

	}
	
	public Tracking(int roleId) {
		super();
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "EventService [trackingId=" + trackingId + ", eventDate=" + eventDate + ", roleId=" + roleId
				+ ", statusId=" + statusId + ", comments=" + comments + "]";
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
