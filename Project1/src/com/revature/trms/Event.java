package com.revature.trms;

import java.util.Date;

public class Event {
	private int eventId = 0;
	private Date startDate;
	private String startTime;
	private Date stopDate;
	private String location;
	private String description;
	private double cost;
	private String justify;
	private String gradeFormat;
	private String eventType;
	private String priority;
	private int closed = 0;
	
	public Event() {
			
	}
	
	public Event(Date startDate, String startTime, Date stopDate, String location, String description,
			double cost, String justify, String gradeFormat, String eventType, String priority) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.stopDate = stopDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.justify = justify;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.priority = priority;
	}
	
	public Event(int eventId, Date startDate, String startTime, Date stopDate, String location, String description,
			double cost, String justify, String gradeFormat, String eventType, String priority) {
		super();
		this.eventId = eventId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.stopDate = stopDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.justify = justify;
		this.gradeFormat = gradeFormat;
		this.eventType = eventType;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", startDate=" + startDate + ", startTime=" + startTime + ", stopDate="
				+ stopDate + ", location=" + location + ", description=" + description + ", cost=" + cost + ", justify="
				+ justify + ", gradeFormat=" + gradeFormat + ", eventType=" + eventType + ", priority=" + priority
				+ ", closed=" + closed + "]";
	}

	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getJustify() {
		return justify;
	}

	public void setJustify(String justify) {
		this.justify = justify;
	}
	
	public String getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}
}
