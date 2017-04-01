package User;

import java.util.ArrayList;

public class appinfo {
	private String firstname;
	private String lastname;
	private String location;
	private String enddate;
	private String startdate;
	private int coursecost;
	private String selectcoursetype;
	private String coursetypetext;
	private String gradeformat;
	private String gradeformattext;
	private String reason;
	private int employee_Id;
	private int courseduration;
	private String addDate;
	private int reimbursementAmmount;
	private int reim_id;
	private int approval_id;
	private int course_id;
	private int grade_id;
	private int grade;
	
	////// reason=" + reason + "/////
	@Override
	public String toString() {
		return "appinfo [location=" + location + ", enddate="
				+ enddate + ", startdate=" + startdate + ", coursecost=" + coursecost + ", coursetypetext=" + coursetypetext + ","
				+ " gradeformattext=" + gradeformattext + ", employee_Id=" + employee_Id
				+ ", courseduration=" + courseduration + ", addDate=" + addDate + ", reimbursementAmmount="
				+ reimbursementAmmount + ", reim_id=" + reim_id + ", approval_id=" + approval_id + ", course_id="
				+ course_id + ", grade_id=" + grade_id + ", grade=" + grade + "]";
	}
	public int getReim_id() {
		return reim_id;
	}
	public void setReim_id(int reim_id) {
		this.reim_id = reim_id;
	}
	public int getApproval_id() {
		return approval_id;
	}
	public void setApproval_id(int approval_id) {
		this.approval_id = approval_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getReimbursementAmmount() {
		return reimbursementAmmount;
	}
	public void setReimbursementAmmount(int reimbursementAmmount) {
		this.reimbursementAmmount = reimbursementAmmount;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public int getCourseduration() {
		return courseduration;
	}
	public void setCourseduration(int courseduration) {
		this.courseduration = courseduration;
	}
	public int getEmployee_id() {
		return employee_Id;
	}
	public void setEmployee_id(int employee_id) {
		employee_Id = employee_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public int getCoursecost() {
		return coursecost;
	}
	public void setCoursecost(int coursecost) {
		this.coursecost = coursecost;
	}
	public String getSelectcoursetype() {
		return selectcoursetype;
	}
	public void setSelectcoursetype(String selectcoursetype) {
		this.selectcoursetype = selectcoursetype;
	}
	public String getCoursetypetext() {
		return coursetypetext;
	}
	public void setCoursetypetext(String coursetypetext) {
		this.coursetypetext = coursetypetext;
	}
	public String getGradeformat() {
		return gradeformat;
	}
	public void setGradeformat(String gradeformat) {
		this.gradeformat = gradeformat;
	}
	public String getGradeformattext() {
		return gradeformattext;
	}
	public void setGradeformattext(String gradeformattext) {
		this.gradeformattext = gradeformattext;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}

