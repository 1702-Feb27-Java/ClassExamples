package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimburstment;
import com.revature.util.ConnectionUtil;

public class DAOImpl implements DAO
{
	public Employee getUser(String user)
	{
		Employee emp = new Employee();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM EMPLOYEE WHERE username = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(8));
				emp.setEmp_id(rs.getInt(1));
				emp.setFname(rs.getString(2));
				emp.setLname(rs.getString(3));
				emp.setAddress(rs.getString(4));
				emp.setDept_id(rs.getInt(5));
				emp.setReportsto(rs.getInt(6));
				emp.setRole_id(rs.getInt(7));
				emp.setUsername(rs.getString(8));
				emp.setPassword(rs.getString(9));
				emp.setEmail(rs.getString(10));
				emp.setAllowence(rs.getInt(11));
			}
			connect.commit();
			return emp;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return emp;
	}
	
	public ArrayList<Employee> getAllUsers()
	{
		Employee temp;
		ArrayList<Employee> tempArray = new ArrayList();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM EMPLOYEE";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				temp = new Employee();
				temp.setEmp_id(rs.getInt(1));
				temp.setFname(rs.getString(2));
				temp.setLname(rs.getString(3));
				temp.setAddress(rs.getString(4));
				temp.setDept_id(rs.getInt(5));
				temp.setReportsto(rs.getInt(6));
				temp.setRole_id(rs.getInt(7));
				temp.setUsername(rs.getString(8));
				temp.setPassword(rs.getString(9));
				temp.setEmail(rs.getString(10));
				temp.setAllowence(rs.getInt(11));
				tempArray.add(temp);
			}
			connect.commit();
			return tempArray;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return tempArray;
	}

	@Override
	public Reimburstment getReim(int reim_id)
	{
		Reimburstment reim = new Reimburstment();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM REIMBURSTMENT WHERE reim_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, reim_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				reim.setReim_id(rs.getInt(1));
				reim.setEmp_id(rs.getInt(2));
				reim.setLocation(rs.getString(3));
				reim.setAddDate(rs.getString(4));
				reim.setCourseStartDate(rs.getString(5));
				reim.setCourseEndDate(rs.getString(6));
				reim.setTime(rs.getString(7));
				reim.setCourseCost(rs.getInt(8));
				reim.setReimburstAmt(rs.getInt(9));
				reim.setApproval(rs.getInt(10));
				reim.setCourseID(rs.getInt(11));
				reim.setGradeTypeID(rs.getInt(12));
			}
			connect.commit();
			return reim;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return reim;
	}

	@Override
	public ArrayList<Reimburstment> getAllReim(int emp_id)
	{
		Reimburstment reim;
		ArrayList<Reimburstment> temp = new ArrayList();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM REIMBURSTMENT WHERE emp_id = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, emp_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				reim = new Reimburstment();
				reim.setReim_id(rs.getInt(1));
				reim.setEmp_id(rs.getInt(2));
				reim.setLocation(rs.getString(3));
				reim.setAddDate(rs.getString(4));
				reim.setCourseStartDate(rs.getString(5));
				reim.setCourseEndDate(rs.getString(6));
				reim.setTime(rs.getString(7));
				reim.setCourseCost(rs.getInt(8));
				reim.setReimburstAmt(rs.getInt(9));
				reim.setApproval(rs.getInt(10));
				reim.setCourseID(rs.getInt(11));
				reim.setGradeTypeID(rs.getInt(12));
				temp.add(reim);
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
	public ArrayList<Reimburstment> allReims()
	{
		Reimburstment reim;
		ArrayList<Reimburstment> temp = new ArrayList();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM REIMBURSTMENT";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				reim = new Reimburstment();
				reim.setReim_id(rs.getInt(1));
				reim.setEmp_id(rs.getInt(2));
				reim.setLocation(rs.getString(3));
				reim.setAddDate(rs.getString(4));
				reim.setCourseStartDate(rs.getString(5));
				reim.setCourseEndDate(rs.getString(6));
				reim.setTime(rs.getString(7));
				reim.setCourseCost(rs.getInt(8));
				reim.setReimburstAmt(rs.getInt(9));
				reim.setApproval(rs.getInt(10));
				reim.setCourseID(rs.getInt(11));
				reim.setGradeTypeID(rs.getInt(12));
				temp.add(reim);
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
}
