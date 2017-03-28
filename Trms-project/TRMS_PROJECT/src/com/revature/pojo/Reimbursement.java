package com.revature.pojo;

/**
 * This class will hold the values of what is stored in the reimbursement table
 * will be put into an ArrayList for tracking and displaying data 
 * @author Nick
 *
 */
public class Reimbursement {
 
	private int Emp_id;
	private String Location_;
	private int Add_date;
	private int start_course;
	private int end_course;
	private int course_time;
	private int course_cost;
	private int Reim_id;
	private int Appr_id;
	private int course_id;
	private int grade_type_id;
	private String grade;
	
	//setters and getters for class
	public int getEmp_id() {
		return Emp_id;
	}
	public void setEmp_id(int emp_id) {
		Emp_id = emp_id;
	}
	public String getLocation_() {
		return Location_;
	}
	public void setLocation_(String location_) {
		Location_ = location_;
	}
	public int getAdd_date() {
		return Add_date;
	}
	public void setAdd_date(int add_date) {
		Add_date = add_date;
	}
	public int getStart_course() {
		return start_course;
	}
	public void setStart_course(int start_course) {
		this.start_course = start_course;
	}
	public int getEnd_course() {
		return end_course;
	}
	public void setEnd_course(int end_course) {
		this.end_course = end_course;
	}
	public int getCourse_time() {
		return course_time;
	}
	public void setCourse_time(int course_time) {
		this.course_time = course_time;
	}
	public int getCourse_cost() {
		return course_cost;
	}
	public void setCourse_cost(int course_cost) {
		this.course_cost = course_cost;
	}
	public int getReim_id() {
		return Reim_id;
	}
	public void setReim_id(int reim_id) {
		Reim_id = reim_id;
	}
	public int getAppr_id() {
		return Appr_id;
	}
	public void setAppr_id(int appr_id) {
		Appr_id = appr_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getGrade_type_id() {
		return grade_type_id;
	}
	public void setGrade_type_id(int grade_type_id) {
		this.grade_type_id = grade_type_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Reimbursement [Emp_id=" + Emp_id + ", Location_=" + Location_ + ", Add_date=" + Add_date
				+ ", start_course=" + start_course + ", end_course=" + end_course + ", course_time=" + course_time
				+ ", course_cost=" + course_cost + ", Reim_id=" + Reim_id + ", Appr_id=" + Appr_id + ", course_id="
				+ course_id + ", grade_type_id=" + grade_type_id + ", grade=" + grade + "]";
	}
	
	
	
}
