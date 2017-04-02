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
	public Reimburstment getReim(String pass)
	{
		Reimburstment reim = new Reimburstment();
		int tempID = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT EMP_ID FROM EMPLOYEE WHERE PASS = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, pass);
			ResultSet temp = ps.executeQuery();
			while(temp.next())
			{
				tempID = temp.getInt(1);
			}
			String sql2 = "SELECT * FROM REIMBURSTMENT WHERE EMP_ID = ?";
			PreparedStatement ps2 = connect.prepareStatement(sql2);
			ps2.setInt(1, tempID);
			ResultSet rs = ps2.executeQuery();
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

	@Override
	public void makeReim(Reimburstment reim)
	{
		// TODO Auto-generated method stub
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "INSERT INTO REIMBURSTMENT (EMP_ID, RELOCATION, ADDDATE, "
					+ "STARTDATE, ENDDATE, COURSETIME, COURSECOST, REIMBURST_AMT, "
					+ "APPROVAL_ID, COURSE_ID, GRADETYPE_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?);";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, reim.getEmp_id());
			ps.setString(2, reim.getLocation());
			ps.setString(3, reim.getAddDate());
			ps.setString(4, reim.getCourseStartDate());
			ps.setString(5, reim.getCourseEndDate());
			ps.setString(6, reim.getTime());
			ps.setInt(7, reim.getCourseCost());
			ps.setInt(8, reim.getReimburstAmt());
			ps.setInt(9, reim.getApproval());
			ps.setInt(10, reim.getCourseID());
			ps.setInt(11, reim.getGradeTypeID());
			ps.executeQuery();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Employee> getEmpsInDept(int dept_id)
	{
		Employee emp;
		ArrayList<Employee> temp = new ArrayList();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql = "SELECT * FROM EMPLOYEE WHERE DEPT_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, dept_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				emp = new Employee();
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
				temp.add(emp);
			}
			connect.commit();
			return temp;
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
		return temp;
	}
	
	public ArrayList<Reimburstment> getPendingReim(int emp_id)
	{ //WORKING ON THIS METHOD TO GET THE PENDING REIMBURSEMENT REQUESTS FOR A CERTAIN DEPARTMENT
		Reimburstment reim;
		ArrayList<Reimburstment> temp = new ArrayList();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM REIMBURSTMENT WHERE EMP_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs;
			ps.setInt(1, emp_id);
			rs = ps.executeQuery();
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
