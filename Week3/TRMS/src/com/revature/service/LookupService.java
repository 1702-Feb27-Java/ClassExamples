package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.LookupDaoImpl;
import com.revature.pojo.Grade;

public class LookupService {
	LookupDaoImpl lookupDao = new LookupDaoImpl();
	// return an array list of role strings
	public ArrayList<String> getRoles(){
		ArrayList<String> roles = lookupDao.listRoles();
		return roles;
	}
	// return an array list of dept strings
	public ArrayList<String> getDepts(){
		ArrayList<String> depts = lookupDao.listDepts();
		return depts;
	}
	// return an array list of grade format strings
	public ArrayList<Grade> getGrades(){
		ArrayList<Grade> grades = lookupDao.listGrades();
		return grades;
	}
	// insert a grade format
	// return true if insertGrade successfully inserted gradeID
	public boolean insertGrade(Grade g) {
		lookupDao.insertGrade(g);
		return (g.getGradeId() == 0 ? false : true);
	}
	// return a single event title by id
	public String getEventTitleById(int eventTitleId){
		String eventTitle = lookupDao.getEventTitle(eventTitleId);
		return eventTitle;
	}
	// return an array list of event title strings
	public ArrayList<String> getEventTitles(){
		ArrayList<String> eventTitles = lookupDao.listEventTitles();
		return eventTitles;
	}
	
	// insert an event title
	// return last id of event_title
	public int insertEvent(String eventTitle) {
		return lookupDao.insertEvent(eventTitle);
	}
	

}
