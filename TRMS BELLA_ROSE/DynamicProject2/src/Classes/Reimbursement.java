package Classes;

import java.util.Date;

public class Reimbursement
{
	
	private int reimId;
	private int empId;
	private int courseCost;
	private int courseLength;
	private int approvalId;
	private int courseId;
	private int gradeTypeId;
	private String location;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private java.util.Date appDate;
	private String passingGrade;
	private float reim_amnt = 0;
	private String rejectionNote;
	
	public Reimbursement(int reimId, int empId, int courseCost, int courseLength, int approvalId, int courseId,
			int gradeTypeId, int grade, String location, Date startDate, Date endDate, Date appDate,
			String passingGrade, float reim_amnt, String rejectionNote)
	{
		super();
		this.reimId = reimId;
		this.empId = empId;
		this.courseCost = courseCost;
		this.courseLength = courseLength;
		this.approvalId = approvalId;
		this.courseId = courseId;
		this.gradeTypeId = gradeTypeId;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.appDate = appDate;
		this.passingGrade = passingGrade;
		this.reim_amnt = reim_amnt;
		this.rejectionNote = rejectionNote;
	}

	public Reimbursement()
	{
		super();
	}

	public int getReimId()
	{
		return reimId;
	}
	public void setReimId(int reimId)
	{
		this.reimId = reimId;
	}
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public int getCourseCost()
	{
		return courseCost;
	}
	public void setCourseCost(int courseCost)
	{
		this.courseCost = courseCost;
	}
	public int getCourseLength()
	{
		return courseLength;
	}
	public void setCourseLength(int courseLength)
	{
		this.courseLength = courseLength;
	}
	public int getApprovalId()
	{
		return approvalId;
	}
	public void setApprovalId(int approvalId)
	{
		this.approvalId = approvalId;
	}
	public int getCourseId()
	{
		return courseId;
	}
	public void setCourseId(int courseId)
	{
		this.courseId = courseId;
	}
	public int getGradeTypeId()
	{
		return gradeTypeId;
	}
	public void setGradeTypeId(int gradeTypeId)
	{
		this.gradeTypeId = gradeTypeId;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public float getReim_amnt()
	{
		return reim_amnt;
	}
	public void setReim_amnt(float reim_amnt)
	{
		this.reim_amnt = reim_amnt;
	}

	public java.util.Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(java.util.Date startDate)
	{
		this.startDate = startDate;
	}

	public java.util.Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(java.util.Date endDate)
	{
		this.endDate = endDate;
	}

	public java.util.Date getAppDate()
	{
		return appDate;
	}

	public void setAppDate(java.util.Date appDate)
	{
		this.appDate = appDate;
	}

	public String getRejectionNote()
	{
		return rejectionNote;
	}

	public void setRejectionNote(String rejectionNote)
	{
		this.rejectionNote = rejectionNote;
	}

	@Override
	public String toString()
	{
		return "Reimbursement [reimId=" + reimId + ", empId=" + empId + ", courseCost=" + courseCost + ", courseLength="
				+ courseLength + ", approvalId=" + approvalId + ", courseId=" + courseId + ", gradeTypeId="
				+ gradeTypeId + ", location=" + location + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", appDate=" + appDate + ", reim_amnt=" + reim_amnt + "Rejection Note: " + rejectionNote + "]";
	}

	public String getPassingGrade()
	{
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade)
	{
		this.passingGrade = passingGrade;
	}
	
}
