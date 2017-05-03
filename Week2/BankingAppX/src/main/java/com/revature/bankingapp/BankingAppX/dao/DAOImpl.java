package com.revature.bankingapp.BankingAppX.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.bankingapp.BankingAppX.account.Account;
import com.revature.bankingapp.BankingAppX.users.User;
import com.revature.bankingapp.BankingAppX.util.ConnectionUtil;

public class DAOImpl implements DAO
{

	@Override
	public int addUser(String firstname, String lastname, String username, String password, int role)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "INSERT INTO USERS (first_name, last_name, username, pw, role_id, adminpin) "
					+ "VALUES (?, ?, ?, ?, ?, null)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, role);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}
	
	@Override
	public int addAdmin(String firstname, String lastname, String username, String password, int role, String apin)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "INSERT INTO USERS (first_name, last_name, username, pw, role_id, adminpin) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setInt(5, role);
			ps.setString(6, apin);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}

	@Override
	public int updateFirstName(User user, String firstname)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String username = user.getUserName();
			String sql = "UPDATE USERS SET first_name = '?' WHERE username = '?'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, username);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}
	
	@Override
	public int updateLastName(User user, String lastname)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String username = user.getUserName();
			String sql = "UPDATE USERS SET last_name = '?' WHERE username = '?'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, lastname);
			ps.setString(2, username);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}
	
	@Override
	public int updateUserName(User user, String username)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String firstname = user.getFirstName();
			String sql = "UPDATE USERS SET username = '?' WHERE first_name = '?'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, firstname);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}

	@Override
	public int updatePassWord(User user, String password)
	{
		int numRows = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String username = user.getUserName();
			String sql = "UPDATE USERS SET password = '?' WHERE username = '?'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}

	@Override
	public User getUserByUsername(String username)
	{
		User temp = new User();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM USERS WHERE username = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			temp = new User();
			while(rs.next())
			{
				temp.setUserID(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUserName(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setRole(rs.getInt(6));
			}
			connect.commit();
			return temp;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public ArrayList<User> getAllUsers()
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM USERS";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			User temp;
			ArrayList<User> tempArray = new ArrayList();
			while(rs.next())
			{
				temp = new User();
				temp.setUserID(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setUserName(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setRole(rs.getInt(6));
				tempArray.add(temp);
			}
			connect.commit();
			return tempArray;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deposit(String username, Double bal)
	{
		int numRows = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM USERS WHERE username = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			while(rs.next())
			{
				user.setUserID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRole(rs.getInt(6));
				user.setAdminPin(rs.getString(7));
			}
			double tempdep = user.getAccountBalance() + bal;
			user.setAccountBalance(tempdep);
			String sql2 = "UPDATE ACCOUNTS SET balance = ? where account_id = ?";
			PreparedStatement ps2 = connect.prepareStatement(sql2);
			ps2.setDouble(1, tempdep);
			ps2.setInt(2, user.getUserID());
			numRows = ps2.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return numRows;
	}

	@Override
	public int withdrawl(String username, Double bal)
	{
		int numRows = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM USERS WHERE username = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			while(rs.next())
			{
				user.setUserID(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setRole(rs.getInt(6));
				user.setAdminPin(rs.getString(7));
			}
			double tempwith = user.getAccountBalance() - bal;
			user.setAccountBalance(tempwith);
			String sql2 = "UPDATE ACCOUNTS SET balance = ? where account_id = ?";
			PreparedStatement ps2 = connect.prepareStatement(sql2);
			ps2.setDouble(1, tempwith);
			ps2.setInt(2, user.getUserID());
			numRows = ps2.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		return numRows;
	}

	@Override
	public int addAccount(User user)
	{
		int numRows = 0;
		int typeid;
		int accountStat;
		if(user.getAccountType().equals("Checking"))
		{
			typeid = 1;
		}
		else
		{
			typeid = 2;
		}
		if(user.getActive() == false)
		{
			accountStat = 1;
		}
		else
		{
			accountStat = 2;
		}
		
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "INSERT INTO ACCOUNTS (type_id, balance, status_id) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, typeid);
			ps.setDouble(2, user.getAccountBalance());
			ps.setInt(3, accountStat);
			numRows = ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		
		return numRows;
	}
	
	public ArrayList<Account> getPending()
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM ACCOUNTS WHERE status_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			Account acct;
			ArrayList<Account> tempArray = new ArrayList();
			while(rs.next())
			{
				acct = new Account();
				acct.setAccountid(rs.getInt(1));
				int temper = rs.getInt(2);
				if(temper == 1)
				{
					acct.setAcctType("Checking");
				}
				else
					acct.setAcctType("Savings");
				acct.setAcctBalance(rs.getDouble(3));
				int strata = rs.getInt(4);
				if(strata == 1)
				{
					acct.setActive(false);
				}
				else if(strata == 2)
				{
					acct.setActive(true);
				}
				else
					acct.setActive(false);
				acct.setResolverID(rs.getInt(5));
				tempArray.add(acct);
			}
			return tempArray;
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		return null;
	}
	
	public void validatePending(User user, Account account)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			String sql = "UPDATE ACCOUNTS SET role_id = 2, resolver_id = ? WHERE account_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			ps.setInt(2, account.getAccountid());
			ps.executeUpdate();
			connect.commit();
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
	}
}
