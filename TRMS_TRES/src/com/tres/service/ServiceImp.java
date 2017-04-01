package com.tres.service;

import java.util.ArrayList;

import com.tres.dao.DAOImp;
import com.tres.objs.CourseType;
import com.tres.objs.Employee;
import com.tres.objs.Reimbursement;;

public class ServiceImp implements Service
{
	static DAOImp dao = new DAOImp();

	@Override
	public Employee getUser(String uname, String pwd)
	{
		return dao.validUser(uname, pwd);
	}

	@Override
	public ArrayList<CourseType> getCourseTypes()
	{
		return dao.getCourseTypes();
	}

	@Override
	public ArrayList<String> getGradingTypes()
	{
		return dao.getGradingTypes();
	}

	@Override
	public int insertReim(Reimbursement r)
	{
		// TODO Auto-generated method stub
		return dao.insertReim(r);
	}

	@Override
	public boolean addAtch(int id, int reimid, String loc)
	{
		// TODO Auto-generated method stub
		return dao.addAtch(id, reimid, loc) ;
	}

	@Override
	public ArrayList<Reimbursement> getPending(int uid)
	{
		// TODO Auto-generated method stub
		return dao.getPending(uid);
	}
	@Override
	public ArrayList<Reimbursement> getHvPending(int uid)
	{
		// TODO Auto-generated method stub
		return dao.getHvPending(uid);
	}

	@Override
	public void updateAddMsg(int snd, int rcv, String msg, int reimid, int sid)
	{
		dao.updateAddMsg(snd, rcv, msg, reimid, sid);
	}

	@Override
	public void updateReim(int reimid, int apprid, int repid, int send, int recv)
	{
		dao.updateReim(reimid, apprid, repid, send, recv);
	}
}
