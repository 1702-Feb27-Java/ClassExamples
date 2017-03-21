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
			cs.setInt(6, u.getRoleid());
			cs.setInt(7, u.getDeptid());
			cs.setInt(8, u.getSupid());
			int numRows = cs.executeUpdate();
			connect.commit();
			System.out.println("TEST");
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
			cs.setInt(8, ev.getGradingFormat());
			cs.setInt(9, ev.getEventType());
			cs.setInt(10, ev.getPriority());
			cs.setInt(11, tk.getRoleId());
			cs.setInt(12, userid);  //get the users real id, hard coded
			int numRows = cs.executeUpdate();
			connect.commit();
			System.out.println("TEST");
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
		
			String sql = "SELECT USERID, FIRSTNAME, LASTNAME FROM USERS";
			PreparedStatement ps = connect.prepareStatement(sql);  //statement to verify a users login info
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				ht.put(rs.getInt(1), rs.getString(2)+ " "+ rs.getString(3));
			}
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ht;
	}
}
/**
*********************************************************************************************************
*										END CLASS DAOIMPL
*********************************************************************************************************
*/

