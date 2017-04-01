package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.rev.util.ConnectionUtil;

import User.UserInfo;
import User.appinfo;

import User.Messages;

public class DAOimp implements DAOcalls {

	private static ArrayList<UserInfo> currentuserinfo = new ArrayList<>();
	private static ArrayList<appinfo> currentappinfo = new ArrayList<>();

	@Override
	public UserInfo getUserInfo(UserInfo current) {

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			// this will need to know whos info to pull after verified
			String sql = "Select * from employee WHERE email = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, current.getEmail());
			ResultSet rs = ps.executeQuery();

			// i think tis is saving vals as it goes through the row in the DB
			while (rs.next()) {
				current.setFirstname(rs.getString(1));
				current.setLastname(rs.getString(2));
				current.setAddress(rs.getString(3));
				current.setEmployee_id(rs.getInt(4));
				current.setDep_id(rs.getInt(5));
				current.setReportsto(rs.getInt(6));
				current.setRole_id(rs.getInt(7));
				current.setUsername(rs.getString(8));
				current.setPassword(rs.getString(9));
			}
			connect.commit();

			return current;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return current;
	}

	@Override
	public UserInfo CreateRequest() {
		// ArrayList<>

		return null;
	}
	// @Override
	// void updatestuff(){
	// try (Connection connect = ConnectionUtil.getConnection();) {
	// connect.setAutoCommit(false);
	// general update statement?
	// i dont know if this will work...
	// String sql = "UPDATE" + desired table + "SET" + columnname +
	// "= '" + newvalue + "' where employee_id =" +
	// desireduser;
	// the thought is to have variables replace the BS

	@Override
	public UserInfo ApplicationUpdate(appinfo newapp, HttpServletRequest request) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			// HttpSession insertinfo = request.getSession();

			//
			// String sql = "INSERT INTO REIMBURSTMENT set class_locaton = '" +
			// ""
			// + "' class_duration = '" + ""
			// + "' add_date = '" + ""
			// + "'start_date = '" + ""
			// + "' end_date= '" + ""
			// + "' course_cost = '" + ""
			// + "' reimburstment_amount= '" + ""
			// + "' reim_id = '" + ""
			// + "' approval_id = '" + ""
			// + "' course_id= '" + ""
			// + "' grade_id = '" + " "
			// + "' grade = '" + ""
			// + "'" + "wHERE employee_id = '"+ "";
			//
			// INSERT INTO REIMBURSEMENT (employee_id, class_location,
			// class_duration, add_date, start_date, end_date, course_cost,
			// REIMBURSEMENT_AMMOUNT, course_id, grade_id)
			// VALUES (2, 'de', 3, TO_DATE('2007/07/28', 'yyyy/mm/dd'),
			// TO_DATE('2007/07/28', 'yyyy/mm/dd'),TO_DATE('2007/07/28',
			// 'yyyy/mm/dd'), 500, 500, 102, 101);
			//

			String sql = "INSERT INTO REIMBURSEMENT (employee_id, class_location, class_duration, add_date, start_date, end_date, course_cost, REIMBURSEMENT_AMMOUNT, course_id, grade_id)"
					+ "VALUES (?, ?, ?, TO_DATE(?, 'yyyy/mm/dd'), TO_DATE(?, 'yyyy/mm/dd'), TO_DATE(?, 'yyyy/mm/dd'), ?, ?, ?,  ?)";

			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, newapp.getEmployee_id());
			ps.setString(2, newapp.getLocation());
			ps.setInt(3, newapp.getCourseduration());

			// this need to change to the actual date
			// System.out.println(dateFormat.format(date));
			// String modifiedDate= new
			// SimpleDateFormat("yyyy-MM-dd").format(d1);
			// System.out.println(newapp.getSelectcoursetype() + "check it");
			// System.out.println(newapp.getGradeformat() + "for the love of
			// god...");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			ps.setString(4, dateFormat.format(date));

			ps.setString(5, newapp.getStartdate());
			ps.setString(6, newapp.getEnddate());
			ps.setInt(7, newapp.getCoursecost());

			// this need to change
			
			
