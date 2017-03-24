package com.revature.trms;

import java.util.Date;

import com.revature.dao.DAOImpl;

public class EventService {
	

	public EventService() {
		
	}
	
	public static void addNewEvent(Date startDate, String startTime, Date stopDate, 
			String location, String description, double cost, String justify, 
			String gradeFormat, String eventType, String priority, String roleId, int userid){
		
		Event e = new Event(startDate, startTime, stopDate, location, description,
				cost, justify, gradeFormat, eventType, priority);
		Tracking tk = new Tracking(roleId);
	
		DAOImpl.insertEventData(e, tk, userid);
	}
	
	public static void deleteEvent(int eventId){
		Event e = new Event(eventId);
		DAOImpl.deleteEventData(e.getEventId());
	}
}	