package com.tres.objs;

import java.util.ArrayList;

public class Reimbursement
{
	int emid;
	String loc,
			formtm,
			coursedes;
	double cost;
	int gradeid,
		courseid;
	String workjus, 
			tmmiss,
			crsStart,
			crsEnd;
	int approd;
	String formdt,
			gradeval;
	int curr_on;
	String attch,
			creator;
	int sid;
	String status;
	int reimid;
	ArrayList<Message>  msgs;
	//GETTERS AND SETTERS ===========================================
	public final int getEmid()
	{
		return emid;
	}
	public final int getReimid()
	{
		return reimid;
	}
	public final void setReimid(int reimid)
	{
		this.reimid = reimid;
	}
	public final void setEmid(int emid)
	{
		this.emid = emid;
	}
	public final String getLoc()
	{
		return loc;
	}
	public final void setLoc(String loc)
	{
		this.loc = loc;
	}
	public final String getFormtm()
	{
		return formtm;
	}
	public final void setFormtm(String formtm)
	{
		this.formtm = formtm;
	}
	public final String getCoursedes()
	{
		return coursedes;
	}
	public final void setCoursedes(String coursedes)
	{
		this.coursedes = coursedes;
	}
	public final double getCost()
	{
		return cost;
	}
	public final void setCost(double cost)
	{
		this.cost = cost;
	}
	public final int getGradeid()
	{
		return gradeid;
	}
	public final void setGradeid(int gradeid)
	{
		this.gradeid = gradeid;
	}
	public final int getCourseid()
	{
		return courseid;
	}
	public final void setCourseid(int courseid)
	{
		this.courseid = courseid;
	}
	public final String getWorkjus()
	{
		return workjus;
	}
	public final void setWorkjus(String workjus)
	{
		this.workjus = workjus;
	}
	public final String getTmmiss()
	{
		return tmmiss;
	}
	public final void setTmmiss(String tmmiss)
	{
		this.tmmiss = tmmiss;
	}
	public final String getCrsStart()
	{
		return crsStart;
	}
	public final void setCrsStart(String crsStart)
	{
		this.crsStart = crsStart;
	}
	public final String getCrsEnd()
	{
		return crsEnd;
	}
	public final void setCrsEnd(String crsEnd)
	{
		this.crsEnd = crsEnd;
	}
	public final int getApprod()
	{
		return approd;
	}
	public final void setApprod(int approd)
	{
		this.approd = approd;
	}
	public final String getFormdt()
	{
		return formdt;
	}
	public final void setFormdt(String formdt)
	{
		this.formdt = formdt;
	}
	public final String getGradeval()
	{
		return gradeval;
	}
	public final void setGradeval(String gradeval)
	{
		this.gradeval = gradeval;
	}
	public final int getCurr_on()
	{
		return curr_on;
	}
	public final void setCurr_on(int curr_on)
	{
		this.curr_on = curr_on;
	}
	public final String getAttch()
	{
		return attch;
	}
	public final void setAttch(String attch)
	{
		this.attch = attch;
	}
	public final String getCreator()
	{
		return creator;
	}
	public final void setCreator(String creator)
	{
		this.creator = creator;
	}
	public final int getSid()
	{
		return sid;
	}
	public final void setSid(int sid)
	{
		this.sid = sid;
	}
	public final String getStatus()
	{
		return status;
	}
	public final void setStatus(String status)
	{
		this.status = status;
	}
	public final ArrayList<Message> getMsgs()
	{
		return msgs;
	}
	public final void setMsgs(ArrayList<Message> msgs)
	{
		this.msgs = msgs;
	}
	//=============== CONSTURCTORS ============================================
	public Reimbursement(int emid, String loc, String formtm, String coursedes, double cost, int gradeid, int courseid,
			String workjus, String tmmiss, String crsStart, String crsEnd, int approd, String formdt, String gradeval, int curr_on, int sid)
	{
		super();
		this.emid = emid;
		this.loc = loc;
		this.formtm = formtm;
		this.coursedes = coursedes;
		this.cost = cost;
		this.gradeid = gradeid;
		this.courseid = courseid;
		this.workjus = workjus;
		this.tmmiss = tmmiss;
		this.crsStart = crsStart;
		this.crsEnd = crsEnd;
		this.approd = approd;
		this.formdt = formdt;
		this.gradeval = gradeval;
		this.curr_on = curr_on;
		this.sid = sid;
	}
	@Override
	public String toString()
	{
		return "Reimbursement [emid=" + emid + ", loc=" + loc + ", formtm=" + formtm + ", coursedes=" + coursedes
				+ ", cost=" + cost + ", gradeid=" + gradeid + ", courseid=" + courseid + ", workjus=" + workjus
				+ ", tmmiss=" + tmmiss + ", crsStart=" + crsStart + ", crsEnd=" + crsEnd + ", approd=" + approd
				+ ", formdt=" + formdt + ", gradeval=" + gradeval + ", attch=" + attch + "]";
	}
	public String toSql()
	{
		return emid + "," + loc + "," + formtm + "," + coursedes +
				"," + cost + "," + gradeid + "," + courseid + "," +
				workjus + "," + tmmiss + "," + crsStart + "," +
				crsEnd + "," + approd+ "," + formdt + "," + gradeval;
	}
}
