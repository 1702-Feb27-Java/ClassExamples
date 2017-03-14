package com.sqlbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sqlbank.bankaccount.Account;
import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;
import com.sqlbank.util.ConnectionUtil;

public class DAOAdminImp extends DAOImplementation implements DAOAdmin
{
//	@Override
//	public Customer viewAccount(Customer cus)
//	{
//		
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ArrayList<People> viewAccounts()
	{
		ArrayList<People> users = new ArrayList<People>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			People person;
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM USERS";
			Statement ps = connect.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next())
			{
				String role="";
				String role_sql = "SELECT ROLE FROM ROLE WHERE ROLE_ID =" + rs.getInt(6);
				Statement sqls = connect.createStatement();
				ResultSet nrs = sqls.executeQuery(role_sql);
				if(nrs.next())
				{
					role = nrs.getString(1);
				}
				
				person = new People(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), role);
				users.add(person);
			}
			connect.commit();
			return users;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin editAccount(Admin ad)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee editAccount(Employee em)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer editAccount(Customer cus)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
