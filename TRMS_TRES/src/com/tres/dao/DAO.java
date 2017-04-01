package com.tres.dao;

import java.util.ArrayList;

import com.tres.objs.CourseType;
import com.tres.objs.Employee;
import com.tres.objs.Reimbursement;;

public interface DAO
{
	//FUNCTION PROTOTYPES
	
	//GET STUFF FROM DB
	public Employee validUser (String uname, String pwd);
	
	public ArrayList<CourseType> getCourseTypes();

	public ArrayList<String> getGradingTypes();

	public ArrayList<Reimbursement> getPending(int uid);

	public ArrayList<Reimbursement> getHvPending(int uid);
	
	//INSERT STUFF FROM DB
	public int insertReim(Reimbursement r);
	public boolean addAtch(int id, int reimid, String loc);
	
	
	//UPDATE DB
	public void updateAddMsg(int snd, int rcv, String msg, int reimid, int sid);
	public void updateReim(int reimid, int apprid, int repid);
}
