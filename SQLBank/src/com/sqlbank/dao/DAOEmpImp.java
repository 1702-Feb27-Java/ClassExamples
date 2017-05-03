package com.sqlbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.util.ConnectionUtil;

public class DAOEmpImp extends DAOImplementation implements DAOEmp
{
	@Override
	public ArrayList<Customer> viewMyCustomers(String name)
	{
		
		ArrayList<Customer> users = new ArrayList<Customer>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			Customer cus;
			connect.setAutoCommit(false);
			String sql = "SELECT E2.*, a.account_id, a.balance, at.AC_TYPE, s.STATUS, E1.USERNAME  FROM USERS E1" 
						+ " INNER JOIN accounts a ON"
						+ "	e1.user_id = a.RESOLVER"
						+ " INNER JOIN customer_accounts c ON"
						+ " c.account_id = a.account_id"
						+ " INNER JOIN users e2 ON"
						+ " e2.user_id = c.user_id"
						+ " INNER JOIN account_type at ON"
						+ " at.TYPE_ID = a.TYPE_ID"
						+ "	INNER JOIN status s ON"
						+ " a.status_id = s.status_id"
						+ " WHERE E1.USERNAME = ? ";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				cus = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), "Customer");
				(cus.accounts) = new ArrayList<Account> ();
				cus.accounts.add(new Account (rs.getInt(7), rs.getDouble(8), rs.getString(9), rs.getString(10), rs.getString(11)));
				users.add(cus);
			}
			connect.commit();
			return users;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return users;
	}
	public ArrayList<Customer> getPending()
	{
		ArrayList<Customer> users = new ArrayList<Customer>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			String sql =   "SELECT E2.*, a.account_id, a.balance, at.AC_TYPE, s.STATUS  FROM USERS E2" 
				    		+ " INNER JOIN customer_accounts c ON"
				    		+ " c.user_id = E2.USER_ID"
				    		+ " INNER JOIN accounts a ON"
				    		+ " a.ACCOUNT_ID = c.ACCOUNT_ID"
				    		+ " INNER JOIN account_type at ON"
				    		+ " at.TYPE_ID = a.TYPE_ID"
				    		+ " INNER JOIN status s ON"
				    		+ " a.status_id = s.status_id"
				    		+ " WHERE a.status_id = 1";
			Customer cus;
			connect.setAutoCommit(false);
			Statement ps = connect.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next())
			{
				cus = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), "Customer");
				(cus.accounts) = new ArrayList<Account> ();
				cus.accounts.add(new Account (rs.getInt(7), rs.getDouble(8), rs.getString(9), rs.getString(10), null));
				users.add(cus);
			}
			connect.commit();
			return users;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public boolean updateAccount (int aid, int resolver, int type)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "UPDATE ACCOUNTS SET STATUS_ID = ?, RESOLVER = ? WHERE ACCOUNT_ID= ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, type);
			ps.setInt(2, resolver); 
			ps.setInt(3, aid); 
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

}