////////////////////////////////******************* FIX THISSSSSSSSSSS******************////////////////////////////////////////////////////
			ps.setInt(8, newapp.getCoursecost());
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			ps.setString(9, newapp.getSelectcoursetype());
			ps.setString(10, newapp.getGradeformat());
			ResultSet rs = ps.executeQuery();

			if ((newapp.getCoursetypetext() != null) || (newapp.getCoursetypetext() != "")) {
				String sql2 = "INSERT INTO MESSAGES (SENDER, MESSAGE) " + "VALUES (?, 'course info:' ?)";
				ps.setInt(1, newapp.getEmployee_id());
				ps.setString(2, newapp.getCoursetypetext());

			}
			if ((newapp.getGradeformattext() != null) || (newapp.getGradeformattext() != "")) {
				String sql2 = "INSERT INTO MESSAGES (SENDER, MESSAGE) " + "VALUES (?, 'grade info: ?)";
				ps.setInt(1, newapp.getEmployee_id());
				ps.setString(2, newapp.getGradeformattext());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<appinfo> getAllInfoBenCo() {
		ArrayList<appinfo> allapprovalinfo = new ArrayList<>();
		appinfo infoHolder = new appinfo();
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			////// this may need to be a join to get messages
			String sql = "SELECT * FROM reimbursement";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoHolder = new appinfo();
				infoHolder.setEmployee_id(rs.getInt(1));
				infoHolder.setLocation(rs.getString(2));
				infoHolder.setCourseduration(rs.getInt(3));
				infoHolder.setAddDate(rs.getString(4));
				infoHolder.setStartdate(rs.getString(5));
				infoHolder.setEnddate(rs.getString(6));
				infoHolder.setCoursecost(rs.getInt(7));
				infoHolder.setReimbursementAmmount(rs.getInt(8));
				infoHolder.setReim_id(rs.getInt(9));
				infoHolder.setApproval_id(rs.getInt(10));
				infoHolder.setCourse_id(rs.getInt(11));
				infoHolder.setGrade_id(rs.getInt(12));
				infoHolder.setGrade(rs.getInt(13));
				allapprovalinfo.add(infoHolder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allapprovalinfo;
	}

	@Override
	public appinfo setApprovalLevelBenCo() {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "UPDATE reimbursement SET approval_id = 104 WHERE employee_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public ArrayList<appinfo> getAllInfoSupervisor() {
		ArrayList<appinfo> allapprovalinfo = new ArrayList<>();
		appinfo infoHolder = new appinfo();
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM reimbursement WHERE APPROVAL_ID < 103";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoHolder = new appinfo();
				infoHolder.setEmployee_id(rs.getInt(1));
				infoHolder.setLocation(rs.getString(2));
				infoHolder.setCourseduration(rs.getInt(3));
				infoHolder.setAddDate(rs.getString(4));
				infoHolder.setStartdate(rs.getString(5));
				infoHolder.setEnddate(rs.getString(6));
				infoHolder.setCoursecost(rs.getInt(7));
				infoHolder.setReimbursementAmmount(rs.getInt(8));
				infoHolder.setReim_id(rs.getInt(9));
				infoHolder.setApproval_id(rs.getInt(10));
				infoHolder.setCourse_id(rs.getInt(11));
				infoHolder.setGrade_id(rs.getInt(12));
				infoHolder.setGrade(rs.getInt(13));
				allapprovalinfo.add(infoHolder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allapprovalinfo;
	}

	@Override
	public appinfo setApprovalLevelSupervisor() {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "UPDATE reimbursement SET approval_id = 102 WHERE employee_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<appinfo> getAllInfoHead() {
		ArrayList<appinfo> allapprovalinfo = new ArrayList<>();
		appinfo infoHolder = new appinfo();
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM reimbursement WHERE APPROVAL_ID < 104 ";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoHolder = new appinfo();
				infoHolder.setEmployee_id(rs.getInt(1));
				infoHolder.setLocation(rs.getString(2));
				infoHolder.setCourseduration(rs.getInt(3));
				infoHolder.setAddDate(rs.getString(4));
				infoHolder.setStartdate(rs.getString(5));
				infoHolder.setEnddate(rs.getString(6));
				infoHolder.setCoursecost(rs.getInt(7));
				infoHolder.setReimbursementAmmount(rs.getInt(8));
				infoHolder.setReim_id(rs.getInt(9));
				infoHolder.setApproval_id(rs.getInt(10));
				infoHolder.setCourse_id(rs.getInt(11));
				infoHolder.setGrade_id(rs.getInt(12));
				infoHolder.setGrade(rs.getInt(13));
				allapprovalinfo.add(infoHolder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allapprovalinfo;
	}

	@Override
	public appinfo setApprovalLevelHead() {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "UPDATE reimbursement SET approval_id = 103 WHERE employee_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Messages> viewMessages(int sender) {
	
		ArrayList<Messages> viewableMessages = new ArrayList<Messages>();
		Messages infoHolder = new Messages();
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM messages WHERE SENDER = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, sender);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoHolder = new Messages();
				infoHolder.setSender(rs.getInt(1));
				infoHolder.setReciever(rs.getInt(2));
				infoHolder.setMessages(rs.getString(3));
				infoHolder.setMessage_id(rs.getInt(4));
				viewableMessages.add(infoHolder);

				

		
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return viewableMessages;		
	}
}
