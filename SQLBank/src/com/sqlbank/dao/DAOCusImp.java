package com.sqlbank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.People;
import com.sqlbank.util.ConnectionUtil;

public class DAOCusImp implements DAOCus
{
	@Override
	public ArrayList<Account> viewCustomer(Customer c)
	{
		ArrayList<Account> accounts= new ArrayList<Account>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT a.account_id, a.balance, at.AC_TYPE, s.status, a.resolver "
							+ " FROM ACCOUNTS a"
							+ " INNER JOIN  account_type at ON"
							+ " at.type_id = a.type_id"
							+ " INNER JOIN status s ON"
							+ " s.status_id = a.status_id"
							+ " INNER JOIN customer_accounts cs ON"
							+ " cs.account_id = a.account_id "
							+ " INNER JOIN users u2 ON"
							+ " u2.user_id = cs.user_id"
							+ " WHERE u2.username = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Account a = null;
				if(rs.getInt(5)==0)
				{
					a = new Account(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getString(4),
						"N/A");
					
				}
				else
				{
					sql = "SELECT username FROM USERS WHERE user_id = ?";
					ps = connect.prepareStatement(sql);
					ps.setInt(1, rs.getInt(5));
					ResultSet rs2 = ps.executeQuery();
					while(rs2.next())
					{
						a = new Account(
							rs.getInt(1),
							rs.getDouble(2),
							rs.getString(3),
							rs.getString(4),
							rs2.getString(1));
					}
				}
				accounts.add(a);
			}
			connect.commit();
			return accounts;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}
	@Override
	public boolean updateAccountBalance(Account a)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "UPDATE ACCOUNTS SET BALANCE = ? WHERE ACCOUNT_ID= ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setDouble(1, a.getAmount());
			ps.setInt(2, a.getId());
			int rs = ps.executeUpdate();
			if (rs > 0)
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean applyAccount(Customer c, int type)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			String sql = "{Call cus_acc_proc(?, ?)}";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, c.getId());
			cs.setInt(2, type);
			int rs = cs.executeUpdate();
			if (rs > 0)
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;	
	}
	@Override
	public ArrayList<Account> viewAccounts(Customer c)
	{
		ArrayList<Account> accounts= new ArrayList<Account>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT a.account_id, a.balance, at.AC_TYPE, s.status, u2.username "
							+ " FROM ACCOUNTS a"
							+ " INNER JOIN  account_type at ON"
							+ " at.type_id = a.type_id"
							+ " INNER JOIN status s ON"
							+ " s.status_id = a.status_id"
							+ " INNER JOIN customer_accounts cs ON"
							+ " cs.account_id = a.account_id "
							+ " INNER JOIN users u2 ON"
							+ " u2.user_id = cs.user_id"
							+ " WHERE u2.username = ?"
							+ " AND s.status = 'Approved'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Account a = new Account(
						rs.getInt(1),
						rs.getDouble(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5));
				accounts.add(a);
			}
			connect.commit();
			return accounts;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return accounts;
	}
}
