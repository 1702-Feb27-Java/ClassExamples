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
		
		System.out.println(roleId);
			
		DAOImpl.insertEventData(e, roleId, userid);
	}
	
	public static void deleteEvent(int userId, int eventId, double cost){
		DAOImpl.deleteEventData(userId, eventId, cost);
	}
}	