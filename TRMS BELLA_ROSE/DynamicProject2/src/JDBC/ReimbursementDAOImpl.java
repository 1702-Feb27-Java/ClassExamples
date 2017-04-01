package JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import Classes.Employee;
import Classes.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO
{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private CallableStatement callStmt;
	private ResultSet rs;

	@Override
	public boolean insertReimbursement(Reimbursement per)
	{
		// TODO Auto-generated method stub
		try
		{
			java.util.Date date = (java.util.Date) Calendar.getInstance().getTime();
			conn = ConnectionFactory.getConnection();

			String query = "Insert into Reimbursement (reimbursement_amnt, employee_id, location, application_date, start_date, end_date, course_Cost, course_Length, approval_Id, course_Id, gradetype_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preStmt = conn.prepareStatement(query);

			String location = per.getLocation();
			Integer empId = per.getEmpId(), courseCost = per.getCourseCost(), courseLength = per.getCourseLength(), approvalId = per.getApprovalId(), courseId = per.getCourseId(), gradeTypeId = per.getGradeTypeId();
			Float reim_amnt = per.getReim_amnt();
			java.sql.Date startDate = formatDate(per.getStartDate()), endDate = formatDate(per.getEndDate()), appDate = formatDate(date);
			
			// Fill out Statement Parameters
			
			preStmt.setFloat(1, reim_amnt);
			preStmt.setInt(2, empId);
			preStmt.setString(3, location);
			preStmt.setDate(4, appDate);
			preStmt.setDate(5, startDate);
			preStmt.setDate(6, endDate);
			preStmt.setInt(7, courseCost);
			preStmt.setInt(8, courseLength);
			preStmt.setInt(9, approvalId);
			preStmt.setInt(10, courseId);
			preStmt.setInt(11, gradeTypeId);
			

			// Execute the Query
			preStmt.executeQuery();
			
			return true;

		} catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}

	}

	@Override
	public LinkedList<Reimbursement> getAllReimbursement()
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Reimbursement";
			stmt = conn.createStatement();

			// Execute the Query
			rs = stmt.executeQuery(query);
			
			LinkedList<Reimbursement> emps = new LinkedList<>();

			while (rs.next())
			{
				
				Reimbursement temp = new Reimbursement();
				
				temp.setReimId(rs.getInt("reimbursement_id"));
				temp.setReim_amnt(rs.getFloat("reimbursement_amnt"));
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setLocation(rs.getString("location"));
				temp.setAppDate(rs.getDate("application_date"));
				temp.setStartDate(rs.getDate("start_date"));
				temp.setEndDate(rs.getDate("end_date"));
				temp.setCourseCost(rs.getInt("course_cost"));
				temp.setCourseLength(rs.getInt("course_length"));
				temp.setApprovalId(rs.getInt("approval_id"));
				temp.setCourseId(rs.getInt("course_id"));
				temp.setGradeTypeId(rs.getInt("gradetype_id"));
				temp.setPassingGrade(rs.getString("passing_grade"));
				temp.setRejectionNote(rs.getString("rejection_note"));
				
				emps.add(temp);
				
			}
			return emps;

			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(stmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}

	@Override
	public LinkedList<Reimbursement> getReimbursementsByApproval_ID(int id)
	{
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Reimbursement where approval_id = ?";
			preStmt = conn.prepareStatement(query);
			
			preStmt.setInt(1, id);

			// Execute the Query
			rs = preStmt.executeQuery();
			
			LinkedList<Reimbursement> emps = new LinkedList<>();

			while (rs.next())
			{
				
				Reimbursement temp = new Reimbursement();
				
				temp.setReimId(rs.getInt("reimbursement_id"));
				temp.setReim_amnt(rs.getFloat("reimbursement_amnt"));
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setLocation(rs.getString("location"));
				temp.setAppDate(rs.getDate("application_date"));
				temp.setStartDate(rs.getDate("start_date"));
				temp.setEndDate(rs.getDate("end_date"));
				temp.setCourseCost(rs.getInt("course_cost"));
				temp.setCourseLength(rs.getInt("course_length"));
				temp.setApprovalId(rs.getInt("approval_id"));
				temp.setCourseId(rs.getInt("course_id"));
				temp.setGradeTypeId(rs.getInt("gradetype_id"));
				temp.setPassingGrade(rs.getString("passing_grade"));
				temp.setRejectionNote(rs.getString("rejection_note"));
				
				emps.add(temp);
				
			}
			return emps;

			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}

	@Override
	public LinkedList<Reimbursement> getReimbursementsByEmployee_ID(int id)
	{
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Reimbursement where employee_id = ?";
			preStmt = conn.prepareStatement(query);
			
			preStmt.setInt(1, id);

			// Execute the Query
			rs = preStmt.executeQuery();
			
			LinkedList<Reimbursement> emps = new LinkedList<>();

			while (rs.next())
			{
				
				Reimbursement temp = new Reimbursement();
				
				temp.setReimId(rs.getInt("reimbursement_id"));
				temp.setReim_amnt(rs.getFloat("reimbursement_amnt"));
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setLocation(rs.getString("location"));
				temp.setAppDate(rs.getDate("application_date"));
				temp.setStartDate(rs.getDate("start_date"));
				temp.setEndDate(rs.getDate("end_date"));
				temp.setCourseCost(rs.getInt("course_cost"));
				temp.setCourseLength(rs.getInt("course_length"));
				temp.setApprovalId(rs.getInt("approval_id"));
				temp.setCourseId(rs.getInt("course_id"));
				temp.setGradeTypeId(rs.getInt("gradetype_id"));
				temp.setPassingGrade(rs.getString("passing_grade"));
				temp.setRejectionNote(rs.getString("rejection_note"));
				
				emps.add(temp);
				
			}
			return emps;

			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}

	@Override
	public Reimbursement getReimbursementByReimbursement_Id(int id)
	{
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Reimbursement where reimbursement_id = ?";
			preStmt = conn.prepareStatement(query);
			
			preStmt.setInt(1, id);

			// Execute the Query
			rs = preStmt.executeQuery();

			if (rs.next())
			{
				
				Reimbursement temp = new Reimbursement();
				
				temp.setReimId(rs.getInt("reimbursement_id"));
				temp.setReim_amnt(rs.getFloat("reimbursement_amnt"));
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setLocation(rs.getString("location"));
				temp.setAppDate(rs.getDate("application_date"));
				temp.setStartDate(rs.getDate("start_date"));
				temp.setEndDate(rs.getDate("end_date"));
				temp.setCourseCost(rs.getInt("course_cost"));
				temp.setCourseLength(rs.getInt("course_length"));
				temp.setApprovalId(rs.getInt("approval_id"));
				temp.setCourseId(rs.getInt("course_id"));
				temp.setGradeTypeId(rs.getInt("gradetype_id"));
				temp.setPassingGrade(rs.getString("passing_grade"));
				temp.setRejectionNote(rs.getString("rejection_note"));
				
				System.out.println("DAO " + temp);
				return temp;
				
			}
			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;
	}

	@Override
	public void saveReimbursement(Reimbursement per)
	{
		// TODO Auto-generated method stub
		
		
				try
				{
					
					conn = ConnectionFactory.getConnection();

					String query = "{call UpdateReim(?, ?, ?, ?)}";
					callStmt = conn.prepareCall(query);

					Integer reim_id = per.getReimId(), approvalId = per.getApprovalId();
					Float reim_amnt = per.getReim_amnt();
					String rejNote = per.getRejectionNote();
					
					// Fill out Statement Parameters
					callStmt.setInt(1, reim_id);
					callStmt.setFloat(2, reim_amnt);
					callStmt.setString(3, rejNote);
					callStmt.setInt(4, approvalId);

					// Execute the Query
					callStmt.executeUpdate();

				} catch (Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(callStmt);
					DAOUtil.close(rs);
				}

	}

	@Override
	public void deleteReimbursementById(int id)
	{
		try
		{
			
			conn = ConnectionFactory.getConnection();

			String query = "{call DeleteReim(?)}";
			callStmt = conn.prepareCall(query);
			
			// Fill out Statement Parameters
			callStmt.setInt(1, id);
			
			// Execute the Query
			callStmt.executeUpdate();

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
	}
	
	public java.sql.Date formatDate(java.util.Date date)
	{
		
	    return new java.sql.Date(date.getTime());
	}
	
	public double getSumOfReim(int id)
	{
		try
		{
			
			conn = ConnectionFactory.getConnection();

			String query = "Select Sum(reimbursement.reimbursement_amnt) from reimbursement where employee_id = ? and approval_id < 11";
			preStmt = conn.prepareStatement(query);
			
			// Fill out Statement Parameters
			preStmt.setInt(1, id);
			
			// Execute the Query
			rs = preStmt.executeQuery();
			
			if (rs.next())
			{
				return rs.getDouble(1);
			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return 0;
		
	}
	
	public int getPercentageByCourseId(int id)
	{
		try
		{
			
			conn = ConnectionFactory.getConnection();

			String query = "Select percentage from coursetype where course_id = ?";
			preStmt = conn.prepareStatement(query);
			
			// Fill out Statement Parameters
			preStmt.setInt(1, id);
			
			// Execute the Query
			rs = preStmt.executeQuery();
			
			if (rs.next())
			{
				return rs.getInt(1);
			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return 0;
		
	}

}
