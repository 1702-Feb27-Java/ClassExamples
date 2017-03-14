package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banking.Account;
import com.revature.banking.User;
import com.revature.sql.ConnectionFactory;

public class DAOImplement implements DAO {

	public DAOImplement() {
		// TODO Auto-generated constructor stub
	}
	
	// login method returns true if login info matches
	/**
	 * Returns 0 if no userId found
	 * @param String uName
	 * @param String pw
	 * @return int userId
	 */
	public int login(String uName, String pw) {
		int userId = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL LOGIN(?, ?, ?)";
			CallableStatement ps = c.prepareCall(sql);
			
			ps.setString(1, uName);
			ps.setString(2, pw);
			ps.registerOutParameter(3, java.sql.Types.INTEGER);
			System.out.println("execute login call");
			ps.execute();
			userId = ps.getInt(3);
			System.out.println("userId: " + userId);
			c.commit();
			
		} catch (SQLException e) {
			System.out.println("ERROR IN LOGIN");
			e.printStackTrace();
		}
		
		return userId;
		
	}

	@Override
	public int addAdmin(String fName, String lName, String uName, String pw) {
		// TODO Auto-generated method stub
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL ADD_ADMIN(?, ?, ?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1, fName);
			cs.setString(2, lName);
			cs.setString(3, uName);
			cs.setString(4, pw);
			numRows = cs.executeUpdate();
			
			c.commit();
			
		} catch (SQLException e) {
			
		}
		return numRows;
	}

	@Override
	public int addEmployee(String fName, String lName, String uName, String pw) {
		// TODO Auto-generated method stub
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL ADD_EMPLOYEE(?, ?, ?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1, fName);
			cs.setString(2, lName);
			cs.setString(3, uName);
			cs.setString(4, pw);
			numRows = cs.executeUpdate();
			
			c.commit();
			
		} catch (SQLException e) {
			
		}
		return numRows;
	}

	@Override
	public int addCustomer(String fName, String lName, String uName, String pw) {
		// TODO Auto-generated method stub
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL ADD_CUSTOMER(?, ?, ?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setString(1, fName);
			cs.setString(2, lName);
			cs.setString(3, uName);
			cs.setString(4, pw);
			numRows = cs.executeUpdate();
			
			c.commit();
			
		} catch (SQLException e) {
			System.out.println("ERROR IN ADD_CUSTOMER");
			e.printStackTrace();
		}
		return numRows;
	}
	
	public int getUserId(String uName, String pw){
		int userId = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT USER_ID FROM USERS WHERE USERNAME = ? AND PASS = ?";
			PreparedStatement cs = c.prepareCall(sql);
			cs.setString(1, uName);
			cs.setString(2, pw);
			ResultSet rs = cs.executeQuery();
			rs.next();
			userId = rs.getInt(1);
			
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userId;
	}

	@Override
	public int addAccount(int userId, int typeId, int statusId) {
		// TODO Auto-generated method stub
		// successful here
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection()) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL ADD_ACCOUNT(?, ?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, userId);
			cs.setInt(2, typeId);
			cs.setInt(3, statusId);
			numRows = cs.executeUpdate();
			c.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}
	

	@Override
	public User getUser(int uId) {
		User user = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
			PreparedStatement cs = c.prepareCall(sql);
			cs.setInt(1, uId);
			ResultSet rs = cs.executeQuery();
			user = new User();
			if(rs.next()) {
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRoleId(rs.getInt(6));
			}
		// method to populate account id list
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public Account getAccount(int aId) {
		Account acct = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection()) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			PreparedStatement cs = c.prepareCall(sql);
			cs.setInt(1, aId);
			rs = cs.executeQuery();
			acct = new Account();
			if(rs.next()) {
				acct.setAccountId(rs.getInt(1));
				acct.setTypeId(rs.getInt(2));
				acct.setBalance(rs.getDouble(3));
				acct.setStatusId(rs.getInt(4));
				acct.setResolverId(rs.getInt(5));
			}
			c.commit();
			return acct;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return acct;
	}
	
	public ArrayList<Integer> getEmployeeIds(){
		ArrayList<Integer> employeeIds = new ArrayList<Integer>();
		PreparedStatement cs = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT USER_ID FROM USERS WHERE ROLE_ID = 2";
			cs = c.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next())
				employeeIds.add(rs.getInt(1));
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cs != null) cs.close(); } catch (Exception e) {};
		}
		
		return employeeIds;
	}
	
	public ArrayList<Integer> getCustomerIds(){
		ArrayList<Integer> customerIds = new ArrayList<Integer>();
		PreparedStatement cs = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT USER_ID FROM USERS WHERE ROLE_ID = 3";
			cs = c.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				customerIds.add(rs.getInt(1));
			}
			System.out.println(customerIds);
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cs != null) cs.close(); } catch (Exception e) {};
		}
		
		return customerIds;
	}

	
	public ArrayList<Integer> getAccountIds(int uId){
		ArrayList<Integer> accountIds = new ArrayList<Integer>();
		PreparedStatement cs = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT ACCOUNT_ID FROM CUSTOMERACCOUNTS WHERE USER_ID = ?";
			cs = c.prepareCall(sql);
			cs.setInt(1, uId);
			rs = cs.executeQuery();
			while(rs.next())
				accountIds.add(rs.getInt(1));
			c.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (cs != null) cs.close(); } catch (Exception e) {};
		}
		
		return accountIds;
	}


	
	public double getBalance(int aId) {
		double balance = 0;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT BALANCE FROM ACCOUNTS A WHERE A.ACCOUNT_ID = ?";
			PreparedStatement cs = c.prepareCall(sql);
			cs.setInt(1, aId);
			rs = cs.executeQuery();
			if(rs.next()) {
				balance = rs.getDouble(1);
			}
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}
	@Override
	public int updateBalance(int aId, double bal) {
	// TODO flesh out set balance
	
		double balance = 0;
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "UPDATE ACCOUNTS A SET A.BALANCE = ? WHERE A.ACCOUNT_ID = ?";
			CallableStatement cs = c.prepareCall(sql);
			cs.setDouble(1, balance);
			cs.setInt(2, aId);
			numRows = cs.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}
	
	// in main, withdraw and deposit selection will simply change updatebalance
	
	@Override
	public int updateStatus(int aId, int sId) {
		// TODO Auto-generated method stub
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "UPDATE ACCOUNTS A SET A.STATUS_ID = ? WHERE A.ACCOUNT_ID = ?";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, sId);
			cs.setInt(2, aId);
			numRows = cs.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}

	@Override
	public ArrayList<Integer> getAllPendingAccountIds() {
		ArrayList<Integer> accountIds = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT * FROM ACCOUNTS A WHERE A.STATUS_ID = 1";
			PreparedStatement cs = c.prepareCall(sql);
			rs = cs.executeQuery();
			accountIds = new ArrayList<Integer>();
			while(rs.next()) {
				accountIds.add(rs.getInt(1));
			}
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountIds;
	}

	@Override
	public ArrayList<User> getAllCustomers() {
		ArrayList<User> customers = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT * FROM USERS";
			PreparedStatement cs = c.prepareCall(sql);
			rs = cs.executeQuery();
			customers = new ArrayList<User>();
			User u = new User();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRoleId(rs.getInt(6));
				customers.add(u);
			}
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public ArrayList<User> getAllEmployees() {
		ArrayList<User> employees = null;
		ResultSet rs = null;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "SELECT * FROM USERS";
			PreparedStatement cs = c.prepareCall(sql);
			rs = cs.executeQuery();
			employees = new ArrayList<User>();
			User u = new User();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRoleId(rs.getInt(6));
				employees.add(u);
			}
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public int approve(int uId, int aId) {
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL APPROVE(?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, uId);
			cs.setInt(2, aId);
			numRows = cs.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}

	public int decline(int uId, int aId) {
		int numRows = 0;
		try(Connection c = ConnectionFactory.getConnection();) {
			c.setAutoCommit(false);
			// CallableStatement calls a stored procedure
			// ? basically means variable will be passed
			String sql = "CALL DECLINE(?, ?)";
			CallableStatement cs = c.prepareCall(sql);
			cs.setInt(1, uId);
			cs.setInt(2, aId);
			numRows = cs.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}


}
