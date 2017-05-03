package com.rev.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.rev.bankapp.connector.DBConnector;;

public class BankDAO {
	private static int accountCounter = 1;

	// creates a new user row
	public static void createNewUser(String fname, String lname, String username, String password, int roleID) {

		try (Connection c = DBConnector.getConnection()) {
			String sql = "INSERT INTO USERS(FIRST_NAME, LAST_NAME, USERNAME, PW, ROLE_ID) VALUES(?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, roleID);

			ps.executeQuery();

		} catch (Exception e) {
			System.out.println("INSERT USER FAILED " + e);
		}

	}

	// deletes user
	public static void deleteUser(int userID) {

		try (Connection c = DBConnector.getConnection()) {

			String sql = "DELETE FROM USERS WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.executeQuery();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// returns a users full information
	public static String getUserString(int userID) {

		String user = "";
		try (Connection c = DBConnector.getConnection()) {

			String sql = "select u.user_id, u.first_name, u.last_name, u.username, "
					+ "u.pw, r.role from users u left join role r on u.role_id = r.role_id where u.user_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user += "User ID: " + rs.getString(1) + " || ";
				user += "First name: " + rs.getString(2) + " || ";
				user += "Last name: " + rs.getString(3) + " || ";
				user += "Username: " + rs.getString(4) + " || ";
				user += "Password: " + rs.getString(5) + " || ";
				user += "Account type: " + rs.getString(6);
			}

		} catch (Exception e) {
			System.out.println("Get user string failed " + e);
		}

		return user;
	}

	public static void requestAccount(int userID, int accountTypeID) {

		try (Connection c = DBConnector.getConnection()) {
			c.setAutoCommit(false);
			String sql = "INSERT INTO ACCOUNTS(TYPE_ID, BALANCE, STATUS_ID) VALUES(?,0,1)";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, accountTypeID);
			pc.executeQuery();

			sql = "INSERT INTO USER_ACCOUNTS(USER_ID) VALUES(?)";
			pc = c.prepareStatement(sql);
			pc.setInt(1, userID);
			pc.executeQuery();

			c.commit();
			c.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkUsername(String username) {

		boolean userExists = false;
		try (Connection c = DBConnector.getConnection()) {

			String sql = "SELECT COUNT(USERS.USERNAME) FROM USERS WHERE USERS.USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			username = username.toLowerCase();
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = Integer.parseInt(rs.getString(1));
			}
			if (count > 0) {
				userExists = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return userExists;
	}

	public static ArrayList<Integer> getListOfPendingAccounts() {

		ArrayList<Integer> pendingAccounts = new ArrayList<Integer>();
		try (Connection c = DBConnector.getConnection()) {

			String sql = "SELECT * FROM ACCOUNTS WHERE STATUS_ID = 1";
			PreparedStatement pc = c.prepareStatement(sql);
			ResultSet rs = pc.executeQuery();

			while (rs.next()) {
				pendingAccounts.add(rs.getInt(1));
			}

		} catch (Exception e) {
			System.out.println("PENDING ACCOUNTS ERROR" + e);
		}

		return pendingAccounts;
	}

	// stores all user_id into an array for manipulation

	public static ArrayList<Integer> getAllUsers() {
		ArrayList<Integer> allUsers = new ArrayList<Integer>();

		try (Connection c = DBConnector.getConnection()) {
			String sql = "Select user_id from users";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				allUsers.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("GET ALL USERS ERROR " + e);
		}

		return allUsers;
	}

	// RETURNS AN ARRAY OF ALL USERS ACCOUNT

	public static ArrayList<Integer> getListOfAllAccounts(int userID) {

		ArrayList<Integer> allAccounts = new ArrayList<Integer>();
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT ACCOUNT_ID FROM USER_ACCOUNTS WHERE USER_ID = ?";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, userID);
			ResultSet rs = pc.executeQuery();

			while (rs.next()) {
				allAccounts.add(rs.getInt(1));
			}

		} catch (Exception e) {
			System.out.println("GET ACCOUNTS LIST ERROR " + e);
		}

		return allAccounts;
	}

	public static boolean checkApproved(int accountID) {

		boolean isApproved = false;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT STATUS_ID FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, accountID);
			ResultSet rs = pc.executeQuery();
			int pendStat = 0;

			while (rs.next()) {
				pendStat = rs.getInt(1);
			}
			if (pendStat == 2) {
				isApproved = true;
			}
		} catch (SQLException e) {
			System.out.println("check pending error " + e);
		}

		return isApproved;
	}

	public static boolean checkPending(int accountID) {

		boolean isPending = false;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT STATUS_ID FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, accountID);
			ResultSet rs = pc.executeQuery();
			int pendStat = 0;

			while (rs.next()) {
				pendStat = rs.getInt(1);
			}
			if (pendStat == 1) {
				isPending = true;
			}
		} catch (SQLException e) {
			System.out.println("check pending error " + e);
		}

		return isPending;
	}
