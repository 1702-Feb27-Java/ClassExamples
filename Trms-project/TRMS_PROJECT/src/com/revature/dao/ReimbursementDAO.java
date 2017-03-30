package com.revature.dao;
import java.sql.Date;
import java.util.ArrayList;
import com.revature.pojo.Reimbursement;

public interface ReimbursementDAO {
	
	/*
	 * 
	 * 
	 * USERN in VARCHAR, LOCA in VARCHAR, DATE_ADD in DATE, course_start in DATE, course_end in DATE,
course_len in NUMBER, course_cost in NUMBER, app_id in number, course_id in number, grade_id in number, grade in varchar
	 */
	public boolean InsertIntoReimTable(String username, String location, Date form_add, Date start_course, Date end_course, int course_length,
			int course_cost, int app_id, int id_course, int id_grade, String grade);
	
	//public List<String> GetLocations();
	public ArrayList<String> GetgradeTypes();
	public ArrayList<String> GetCourseTypes();
	public int getCourseID(String course);
	public int getGradeID(String grade);
	
	
	
	
	

}
