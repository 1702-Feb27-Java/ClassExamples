/**
*********************************************************************************************************
* TITLE: MINNCOMM BANKING APPLICATION
* FILENAME: DAOImpl.java
* PROGRAMMER: KEITH MINNER
* 
* PURPOSE: ALLOW A USER TO SIGN UP FOR A BANKING SERVICE TO INCLUDE A CHECKING AND / OR SAVINGS ACCOUNT
* WITH THE CAPABILITIES TO DEPOSIT, WITHDRAW, VIEW AND EDIT PERSONAL INFORMATION.  AN EMPLOYEE CAN
* VIEW CUSTOMER INFORMATION, APPROVE ACCOUNTS, AND EDIT CUSTOMER INFO.  ADDITIONALLY AN ADMIN CAN 
* APPROVE CUSTOMER ACCOUNTS.
*========================================================================================================
*										PROJECT FILES
*
* Customer.java				MenusClassTest.java	
* DAOImpl.java				Person.java			
* Employee.java				PersonClassTest.java			
* EmployeeClassTest.java	UserScreen.java	
* Menus.java				UserScreenTest.java	

*========================================================================================================
*										PACKAGE & IMPORT FILES
*********************************************************************************************************
*/
package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import com.revature.trms.Department;
import com.revature.trms.Event;
import com.revature.trms.EventType;
import com.revature.trms.GradingFormats;
import com.revature.trms.Priority;
import com.revature.trms.Role;
import com.revature.trms.Tracking;
import com.revature.trms.User;
import com.revature.util.FactoryConnection;

/**
********************************************************************************************************
*										CLASS DAOIMPL
*********************************************************************************************************
 */
public class DAOImpl {
	
/**
*********************************************************************************************************
*	@METHOD TO INSERT DATA INTO THE DB
*********************************************************************************************************
*/	
	public static int insertUserData(User u, Role r, Department d) {
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL INSERT_NEW_USER(?, ?, ?, ?, ?, ?, ?, ?)"; //calls procedure from db to enter
			CallableStatement cs = connect.prepareCall(sql);		//user specifics
			cs.setString(1, u.getFirstName());
			cs.setString(2, u.getLastName());
			cs.setString(3, u.getUsername());
			cs.setString(4, u.getPassword());
			cs.setString(5, u.getEmail());
			cs.setInt(6, r.getRoleId());
			cs.setInt(7, d.getDeptId());
			cs.setInt(8, u.getSupId());
			int numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
			return 0;
	}

/**
*********************************************************************************************************
*	@METHOD THAT VERIFYS THE USERNAME FOR UNIQUE
*********************************************************************************************************
*/		
	public static int insertEventData(Event ev, GradingFormats gf, EventType et, 
			Priority p,Tracking tk, int userid){
		
		java.sql.Date start = new java.sql.Date(ev.getStartDate().getTime());
		java.sql.Date stop = new java.sql.Date(ev.getStopDate().getTime());
		System.out.println("Userid2: " + userid);

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL INSERT_NEW_EVENT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);		
			cs.setDate(1, start);
			cs.setString(2, ev.getStartTime());
			cs.setDate(3, stop);
			cs.setString(4, ev.getLocation());
			cs.setString(5, ev.getDescription());
			cs.setDouble(6, ev.getCost());
			cs.setString(7, ev.getJustify());
			cs.setInt(8, gf.getGradeFormatId());
			cs.setInt(9, et.getEventTypeId());
			cs.setInt(10, p.getPriorityId());
			cs.setInt(11, tk.getRoleId());
			cs.setInt(12, userid);  //get the users real id, hard coded
			int numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean verifyInfo(String s) {

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT USERNAME FROM USERS WHERE USERNAME=?"; //prep'd statement to check to		
			PreparedStatement ps = connect.prepareStatement(sql);		//make sure the username is unique
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
						
			while (rs.next()){
				if(s.equals(rs.getString(1)));{
					connect.close();
					return true;	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int loginVerification(String s1, String s2) {
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT USERID, USERNAME, PASSWORD FROM USERS WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);  //statement to verify a users login info
			ps.setString(1, s1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				int userid = rs.getInt(1);
				String uname = rs.getString(2);
				String password = rs.getString(3);
				
				if(s1.equals(uname) && s2.equals(password)){
					return userid;
				}
			}
			connect.close();
			return 0;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Hashtable<Integer, String> getSupervisorId() {
		
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
		
			String sql = "SELECT USERID, FIRSTNAME, LASTNAME FROM USERS WHERE ROLEID!=4";
			PreparedStatement ps = connect.prepareStatement(sql);  //statement to verify a users login info
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				ht.put(rs.getInt(1), rs.getString(3)+ ", " + rs.getString(2));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ht;
	}
	
	public static User getUserDetails(int userid) {
		
		User u = new User();
				
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
		
			String sql = "SELECT * FROM USERS WHERE USERID=?";
			PreparedStatement ps = connect.prepareStatement(sql);  //statement to verify a users login info
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				u.setUserId(rs.getInt(1));
				u.setFirstName(rs.getString(2));
				u.setLastName(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setAmount(rs.getInt(7));
				u.setSupid(rs.getInt(10));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public static ArrayList<Event> getEventStats(int userid) {
		
		ArrayList<Event> list = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT e.EVENTID, e.STARTDATE, e.STARTTIME, e.STOPDATE, e.LOCATION, e.COST FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID WHERE U.USERID=? AND E.ISCLOSED=?"; //calls procedure from db to enter
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ps.setInt(2, 0);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Event>();
						
			while(rs.next()){
				list.add(new Event(rs.getInt(1), rs.getDate(2), rs.getString(3),
						rs.getDate(4),rs.getString(5), rs.getDouble(6)));
			}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}
	
public static ArrayList<EventType> getEventTypes(int userid) {
		
		ArrayList<EventType> list = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT et.EVENTTYPE FROM EVENTTYPES et INNER JOIN EVENTS e ON et.EVENTTYPEID=e.EVENTTYPEID INNER JOIN USEREVENTS uv ON e.EVENTID=uv.EVENTID INNER JOIN USERS u ON uv.USERID=u.USERID WHERE U.USERID=? AND E.ISCLOSED=?"; //calls procedure from db to enter
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ps.setInt(2, 0);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<EventType>();
						
			while(rs.next()){
				list.add(new EventType(rs.getString(1)));
			}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}
}
/**
*********************************************************************************************************
*										END CLASS DAOIMPL
*********************************************************************************************************
*/

