package com.sqlbank.dao;

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
			String sql = "SELECT a.account_id, a.balance, account_type.AC_TYPE, status.status, u2.username"
					+ " FROM ACCOUNTS a, users u1, customer_accounts, account_type, users u2,status "
					+ "WHERE u1.USERNAME ='" + c.getUsername()
					+ "' AND U1.USER_ID = Customer_accounts.USER_ID"  
		            + " AND Customer_accounts.ACCOUNT_ID = A.ACCOUNT_ID"
		            + " and account_type.TYPE_ID = a.TYPE_ID"
		            + " and a.resolver = u2.user_id"
		            + " and status.STATUS_ID = a.STATUS_ID";
			Statement ps = connect.createStatement();
			ResultSet rs = ps.executeQuery(sql);
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
		
		return null;
	}

	@Override
	public boolean updateAccount(Account a)
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

}
