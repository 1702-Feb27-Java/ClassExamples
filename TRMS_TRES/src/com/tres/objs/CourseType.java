package com.tres.objs;

public class CourseType
{
	private int crsid;
	private String name;
	private double percent;
	
	//GETTERS AND SETTERS ==========================================================
	public final int getCrsid()
	{
		return crsid;
	}
	public final void setCrsid(int crsid)
	{
		this.crsid = crsid;
	}
	public final String getName()
	{
		return name;
	}
	public final void setName(String name)
	{
		this.name = name;
	}
	public final double getPercent()
	{
		return percent;
	}
	public final void setPercent(double percent)
	{
		this.percent = percent;
	}
	//CONSTRUCTOR ==========================================================
	public CourseType(int crsid, String name, double percent)
	{
		super();
		this.crsid = crsid;
		this.name = name;
		this.percent = percent;
	}
	
}
