package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionUtil;

public class CustomerDao {

	/***
	 * 
	 * This method add a customer to the database.
	 * 
	 * @param firstName
	 *            customer's first name
	 * @param lastName
	 *            customer's last name
	 * @param username
	 *            customer's username
	 * @param password
	 *            customer's password
	 * @return numrows returns the number of rows changed.
	 */
	public int addCustomer(String firstName, String lastName, String username, String password) {
		int roleid = 3;
		int numRows = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADDCUSTOMER(?, ?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			cs.setString(3, username);
			cs.setString(4, password);
			cs.setInt(5, roleid);
			numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer account created");
		return numRows;
		
	}

	/***
	 * Method for customer's to login
	 * 
	 * @param username
	 *            the customer's username
	 * @param password
	 *            the customer's password
	 * @return true if the username and password match a userid
	 */
	public int customerLogin(String username, String password) {

		int userid = 0;
		try (Connection c = ConnectionUtil.getConnection();) {
			c.setAutoCommit(false);
			String sql = "SELECT user_id FROM users WHERE username = ? AND password=?";
			PreparedStatement cs = c.prepareStatement(sql);
			cs.setString(1, username);
			cs.setString(2, password);

			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;

	}
	
	public int Login(String username, String password) {

		int userid = 0;
		try (Connection c = ConnectionUtil.getConnection();) {
			c.setAutoCommit(false);
			String sql = "SELECT user_id FROM users WHERE username = ? AND password=?";
			PreparedStatement cs = c.prepareStatement(sql);
			cs.setString(1, username);
			cs.setString(2, password);

			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				userid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;

	}

	/***
	 * This methods allow the user to create an account
	 * 
	 * @param userid
	 *            the user's id
	 * @param typeId
	 *            the account type
	 * 
	 */
	public void openAnAccount(int userid, int typeId) {
		int statusid = 3;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "CALL ADD_ACCOUNT (?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, userid);
			cs.setInt(2, typeId);
			cs.setInt(3, statusid);
			cs.execute();
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Account created!");
	}
/***
 * display the list of user's account
 * @param userid the user's unique id
 */
	public void listOfAccounts(int userid) {
		ResultSet rs;

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = " Select accounts.account_id,account_Type.type, accounts.balance " + "from accounts "
					+ "inner join " + "account_Type on  accounts.type_id=account_Type.TYPE_ID "
					+ "inner join customer_accounts on customer_accounts.account_id= accounts.account_id "
					+ " where user_id=?";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, userid);
			rs = cs.executeQuery();
			while (rs.next()) {

				System.out.printf("Account id: %d, Account Type : %s balance: %f \n", rs.getInt(1), rs.getString(2),
						rs.getDouble(3));

			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void deposit(int accountId, double amount) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "update accounts set balance = ? where account_id = ?";
			CallableStatement ps = connect.prepareCall(sql);

			double total = getBalance(accountId);
			total += amount;
			ps.setDouble(1, total);
			ps.setInt(2, accountId);

			ps.executeUpdate();

			connect.commit();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}
		
		System.out.println("your current balance is:"+ getBalance(accountId));

	}
	
	/***
	 * 
	 * @param accountId
	 * @return
	 */
	
	public double getBalance(int accountId) {
		double balance = 0;

		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "call GETBALANCE(?,?)";
			CallableStatement cs = connect.prepareCall(sql);

			cs.registerOutParameter(1, java.sql.Types.DOUBLE);
			cs.setInt(2, accountId);
			cs.execute();

			balance = cs.getDouble(1);

			connect.commit();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());

		}
		return balance;
	}

	
	
	
	public void withdraw(int accountId, double amount) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = " update accounts set balance=? where account_id=?";
			CallableStatement ps = connect.prepareCall(sql);

			double total = getBalance(accountId);
			total -= amount;
			ps.setDouble(1, total);
			ps.setInt(2, accountId);

			ps.executeUpdate();

			connect.getAutoCommit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("your current balance is:"+ getBalance(accountId));
	}
	
	public int getRoleid(int userid){
		int roleid = 0;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = " select users.role_id from users where user_id=?";
			CallableStatement ps = connect.prepareCall(sql);
			ps.setDouble(1, userid);
			ps.executeQuery();
			
			roleid=ps.getInt(2);
			connect.getAutoCommit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleid;
		
		
	}

}
