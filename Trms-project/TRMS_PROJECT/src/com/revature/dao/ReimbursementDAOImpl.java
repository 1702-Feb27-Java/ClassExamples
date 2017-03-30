package com.revature.dao;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import com.revature.connect.ConnectionUtil;
import com.revature.pojo.*;
import com.revature.dao.*;
import com.revature.pojo.Reimbursement;

/*
 *  USERN in VARCHAR, LOCA in VARCHAR, DATE_ADD in DATE, course_start in DATE, course_end in DATE,
course_len in NUMBER, course_cost in NUMBER, app_id in number, course_id in number, grade_id in number, grade in varchar
 */

public class ReimbursementDAOImpl implements ReimbursementDAO {
	
	static public ArrayList<Reimbursement> forms = new ArrayList<Reimbursement>();
	
	//note: for the app_id param it will be one which is pending going up the chain of command as need be
	@Override
	public boolean InsertIntoReimTable(String username, String location, Date form_add, Date start_course, Date end_course,
			int course_length, int course_cost, int app_id, int id_course, int id_grade, String grade) {
		
			//creating reimbursement object and local variables for its setter
			//Reimbursement this_form = new Reimbursement();
			boolean applied = false;
			try( Connection connect = ConnectionUtil.getConnection();) {
				connect.setAutoCommit(false);
				
				String sql = "CALL REIMBURSTMENT_APPLY(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.setString(1, username);
				cs.setString(2, location);
				cs.setDate(3, form_add);
				cs.setDate(4, start_course);
				cs.setDate(5, end_course);
				cs.setInt(6, course_length);
				cs.setInt(7, course_cost);
				cs.setInt(8, app_id = 1);
				cs.setInt(9, id_course);
				cs.setInt(10, id_grade);
				cs.setString(11, grade);
				
				cs.executeUpdate();
				
				connect.commit();
				
				applied = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return applied;
			

	}

	@Override
	public ArrayList<String> GetgradeTypes() {
		
		ArrayList<String> gradeType = new ArrayList<String>();
		
		try( Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "SELECT GRADE_TYPE FROM GRADE TYPE";
			Statement stmt = connect.prepareStatement(sql);
			
			ResultSet rs = stmt.getResultSet();
			
			while( rs.next() ) {
				String gr = rs.getString(1);
				
				gradeType.add(gr);
			}
			
			connect.commit();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gradeType;
	}

	@Override
	public ArrayList<String> GetCourseTypes() {
		
		ArrayList<String> coursetypes = new ArrayList<String>();
		
		try( Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "SELECT COURSE_TYPE FROM COURSE";
			
			Statement stmt = connect.prepareStatement(sql);
			ResultSet rs = stmt.getResultSet();
			
			while( rs.next() ) {
				
				String cs = rs.getString(1);
				
				coursetypes.add(cs);
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coursetypes;
	}

	@Override
	public int getCourseID(String course) {
		
		int course_id = 0;
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			connect.setAutoCommit(false);
			String sql = "SELECT COURSE_ID FROM COURSE WHERE COURSE_TYPE = '"+ course + "'";
			Statement stmt = connect.createStatement();
			
			course_id = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_id;
	}

	@Override
	public int getGradeID(String grade) {
		int grade_id = 0;
		try( Connection connect = ConnectionUtil.getConnection();) {
			
			connect.setAutoCommit(false);
			String sql = "SELECT GRADE_ID FROM GRADE_TYPE g WHERE g.GRADE_TYPE = '"+ grade + "'";
			Statement stmt = connect.createStatement();
			
			grade_id = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade_id;
	}
	
	

	

}
