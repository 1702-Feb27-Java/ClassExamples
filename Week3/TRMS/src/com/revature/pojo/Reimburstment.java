package com.revature.pojo;

public class Reimburstment
{
	String location, addDate, courseStartDate, courseEndDate, time, course, grade, approvalString, courseIDString, gradeTypeString;
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
		if(gradeTypeID == 1)
		{
			gradeTypeString = "Pass-Fail";
		}
		else if(gradeTypeID == 2)
		{
			gradeTypeString = "A-F";
		}
	}
	public String getGradeTypeString()
	{
		return gradeTypeString;
	}
	public int getCourseID()
	{
		return courseID;
	}
	public void setCourseID(int courseID)
	{
		this.courseID = courseID;
		if(courseID == 1)
		{
			courseIDString = "University Course";
		}
		else if(courseID == 2)
		{
			courseIDString = "Seminars";
		}
		else if(courseID == 3)
		{
			courseIDString = "Certification Prep. Class";
		}
		else if(courseID == 4)
		{
			courseIDString = "Certification";
		}
		else if(courseID == 5)
		{
			courseIDString = "Technical";
		}
		else if(courseID == 6)
		{
			courseIDString = "Other";
		}
	}
	public String getCourseIDString()
	{
		return courseIDString;
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
		if(approval == 1)
		{
			approvalString = "Waiting on Supervisor";
		}
		else if(approval == 2)
		{
			approvalString = "Waiting on Dept. Head";
		}
		else if(approval == 3)
		{
			approvalString = "Waiting on Benco";
		}
		else if(approval == 4)
		{
			approvalString = "Approved";
		}
		else if(approval == 5)
		{
			approvalString = "Declined";
		}
	}
	public String getApprovalString()
	{
		return approvalString;
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
