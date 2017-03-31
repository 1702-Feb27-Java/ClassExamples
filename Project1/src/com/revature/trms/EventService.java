package com.revature.trms;

import java.util.ArrayList;
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
	
	public static String approveEvent(String roleId){
		switch(roleId){
		case "4":
			return "3";
		case "3":
			return "2";
		case "2":
			return "1";
		}
		return "0";
	}
	
	public static void editEvent(ArrayList<Event> eventList, int eventId){
		
		Event e = new Event(eventList.get(eventId).getEventId(),eventList.get(eventId).getStartDate(),
				eventList.get(eventId).getStartTime(),eventList.get(eventId).getStopDate(),
				eventList.get(eventId).getLocation(),eventList.get(eventId).getDescription(),
				eventList.get(eventId).getCost(),eventList.get(eventId).getJustify(),
				eventList.get(eventId).getGradeFormat(),eventList.get(eventId).getEventType(),
				eventList.get(eventId).getPriority());
		DAOImpl.editEvents(e);
	}
}	