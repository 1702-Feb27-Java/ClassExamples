package com.revature.pojo;

public class Reimburstment
{
	String location, addDate, courseStartDate, courseEndDate, time, course, grade;
	int emp_id, courseCost, reimburstAmt, reim_id, approval, courseID, gradeTypeID;
	
	public Reimburstment()
	{
		
	}
	public int getGradeTypeID()
	{
		return gradeTypeID;
	}
	public void setGradeTypeID(int gradeTypeID)
	{
		this.gradeTypeID = gradeTypeID;
	}
	public int getCourseID()
	{
		return courseID;
	}
	public void setCourseID(int courseID)
	{
		this.courseID = courseID;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public String getAddDate()
	{
		return addDate;
	}
	public void setAddDate(String addDate)
	{
		this.addDate = addDate;
	}
	public String getCourseStartDate()
	{
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate)
	{
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndDate()
	{
		return courseEndDate;
	}
	public void setCourseEndDate(String courseEndDate)
	{
		this.courseEndDate = courseEndDate;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getCourse()
	{
		return course;
	}
	public void setCourse(String course)
	{
		this.course = course;
	}
	public String getGrade()
	{
		return grade;
	}
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	public int getApproval()
	{
		return approval;
	}
	public void setApproval(int approval)
	{
		this.approval = approval;
	}
	public int getEmp_id()
	{
		return emp_id;
	}
	public void setEmp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}
	public int getCourseCost()
	{
		return courseCost;
	}
	public void setCourseCost(int courseCost)
	{
		this.courseCost = courseCost;
	}
	public int getReimburstAmt()
	{
		return reimburstAmt;
	}
	public void setReimburstAmt(int reimburstAmt)
	{
		this.reimburstAmt = reimburstAmt;
	}
	public int getReim_id()
	{
		return reim_id;
	}
	public void setReim_id(int reim_id)
	{
		this.reim_id = reim_id;
	}
	@Override
	public String toString()
	{
		return "Reimburstment [location=" + location + ", addDate=" + addDate + ", courseStartDate=" + courseStartDate
				+ ", courseEndDate=" + courseEndDate + ", time=" + time + ", course=" + course + ", grade=" + grade
				+ ", emp_id=" + emp_id + ", courseCost=" + courseCost + ", reimburstAmt=" + reimburstAmt + ", reim_id="
				+ reim_id + ", approval=" + approval + ", courseID=" + courseID + ", gradeTypeID=" + gradeTypeID + "]";
	}
}
