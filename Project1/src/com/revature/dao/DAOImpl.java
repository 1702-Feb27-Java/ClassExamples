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
import java.util.ArrayList;
import java.util.Hashtable;
import com.revature.trms.Event;
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
	public static int insertUserData(User u) {
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL INSERT_NEW_USER(?, ?, ?, ?, ?, ?, ?, ?)"; //calls procedure from db to enter
			CallableStatement cs = connect.prepareCall(sql);		//user specifics
			cs.setString(1, u.getFirstName());
			cs.setString(2, u.getLastName());
			cs.setString(3, u.getUsername());
			cs.setString(4, u.getPassword());
			cs.setString(5, u.getEmail());
			cs.setString(6, u.getRole());
			cs.setString(7, u.getDept());
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
	public static int insertEventData(Event ev, Tracking tk, int userid){
		
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
			cs.setString(8, ev.getGradeFormat());
			cs.setString(9, ev.getEventType());
			cs.setString(10, ev.getPriority());
			cs.setString(11, tk.getRoleId());
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
				u.setSupId(rs.getInt(10));
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
			String sql = "SELECT e.EVENTID, e.STARTDATE, e.STARTTIME, e.STOPDATE, e.LOCATION, e.DESCRIPTION, e.COST, e.JUSTIFICATION, gf.GFORMAT, et.EVENTTYPE, p.PRIORITY FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID INNER JOIN GRADINGFORMATS gf ON e.GFORMATID=gf.GFORMATID INNER JOIN EVENTTYPES et ON e.EVENTTYPEID=et.EVENTTYPEID INNER JOIN PRIORITYS p ON e.PRIORITYID=p.PRIORITYID WHERE U.USERID=? AND E.ISCLOSED=?"; //calls procedure from db to enter
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ps.setInt(2, 0);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Event>();
						
			while(rs.next()){
				list.add(new Event(rs.getInt(1), rs.getDate(2), rs.getString(3),
						rs.getDate(4),rs.getString(5), rs.getString(6),rs.getDouble(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
						rs.getInt(12)));
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