/*	public static boolean checkApproved(int accountID) {

		boolean isApproved = false;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT STATUS_ID FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement pc = c.prepareStatement(sql);
			pc.setInt(1, accountID);
			ResultSet rs = pc.executeQuery();
			int apprStat = 0;

			while (rs.next()) {
				apprStat = rs.getInt(1);
			}
			if (apprStat == 2) {
				isApproved = true;
			}
		} catch (SQLException e) {
			System.out.println("check approved error " + e);
		}

		return isApproved;
	}*/

	// GETS USERNAME FOR THE USE OF LISTING USERNAMES OR MATCHING USERNAMES TO A
	// USER ID
	public static String getUsername(int userID) {
		String username = "";
		try (Connection c = DBConnector.getConnection()) {

			String sql = "SELECT USERNAME FROM USERS WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				username = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("GETTING USERNAME ERROR " + e);
		}

		return username;
	}

	public static String getPassword(String username) {
		String password = "";
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT PW FROM USERS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				password = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("GET PASSWORD ERROR" + e);
		}
		return password;
	}

	public static int getAccountType(int accountID) {
		int accountType = 0;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT TYPE_ID FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				accountType = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("GET ACCOUNT TYPE ERROR " + e);
		}

		return accountType;
	}

	public static int getAccountBalance(int accountID) {
		int balance = 0;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				balance = rs.getInt(1);
			}
		} catch (Exception e) {

		}
		return balance;
	}

	public static void setBalance(int accountID, int balance) {
		try (Connection c = DBConnector.getConnection()) {

			String sql = "UPDATE ACCOUNTS SET BALANCE = ? WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, balance);
			ps.setInt(2, accountID);
			ps.executeQuery();

		} catch (Exception e) {
			System.out.println("set balance error" + e);
		}
	}

	public static int getUserRole(int userID) {
		int userRole = 0;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT ROLE_ID FROM USERS WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				userRole = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("GET USER ROLE EXEPTION " + e);
		}
		return userRole;
	}

	public static int getUserID(String username) {
		int userID = 0;
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT USER_ID FROM USERS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				userID = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("GET UESR ID ERROR " + e);
		}

		return userID;
	}

	public static ArrayList<Integer> getAllPendingAccounts() {
		ArrayList<Integer> allPending = new ArrayList<Integer>();
		try (Connection c = DBConnector.getConnection()) {
			String sql = "SELECT ACCOUNT_ID FROM ACCOUNTS WHERE STATUS_ID = 1";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allPending.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("GET ALL PENDING ACCOUNTS ERROR " + e);
		}
		return allPending;
	}

	public static String getPendingAcconutString(int accountID) {
		String pendingString = "";
		try (Connection c = DBConnector.getConnection()) {
			String sql = "select u.user_id, u.username, ua.account_id, at.atype "
					+ "from users u "
					+ "inner join user_accounts ua "
					+ "on ua.USER_ID = u.USER_ID "
					+ "inner join accounts a "
					+ "on a.account_id = ua.ACCOUNT_ID "
					+ "inner join account_type at "
					+ "on a.type_id = at.type_id "
					+ "where a.account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				pendingString += "User ID: " + rs.getString(1) + " || ";
				pendingString += "Username: " + rs.getString(2) + " || ";
				pendingString += "Account ID: " + rs.getString(3) + " || ";
				pendingString += "Account Type: " + rs.getString(4);
			}

		} catch (SQLException e) {
			System.out.println("PENDING ACCOunT STRING ERROR " + e);
		}
		return pendingString;
	}
	
	public static void editAccountPending(int accountID, int statusID, int userID){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE ACCOUNTS SET STATUS_ID = ?, RESOLVER = ? WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusID);
			ps.setInt(2, userID);
			ps.setInt(3, accountID);
			ps.executeQuery();
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit pending account eeror " + e);
		}
	}
	
	public static void editUserFname(int userID, String fname){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE USERS SET FIRST_NAME = ? WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setInt(2, userID);
			ps.executeQuery();
			
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit fname account eeror " + e);
		}
		
	}
	public static void editUserLname(int userID, String lname){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE USERS SET LAST_NAME = ? WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, lname);
			ps.setInt(2, userID);
			ps.executeQuery();
			
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit lname account eeror " + e);
		}
		
	}
	public static void editUserUsername(int userID, String username){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE USERS SET USERNAME = ? WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, userID);
			ps.executeQuery();
			
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit uname account eeror " + e);
		}
		
	}
	public static void editUserPassword(int userID, String password){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE USERS SET PW = ? WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, userID);
			ps.executeQuery();
			
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit pw account eeror " + e);
		}
		
	}
	public static void editUserRole(int userID, int role){
		try(Connection c = DBConnector.getConnection()){
			
			String sql = "UPDATE USERS SET ROLE_ID = ? WHERE USER_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, role);
			ps.setInt(2, userID);
			ps.executeQuery();
			
			System.out.println("Account updated!");
			
		}catch(SQLException e){
			System.out.println("edit ROLEID account eeror " + e);
		}
		
	}
	public static void logEvent(String event){
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
		Date d = new Date();
		String date = df.format(d);
		try(Connection c = DBConnector.getConnection()){
			String sql = "INSERT INTO LOGS VALUES(?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, event);
			ps.executeQuery();
			
		}catch(SQLException e){
			System.out.println("logging error " + e);
		}
		
	}


}
