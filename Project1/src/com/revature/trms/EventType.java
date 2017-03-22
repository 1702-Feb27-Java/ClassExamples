package com.revature.trms;

public class EventType {
	
	private int eventTypeId = 0;
	private String eventType;

	public EventType() {
		
	}
	
	public EventType(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	
	public EventType(String eventType) {
		this.eventType = eventType;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
}
