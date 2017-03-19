package com.revature.trms;

import java.util.Date;

public class EventService {
	

	public EventService() {
		
	}
	
	public static void addNewEvent(Date startDate, String startTime, Date stopDate, 
			String location, String description, double cost, String justify, 
			int gradingFormat, int eventType, int priority, int roleId){
		
		Event ev = new Event(startDate, startTime, stopDate, location, description,
				cost, justify, gradingFormat, eventType, priority);
		System.out.println(ev);
		
		Tracking tk = new Tracking(roleId);
		System.out.println(tk);
	}
}	