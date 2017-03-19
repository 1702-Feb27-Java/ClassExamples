package com.revature.trms;

public class EventService {
	private int trackingId = 0;
	private String eventDate;
	private int roleId;
	private int statusId;
	private String comments;

	public EventService() {
		
	}
	
	public EventService(int trackingId, String eventDate, int roleId, 
			int statusId, String comments) {
		super();
		this.trackingId = trackingId;
		this.eventDate = eventDate;
		this.roleId = roleId;
		this.statusId = statusId;
		this.comments = comments;
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
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
