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
import com.revature.trms.EventService;
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
	public static int editUserData(User u) {
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql1 = "SELECT ROLEID FROM ROLES WHERE ROLE=?"; 
			PreparedStatement ps1 = connect.prepareStatement(sql1);		
			ps1.setString(1, u.getRole());
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			String role = rs1.getString(1);
			System.out.println(role);
			
			String sql2 = "SELECT DEPTID FROM DEPARTMENTS WHERE DEPARTMENT=?"; 
			PreparedStatement ps2 = connect.prepareStatement(sql2);		
			ps2.setString(1, u.getDept());
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String dept = rs2.getString(1);
			System.out.println(dept);	
			System.out.println(u.getSupId());
			String sql = "CALL EDIT_USER(?, ?, ?, ?, ?, ?, ?, ?)"; //calls procedure from db to enter
			CallableStatement cs = connect.prepareCall(sql);		//user specifics
			cs.setString(1, u.getFirstName());
			cs.setString(2, u.getLastName());
			cs.setString(3, u.getUsername());
			cs.setString(4, u.getPassword());
			cs.setString(5, u.getEmail());
			cs.setString(6, role);
			cs.setString(7, dept);
			cs.setInt(8, u.getSupId());
			int numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
			return 0;
	}
	
	public static int insertEventData(Event ev, String roleId, int userid){
		
		java.sql.Date start = new java.sql.Date(ev.getStartDate().getTime());
		java.sql.Date stop = new java.sql.Date(ev.getStopDate().getTime());
 
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
			cs.setString(11, roleId);
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
				u.setRole(rs.getString(8));
				u.setDept(rs.getString(9));				
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
			String sql = "SELECT DISTINCT s.STATUS, e.EVENTID, e.STARTDATE, e.STARTTIME, e.STOPDATE, e.LOCATION, e.DESCRIPTION, e.COST, e.JUSTIFICATION, gf.GFORMAT, et.EVENTTYPE,p.PRIORITY, r.ROLE, U1.FIRSTNAME, U1.LASTNAME FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID INNER JOIN GRADINGFORMATS gf ON e.GFORMATID=gf.GFORMATID INNER JOIN EVENTTYPES et ON e.EVENTTYPEID=et.EVENTTYPEID INNER JOIN EVENTTRACKING ek ON e.EVENTID=ek.EVENTID INNER JOIN TRACKING t ON ek.TRACKINGID=t.TRACKINGID INNER JOIN PRIORITYS p ON e.PRIORITYID=p.PRIORITYID INNER JOIN STATUS s ON t.STATUSID=s.STATUSID INNER JOIN ROLES r ON t.ROLEID=r.ROLEID INNER JOIN USERS U1 ON U1.USERID=T.SUPID WHERE U.USERID=? AND ISCLOSED=0 AND (T.STATUSID='1' OR T.STATUSID='2' OR T.STATUSID='4') ORDER BY EVENTID";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Event>();
			while(rs.next()){
				list.add(new Event(rs.getInt(2), rs.getDate(3), rs.getString(4),
						rs.getDate(5),rs.getString(6), rs.getString(7),rs.getDouble(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<Tracking> getTrackingStats(int userid) {
		
		ArrayList<Tracking> tList = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT DISTINCT s.STATUS, e.EVENTID, e.STARTDATE, e.STARTTIME, e.STOPDATE, e.LOCATION, e.DESCRIPTION, e.COST, e.JUSTIFICATION, gf.GFORMAT, et.EVENTTYPE,p.PRIORITY, r.ROLE, U1.FIRSTNAME, U1.LASTNAME FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID INNER JOIN GRADINGFORMATS gf ON e.GFORMATID=gf.GFORMATID INNER JOIN EVENTTYPES et ON e.EVENTTYPEID=et.EVENTTYPEID INNER JOIN EVENTTRACKING ek ON e.EVENTID=ek.EVENTID INNER JOIN TRACKING t ON ek.TRACKINGID=t.TRACKINGID INNER JOIN PRIORITYS p ON e.PRIORITYID=p.PRIORITYID INNER JOIN STATUS s ON t.STATUSID=s.STATUSID INNER JOIN ROLES r ON t.ROLEID=r.ROLEID INNER JOIN USERS U1 ON U1.USERID=T.SUPID WHERE U.USERID=? AND ISCLOSED=0 AND (T.STATUSID='1' OR T.STATUSID='2' OR T.STATUSID='4') ORDER BY EVENTID";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			tList = new ArrayList<Tracking>();
						
			while(rs.next()){
				tList.add(new Tracking(rs.getString(1)));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return tList;
	}
	
	public static ArrayList<Tracking> getTrackingDetails(int eventId) {
		
		ArrayList<Tracking> sList = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT T.TRACKINGID, T.EVENTDATE, R.ROLE, S.STATUS, U1.FIRSTNAME, U1.LASTNAME, T.COMMENTS FROM TRACKING T INNER JOIN EVENTTRACKING ET ON ET.TRACKINGID=T.TRACKINGID INNER JOIN EVENTS E ON E.EVENTID=ET.EVENTID INNER JOIN ROLES R ON T.ROLEID=R.ROLEID INNER JOIN STATUS S ON S.STATUSID=T.STATUSID INNER JOIN USERS U1 ON U1.USERID=T.SUPID WHERE E.EVENTID=? ORDER BY T.TRACKINGID DESC";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();
			sList = new ArrayList<Tracking>();
						
			while(rs.next()){
				sList.add(new Tracking(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7)));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return sList;
	}
	
public static ArrayList<User> getTrackingDetails2(int eventId) {
		
		ArrayList<User> sList = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT T.TRACKINGID, T.EVENTDATE, R.ROLE, S.STATUS, U1.FIRSTNAME, U1.LASTNAME, T.COMMENTS FROM TRACKING T INNER JOIN EVENTTRACKING ET ON ET.TRACKINGID=T.TRACKINGID INNER JOIN EVENTS E ON E.EVENTID=ET.EVENTID INNER JOIN ROLES R ON T.ROLEID=R.ROLEID INNER JOIN STATUS S ON S.STATUSID=T.STATUSID INNER JOIN USERS U1 ON U1.USERID=T.SUPID WHERE E.EVENTID=? ORDER BY T.TRACKINGID DESC";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();
			sList = new ArrayList<User>();
						
			while(rs.next()){
				sList.add(new User(rs.getString(5) + " " + rs.getString(6)));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return sList;
	}
	
	public static ArrayList<String> getSupervisorStats(int userid) {
		
		ArrayList<String> sList = null;
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT DISTINCT s.STATUS, e.EVENTID, e.STARTDATE, e.STARTTIME, e.STOPDATE, e.LOCATION, e.DESCRIPTION, e.COST, e.JUSTIFICATION, gf.GFORMAT, et.EVENTTYPE,p.PRIORITY, r.ROLE, U1.FIRSTNAME, U1.LASTNAME FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID INNER JOIN GRADINGFORMATS gf ON e.GFORMATID=gf.GFORMATID INNER JOIN EVENTTYPES et ON e.EVENTTYPEID=et.EVENTTYPEID INNER JOIN EVENTTRACKING ek ON e.EVENTID=ek.EVENTID INNER JOIN TRACKING t ON ek.TRACKINGID=t.TRACKINGID INNER JOIN PRIORITYS p ON e.PRIORITYID=p.PRIORITYID INNER JOIN STATUS s ON t.STATUSID=s.STATUSID INNER JOIN ROLES r ON t.ROLEID=r.ROLEID INNER JOIN USERS U1 ON U1.USERID=T.SUPID WHERE U.USERID=? AND ISCLOSED=0 AND (T.STATUSID='1' OR T.STATUSID='2' OR T.STATUSID='4') ORDER BY EVENTID";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);			
			ResultSet rs = ps.executeQuery();
			sList = new ArrayList<String>();
						
			while(rs.next()){
				sList.add(rs.getString(13)+ ", " + rs.getString(14)+ " " + rs.getString(15));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return sList;
	}


	public static ArrayList<Event> getEventNumbers(int userid) {
	
	ArrayList<Event> list = null;
	
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT e.EVENTID FROM EVENTS e INNER JOIN USEREVENTS ue ON e.EVENTID=ue.EVENTID INNER JOIN USERS u ON ue.USERID=u.USERID WHERE ISCLOSED=0 AND U.USERID=? ORDER BY EVENTID";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Event>();
			while(rs.next()){
				list.add(new Event(rs.getInt(1)));
				}
			connect.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}
	
	public static int deleteEventData(int userId, int eventId, double cost){

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL DELETE_EVENT(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);		
			cs.setInt(1, userId);
			cs.setInt(2, eventId);
			cs.setDouble(3, cost);
			int numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return 0;
	}
	
	public static ArrayList<Event> getPendingRequests(int userid) {
		
		ArrayList<Event> list = null;
		
			try (Connection connect = FactoryConnection.getConnection();){
				connect.setAutoCommit(false);
				String sql = "SELECT ET.EVENTID, U.FIRSTNAME, U.LASTNAME, ET.EVENTTYPE, E.COST, E.STARTDATE, E.STOPDATE, P.PRIORITY, E.STARTTIME, E.LOCATION, E.DESCRIPTION, E.JUSTIFICATION, G.GFORMAT FROM TRACKING T INNER JOIN EVENTTRACKING ET ON T.TRACKINGID=ET.TRACKINGID INNER JOIN EVENTS E ON ET.EVENTID=E.EVENTID INNER JOIN USEREVENTS UE ON E.EVENTID=UE.EVENTID INNER JOIN USERS U ON UE.USERID=U.USERID INNER JOIN EVENTTYPES ET ON E.EVENTTYPEID=ET.EVENTTYPEID INNER JOIN PRIORITYS P ON E.PRIORITYID=P.PRIORITYID INNER JOIN GRADINGFORMATS G ON E.GFORMATID=G.GFORMATID WHERE T.SUPID=? AND T.STATUSID=1";
				PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
				ps.setInt(1, userid);
				ResultSet rs = ps.executeQuery();
				list = new ArrayList<Event>();
				while(rs.next()){
					list.add(new Event(rs.getInt(1),rs.getDate(6), rs.getString(9), rs.getDate(7), rs.getString(10), rs.getString(11), rs.getDouble(5), rs.getString(12),rs.getString(13), rs.getString(4),rs.getString(8)));
					}
				connect.commit();
			} catch (SQLException e) {
					e.printStackTrace();
			}
			return list;
		}
	
	public static ArrayList<User> getPendingRequests2(int userid) {
		
		ArrayList<User> list = null;
		
			try (Connection connect = FactoryConnection.getConnection();){
				connect.setAutoCommit(false);
				String sql = "SELECT ET.EVENTID, U.FIRSTNAME, U.LASTNAME, ET.EVENTTYPE, E.COST, E.STARTDATE, E.STOPDATE, P.PRIORITY, E.STARTTIME, E.LOCATION,E.DESCRIPTION, E.JUSTIFICATION, G.GFORMAT FROM TRACKING T INNER JOIN EVENTTRACKING ET ON T.TRACKINGID=ET.TRACKINGID INNER JOIN EVENTS E ON ET.EVENTID=E.EVENTID INNER JOIN USEREVENTS UE ON E.EVENTID=UE.EVENTID INNER JOIN USERS U ON UE.USERID=U.USERID INNER JOIN EVENTTYPES ET ON E.EVENTTYPEID=ET.EVENTTYPEID INNER JOIN PRIORITYS P ON E.PRIORITYID=P.PRIORITYID INNER JOIN GRADINGFORMATS G ON E.GFORMATID=G.GFORMATID WHERE T.SUPID=? AND T.STATUSID=1";
				PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
				ps.setInt(1, userid);
				ResultSet rs = ps.executeQuery();
				list = new ArrayList<User>();
				while(rs.next()){
					list.add(new User(rs.getString(2) + " " + rs.getString(3)));
					}
				connect.commit();
			} catch (SQLException e) {
					e.printStackTrace();
			}
			return list;
	}

	
	public static void approveEvent(int eventId, String comments){
		
	try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT U.USERID, T.TRACKINGID, T.ROLEID, DEPTID FROM EVENTS E INNER JOIN EVENTTRACKING ET ON E.EVENTID=ET.EVENTID INNER JOIN TRACKING T ON ET.TRACKINGID=T.TRACKINGID INNER JOIN USEREVENTS UE ON E.EVENTID=UE.EVENTID INNER JOIN USERS U ON UE.USERID=U.USERID WHERE T.STATUSID=1 AND E.EVENTID=?";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();
			
			int userId = 0;
			int trackingId = 0;
			String roleId = null;
			String deptId = null;
			
			while (rs.next()){
				userId = rs.getInt(1);
				trackingId = rs.getInt(2);
				roleId = rs.getString(3);
				deptId = rs.getString(4);
			}
			
			roleId = EventService.approveEvent(roleId);
			
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			String supId = null;
			
			if (!roleId.equals("0")){			
				if (roleId.equals("3")){
					String sql1 = "SELECT U.SUPID FROM USERS U WHERE USERID=?";
					ps1 = connect.prepareStatement(sql1);		//user specifics
					ps1.setInt(1, userId);
				}
				else if (roleId.equals("2")){
					String sql1 = "SELECT U.USERID FROM USERS U WHERE DEPTID=? AND ROLEID=2";
					ps1 = connect.prepareStatement(sql1);		//user specifics
					ps1.setString(1, deptId);
				}
				else if (roleId.equals("1")){
					String sql1 = "SELECT U.USERID FROM USERS U WHERE DEPTID=3 AND ROLEID=1";
					ps1 = connect.prepareStatement(sql1);		//user specifics
				}
				rs1 = ps1.executeQuery();
				rs1.next();
				supId = rs1.getString(1);
			}
			
			CallableStatement cs = null;
			
			if (!roleId.equals("0")){
				String sql2="CALL APPROVE(?, ?, ?, ?, ?)";
				cs = connect.prepareCall(sql2);
				cs.setInt(1,  eventId);
				cs.setInt(2,  trackingId);
				cs.setString(3,  roleId);
				cs.setString(4,  supId);
				cs.setString(5,  comments);
			} else {
				String sql1 = "CALL AWTRESULTS(?, ?)";
				cs = connect.prepareCall(sql1);
				cs.setInt(1, trackingId);
				cs.setString(2, comments);
				}
			cs.executeQuery();
			connect.commit();
			connect.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void denyEvent(int eventId, String comments){

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT U.USERID, T.TRACKINGID FROM EVENTS E INNER JOIN EVENTTRACKING ET ON E.EVENTID=ET.EVENTID INNER JOIN TRACKING T ON ET.TRACKINGID=T.TRACKINGID INNER JOIN USEREVENTS UE ON E.EVENTID=UE.EVENTID INNER JOIN USERS U ON UE.USERID=U.USERID WHERE T.STATUSID=1 AND E.EVENTID=?";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, eventId);
			ResultSet rs = ps.executeQuery();
			
			int userId = 0;
			int trackingId = 0;
						
			while (rs.next()){
				userId = rs.getInt(1);
				trackingId = rs.getInt(2);
			}	
			
			String sql2="CALL DENY(?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql2);
			cs.setInt(1,  eventId);
			cs.setInt(2,  userId);
			cs.setString(3,  comments);
			cs.setInt(4,  trackingId);
			cs.executeQuery();
			connect.commit();
			connect.close();
		} catch (SQLException e) {
					e.printStackTrace();
		}
	}
	
	public static void editEvents(Event e){
		System.out.println(e);

		java.sql.Date start = new java.sql.Date(e.getStartDate().getTime());
		java.sql.Date stop = new java.sql.Date(e.getStopDate().getTime());
		
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "SELECT T.TRACKINGID, U.SUPID FROM EVENTS E INNER JOIN EVENTTRACKING ET ON E.EVENTID=ET.EVENTID INNER JOIN TRACKING T ON ET.TRACKINGID=T.TRACKINGID INNER JOIN USEREVENTS UE ON E.EVENTID=UE.EVENTID INNER JOIN USERS U ON UE.USERID=U.USERID WHERE T.STATUSID=1 OR T.STATUSID=2 OR T.STATUSID=4 AND E.EVENTID=?";
			PreparedStatement ps = connect.prepareStatement(sql);		//user specifics
			ps.setInt(1, e.getEventId());
			ResultSet rs = ps.executeQuery();
						
			int trackingId = 0;
			int supId = 0;
						
			while (rs.next()){
				trackingId = rs.getInt(1);
				supId = rs.getInt(2);
			}	

			String sql1 = "SELECT T.TRACKINGID FROM EVENTS E INNER JOIN EVENTTRACKING ET ON E.EVENTID=ET.EVENTID INNER JOIN TRACKING T ON ET.TRACKINGID=T.TRACKINGID WHERE T.ROLEID=3 AND E.EVENTID=?";
			PreparedStatement ps1 = connect.prepareStatement(sql1);		
			ps1.setInt(1, e.getEventId());
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			int trackingId2 = rs1.getInt(1);
			
			String type = EventService.getEventId(e.getEventType());
			String pri = EventService.getPriorityId(e.getPriority());
			String grade = EventService.getGradeId(e.getGradeFormat());
			
			String sql2 = "CALL EDIT_EVENT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql2);		
			cs.setInt(1, e.getEventId());
			cs.setDate(2, start);
			cs.setString(3, e.getStartTime());
			cs.setDate(4, stop);
			cs.setString(5, e.getLocation());
			cs.setString(6, e.getDescription());
			cs.setDouble(7, e.getCost());
			cs.setString(8, e.getJustify());
			cs.setString(9, grade);
			cs.setString(10, type);
			cs.setString(11, pri);
			cs.setInt(12, trackingId);
			cs.setInt(13, supId);
			cs.setInt(14, trackingId2);
			cs.executeUpdate();
			connect.commit();
		} catch (SQLException e1) {
				e1.printStackTrace();
		}

	}
}
/**
*********************************************************************************************************
*										END CLASS DAOIMPL
*********************************************************************************************************
*/
