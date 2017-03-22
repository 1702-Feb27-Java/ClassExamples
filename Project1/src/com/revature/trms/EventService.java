package com.revature.trms;

import java.util.Date;

import com.revature.dao.DAOImpl;

public class EventService {
	

	public EventService() {
		
	}
	
	public static void addNewEvent(Date startDate, String startTime, Date stopDate, 
			String location, String description, double cost, String justify, 
			int gFormatId, int eventTypeId, int priorityId, int roleId, int userid){
		
		Event e = new Event(startDate, startTime, stopDate, location, description,
				cost, justify);
		GradingFormats gf = new GradingFormats(gFormatId);
		EventType et = new EventType(eventTypeId);
		Priority p = new Priority(priorityId);
		Tracking tk = new Tracking(roleId);
	
		DAOImpl.insertEventData(e, gf, et, p, tk, userid);
	}
}	