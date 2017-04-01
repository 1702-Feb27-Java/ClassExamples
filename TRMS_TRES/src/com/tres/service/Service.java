package com.tres.service;

import java.util.ArrayList;

import com.tres.objs.CourseType;
import com.tres.objs.Employee;
import com.tres.objs.Reimbursement;;

public interface Service
{
	//FUNCTION PROTOTYOES
	
	//GET STUFF FROM DATABASE
	public Employee getUser (String uname, String pwd);
	public ArrayList<CourseType> getCourseTypes();
	public ArrayList<String> getGradingTypes();
	public ArrayList<Reimbursement> getPending(int uid);
	public ArrayList<Reimbursement> getHvPending(int uid);
	
	
	
	//INSERT INTO DATABASE
	public int insertReim(Reimbursement r);
	public boolean addAtch(int id, int reimid, String loc);
	
	
	//UPDATE DATABASE
	public void updateAddMsg(int snd, int rcv, String msg, int reimid, int sid);
	public void updateReim(int reimid, int apprid, int repid);
}
