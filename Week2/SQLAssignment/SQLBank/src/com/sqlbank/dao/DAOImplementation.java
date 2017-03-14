package com.sqlbank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.sqlbank.peoplepack.Admin;
import com.sqlbank.peoplepack.Customer;
import com.sqlbank.peoplepack.Employee;
import com.sqlbank.peoplepack.People;
import com.sqlbank.util.ConnectionUtil;

public class DAOImplementation implements DAO
{

	//====================================== ADDS USER TO DATABASE (CUSTOMER ONLY) ====================================================
	/**
	 * @param uname : username from new customer
	 * @param pwrod : password from new customer
	 * @return int : rows affected
	 */
	@Override
	public boolean addUser(String name, String uname, String pword)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//INSERT INTO USERS
			String sql_count = "INSERT INTO USERS (first_name, username, pw, role_id) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, name);
			ps.setString(2, uname);
			ps.setString(3, pword);
			ps.setInt(4, 3);
			int rs = ps.executeUpdate();
			
			if(rs == 1)
			{
				String sql = "{Call add_account()}";
				CallableStatement cs = connect.prepareCall(sql);
				cs.executeUpdate();
				connect.commit();
				return true;
			}
		
		}
		catch(SQLIntegrityConstraintViolationException e )
		{
			e.printStackTrace();
			System.out.println("USER ALREADY EXISTS");
			return false;
		}
		catch (SQLException e)
		{
			System.out.println("ERORRORRO");
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	//====================================== CHECK TO SEE IF VALID USER ====================================================
	/**RETURNS A NUMBER 
	 * @param uname :username to check
	 * @param pword :password to check
	 *@return int
	 * 
	 *0 FOR NO RESULT
	 *1 FOR ADMIN
	 *2 FOR EMPLOYE
	 *3 FOR CUSTOMER
	 */
	@Override
	public int checkUser(String uname, String pword)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "SELECT COUNT (*) FROM USERS WHERE USERNAME = ? AND PW = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ps.setString(2, pword);
			ResultSet rs = ps.executeQuery();
			int res_count = 0;
			
			while(rs.next())	
			{
				res_count =rs.getInt(1);
			}
			
			//IF COUNT IS 0 THEN DOESNT EXIST
			if (res_count == 0)
			{
				return 0;
			}
			//ERROR IF USER COUNT DOES NOT EQUAL 1
			else if (res_count > 1)
				return -1;
			
			return res_count;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}
	
	//====================================== CHECK TO SEE IF VALID USERNAME ====================================================
	/**
	 * @param unam: username to check
	 * @return boolean
	 * true if exists
	 * false if doesnt
	 */
	public boolean checkUsername (String uname)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?"; 
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			int res_count = 0;
			
			if(rs.next())
				res_count = rs.getInt(1);
			
			//IF COUNT IS 0 THEN DOESNT EXIST
			if (res_count == 0)
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;	
	}
	//====================================== RETURNS ADMIN FROM USERNAME ====================================================
	/**
	 * @param uname to get admin info from
	 * @return Admin : admin retrieved from db
	 */
	@Override
	public Admin returnAd(String uname)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS THE ADMIN USER FROM THAT USERNAME
			String sql_count = "SELECT * FROM USERS WHERE USERNAME = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			Admin ad = null; 
			if(rs.next())
			{
				ad = new Admin (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						"Admin");
			}
			
			return ad;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//====================================== RETURNS EMPLOYEE FROM USERNAME ====================================================
	/**
	 * @param uname to get employee info from
	 * @return Employee : employee retrieved from db
	 */
	@Override
	public Employee returnEm(String uname)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS THE ADMIN USER FROM THAT USERNAME
			String sql_count = "SELECT * FROM USERS WHERE USERNAME = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			Employee ad = null; 
			if(rs.next())
			{
				ad = new Employee (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						"Employee");
			}	
			return ad;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//====================================== RETURNS CUSTOMER FROM USERNAME ====================================================
	/**
	 * @param uname to get employee info from
	 * @return Customer : Customer retrieved from db
	 */
	@Override
	public Customer returnCus(String uname)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS THE ADMIN USER FROM THAT USERNAME
			String sql_count = "SELECT * FROM USERS WHERE USERNAME = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			Customer ad =null; 
			if(rs.next())
			{
				ad = new Customer (
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						"Customer");
			}
			return ad;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//===================================== GETS THE ROLE OF THE PERSON PASSED IN ============================================
	@Override
	public int checkType(String uname)
	{
		int res_count = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "SELECT ROLE_ID FROM USERS WHERE USERS.USERNAME = ?"; 
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				res_count = rs.getInt(1);
			return res_count;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return res_count;
	}

	//======================================== UPDATE USERNAME SINCE EVERYONE CAN DO THAT ========================================================
	/**
	 * @param old := old username
	 * @param newOne := new username
	 * 
	 */
	@Override
	public boolean updateUsername(String old, String newOne)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "UPDATE USERS SET USERNAME = ? WHERE USERNAME= ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, newOne);
			ps.setString(2, old);
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
	public boolean updatePassword(String old, String newOne)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			//GETS COUNT OF USERS WITH SUCH NAME AND PASSWORD
			String sql_count = "UPDATE USERS SET PW = ? WHERE USERNAME= ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, newOne);
			ps.setString(2, old);
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
