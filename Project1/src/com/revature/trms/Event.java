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
	private int gradingFormat;
	private int eventType;
	private int priority;
	
	public Event() {
			
	}
	
	public Event(Date startDate, String startTime, Date stopDate, String location, String description,
			double cost, String justify, int gradingFormat, int eventType, int priority) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.stopDate = stopDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.justify = justify;
		this.gradingFormat = gradingFormat;
		this.eventType = eventType;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", startDate=" + startDate + ", startTime=" + startTime + ", stopDate="
				+ stopDate + ", location=" + location + ", description=" + description + ", cost=" + cost + ", justify="
				+ justify + ", gradingFormat=" + gradingFormat + ", eventType=" + eventType + ", priority=" + priority
				+ "]";
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

	public int getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(int gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
