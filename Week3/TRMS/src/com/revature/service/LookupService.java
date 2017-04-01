package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.LookupDaoImpl;
import com.revature.pojo.Grade;

public class LookupService {
	LookupDaoImpl lookupDao = new LookupDaoImpl();

	public String getRoleById(int roleId){
		return lookupDao.getRole(roleId);
	}
	
	// return an array list of role strings
	public ArrayList<String> getRoles(){
		ArrayList<String> roles = lookupDao.listRoles();
		return roles;
	}
	
	public String getDeptById(int deptId){
		return lookupDao.getDept(deptId);
	}
	// return an array list of dept strings
	public ArrayList<String> getDepts(){
		ArrayList<String> depts = lookupDao.listDepts();
		return depts;
	}
	
	public String getGradeById(int gradeId){
		return lookupDao.getGrade(gradeId);
	}
	// return an array list of grade format strings
	public ArrayList<Grade> getGrades(){
		ArrayList<Grade> grades = lookupDao.listGrades();
		return grades;
	}
	// insert a grade format
	// return true if insertGrade successfully inserted gradeID
	public int insertGrade(Grade g) {
		return lookupDao.insertGrade(g);
	}
	
	public int getGradeCount() {
		return lookupDao.countGrades();
	}

	// return a single event title by id
	public String getEventTitleById(int eventTitleId){
		return lookupDao.getEventTitle(eventTitleId);
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

	public String getUrgencyLevelById(int urgencyId) {
		// TODO Auto-generated method stub
		return lookupDao.getUrgencyLevel(urgencyId);
	}
	
	public String getAppLevelTitleById(int appLevel) {
		return lookupDao.getAppLevelTitle(appLevel);
	}


}
