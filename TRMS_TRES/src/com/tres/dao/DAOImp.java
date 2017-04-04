package com.tres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tres.objs.CourseType;
import com.tres.objs.Employee;
import com.tres.objs.Message;
import com.tres.objs.Reimbursement;
import com.tres.util.ConnectionUtil;

public class DAOImp implements DAO
{
	//====================================== ADDS USER TO DATABASE (CUSTOMER ONLY) ====================================================
	/**
	 * @param uname : username from new customer
	 * @param pwrod : password from new customer
	 * @return int : rows affected
	 */
	@Override
	public Employee validUser (String uname, String pwd)
	{
		Employee valid = null;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select E.fname, E.username, E.password, E.email, D.dept, E2.fname AS REPTO, R.rolet, E.balance, E2.EMPID AS REPTOID,"
								+ " E.deptid, E.ROLEID, E.EMPID"
								+ " FROM Employee E"
								+ " INNER JOIN Department D"
								+ " ON"
								+ " E.deptid = D.deptid"
								+ " LEFT JOIN Employee E2"
								+ " ON"
								+ " E2.EMPID = E.REPTO"
								+ " INNER JOIN Rolet R"
								+ " ON"
								+ " E.roleid = R.roleid"
								+ " WHERE E.username = ? AND E.password = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())	
			{
				valid = new Employee(rs.getString("FNAME"), rs.getString("DEPT"), rs.getString("REPTO")
									, rs.getString("ROLET"), rs.getString("USERNAME"), rs.getString("PASSWORD")
									, rs.getString("EMAIL"), rs.getInt("BALANCE"), rs.getInt("REPTOID")
									, rs.getInt("DEPTID"), rs.getInt("ROLEID"), rs.getInt("EMPID")
									);
			}
			
			return valid;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return valid;
	}

	//====================================== GET COURSE TYPES FROM DATABASE ====================================================
	/**
	 * @return List of  Course Types
	 */
	public ArrayList<CourseType> getCourseTypes()
	{
		ArrayList<CourseType> valid = new ArrayList<>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select * from course"; 
			Statement ps = connect.createStatement();
			ResultSet rs = ps.executeQuery(sql_count);
			
			while(rs.next())	
			{
				CourseType c = new CourseType(rs.getInt("COURSEID"),rs.getString("COURSETYPE"),rs.getDouble("PERCENTAGE"));
				valid.add(c);
			}
			
			return valid;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return valid;
	}

	//====================================== GET GRADING TYPES FROM DATABASE ====================================================
	@Override
	public ArrayList<String> getGradingTypes()
	{	
		ArrayList<String> valid = new ArrayList<>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select * from GradeType"; 
			Statement ps = connect.createStatement();
			ResultSet rs = ps.executeQuery(sql_count);
			
			while(rs.next())	
			{
				valid.add(rs.getString("GRADINGTYPE"));
			}
			
			return valid;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return valid;
	}

	//====================================== INSERT REIMBURSMENT TO DATABASE ====================================================
	@Override
	public int insertReim(Reimbursement r)
	{
			int valid = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "INSERT INTO REIMBURSEMENT (EMPID,LOC,FORMTM,COURSEDESCR,COSTCRS,GRADEID,COURSEID,WORKJST,TMMISS,CRSSTART,CRSEND,APPRID,FORMDT,GRADEVAL, CURR_ON, SID) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, r.getEmid());
			ps.setString(2, r.getLoc());
			ps.setString(3, r.getFormtm());
			ps.setString(4, r.getCoursedes());
			ps.setDouble(5, r.getCost());
			ps.setInt(6, r.getGradeid());
			ps.setInt(7, r.getCourseid());
			ps.setString(8, r.getWorkjus());
			ps.setString(9, r.getTmmiss());
			ps.setString(10, r.getCrsStart());
			ps.setString(11, r.getCrsEnd());
			ps.setInt(12, r.getApprod());
			ps.setString(13, r.getFormdt());
			ps.setString(14, r.getGradeval());
			ps.setInt(15, r.getCurr_on());
			ps.setInt(16, r.getSid());
			ResultSet rs = ps.executeQuery();

			String count = "Select reim_seq.currval from dual"; 
			Statement pss = connect.createStatement();
			ResultSet rss = ps.executeQuery(count);
			
			while(rss.next())	
			{
				valid = rss.getInt(1);
			}
			
			return valid;	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//System.out.println(r.toSql());
		// TODO Auto-generated method stub
		return valid;
	}

	//====================================== INSERT ATTACHMENT TO DATABASE ====================================================
	@Override
	public boolean addAtch(int id, int reim, String loc)
	{
		boolean valid = false;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "INSERT INTO ATTACHMENTS (FILLOC, REIMID) VALUES (?,?)";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setString(1, loc);
			ps.setInt(2, reim);
			int rs = ps.executeUpdate();
			if(rs == 1)
			{
				valid = true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return valid;
	}

	//====================================== GET PENDING REIMBURSEMENT FROM DATABASE ====================================================
	@Override
	public ArrayList<Reimbursement> getPending(int uid)
	{
		ArrayList<Reimbursement> valid = new ArrayList<>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select R.*, A.FILLOC, S.STAT from REIMBURSEMENT R"
								+ " LEFT JOIN ATTACHMENTS A  ON A.REIMID = R.REIMID"
								+ " INNER JOIN STATUS S ON S.SID = R.SID"
								+ " WHERE R.EMPID = ?"; 
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())	
			{
				 Reimbursement r = new Reimbursement
				    		(
								//EMID
				    			rs.getInt("EMPID"),
								//Location
				    			rs.getString("LOC"),
								//TIME
				    			rs.getString("FORMTM"),
								//COURSE DES
				    			rs.getString("COURSEDESCR"),
				    			//COST
				    			rs.getDouble("COSTCRS"),
				    			//GRADEID
				    			rs.getInt("GRADEID"),
				    			//courseid
				    			rs.getInt("COURSEID"),
				    			//workjus
				    			rs.getString("WORKJST"),
				    			//tmmiss
				    			rs.getString("TMMISS"),
				    			//crsStart
				    			rs.getString("CRSSTART"),
				    			//crsEnd
				    			rs.getString("CRSEND"),
				    			//approd
				    			rs.getInt("APPRID"),
				    			//formdt
				    			rs.getString("FORMDT"),
				    			//gradeval
				    			rs.getString("GRADEVAL"),
				    			//curr_on
				    			rs.getInt("CURR_ON"),
				    			//status
				    			rs.getInt("SID")
				    		);
				 r.setAttch(rs.getString("FILLOC"));
				 r.setReimid(rs.getInt("REIMID"));
				 r.setStatus(rs.getString("STAT"));
				 
				 //SELECT MESSAGES THAT MATCH CURRENT REIM
				 String msg_count = "Select M.*, S.USERNAME as SNAME, Rcv.USERNAME AS RNAME FROM MESSAGES M"
				 					+ "	INNER JOIN EMPLOYEE S on M.sender = S.empid"
				 					+ " INNER JOIN EMPLOYEE Rcv on M.receiver = Rcv.empid"
				 					+ " WHERE M.reimid = ?";
				 
				 PreparedStatement p = connect.prepareStatement(msg_count);
				 p.setInt(1,r.getReimid());
				 ArrayList<Message> m = new ArrayList<>();
				 ResultSet rs2 = p.executeQuery();
				 while (rs2.next())
				 {
					 Message m2 = new Message(rs2.getInt("MSGID"), rs2.getInt("SENDER"), rs2.getInt("RECEIVER"), rs2.getInt("REIMID"), rs2.getString("MSG"), rs2.getString("SNAME"), rs2.getString("RNAME"),rs2.getInt("RINFO"));
					 m.add(m2);
				 }
				 r.setMsgs(m);
				 System.out.println(r.toString());
				 valid.add(r);
			}
			
			return valid;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return valid;
	}
	
	//====================================== GET PENDING REIMBURSEMENT TO ACCEPT FROM DATABASE ====================================================
	@Override
	public ArrayList<Reimbursement> getHvPending(int uid)
	{
		ArrayList<Reimbursement> valid = new ArrayList<>();
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select R.*, A.FILLOC, S.STAT from REIMBURSEMENT R"
								+ " LEFT JOIN ATTACHMENTS A  ON A.REIMID = R.REIMID"
								+ " INNER JOIN STATUS S ON S.SID = R.SID"
								+ " WHERE R.CURR_ON = ?"; 
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1,uid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())	
			{
				 Reimbursement r = new Reimbursement
				    		(
								//EMID
				    			rs.getInt("EMPID"),
								//Location
				    			rs.getString("LOC"),
								//TIME
				    			rs.getString("FORMTM"),
								//COURSE DES
				    			rs.getString("COURSEDESCR"),
				    			//COST
				    			rs.getDouble("COSTCRS"),
				    			//GRADEID
				    			rs.getInt("GRADEID"),
				    			//courseid
				    			rs.getInt("COURSEID"),
				    			//workjus
				    			rs.getString("WORKJST"),
				    			//tmmiss
				    			rs.getString("TMMISS"),
				    			//crsStart
				    			rs.getString("CRSSTART"),
				    			//crsEnd
				    			rs.getString("CRSEND"),
				    			//approd
				    			rs.getInt("APPRID"),
				    			//formdt
				    			rs.getString("FORMDT"),
				    			//gradeval
				    			rs.getString("GRADEVAL"),
				    			//curr_on
				    			rs.getInt("CURR_ON"),
				    			//status id
				    			rs.getInt("SID")
				    		);
				 r.setAttch(rs.getString("FILLOC"));
				 r.setReimid(rs.getInt("REIMID"));
				 r.setStatus(rs.getString("STAT"));
				 String sql = "Select USERNAME from EMPLOYEE E WHERE E.EMPID = ?"; 
				 PreparedStatement ps2 = connect.prepareStatement(sql);
				 ps2.setInt(1,r.getEmid());
				 ResultSet rs2 = ps2.executeQuery();
				 while (rs2.next())
				 {
					 r.setCreator(rs2.getString("USERNAME"));
				 }
				 valid.add(r);
			}
			
			return valid;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return valid;
	}

	//====================================== UPDATE REIMBURSMENT AND ADD TO MSGS WITH VALUES
	@Override
	public void updateAddMsg(int snd, int rcv, String msg, int reimid, int sid)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "UPDATE REIMBURSEMENT SET SID= ?  WHERE REIMID = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, sid);
			ps.setInt(2, reimid);
			int rs = ps.executeUpdate();
			if (rs> 0)
			{
				String msgsql = "INSERT INTO MESSAGES (SENDER, RECEIVER, MSG, REIMID) values (?,?,?,?)";
				PreparedStatement p = connect.prepareStatement(msgsql);
				p.setInt(1, snd);
				p.setInt(2, rcv);
				p.setString(3, msg);
				p.setInt(4, reimid);
				p.executeUpdate();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	//====================================== UPDATE REIMBURSMENT MOVE UP AND ADD TO MSGS WITH VALUES
	@Override
	public void updateReim(int reimid, int apprid, int repid,int send, int recv, String msg)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "UPDATE REIMBURSEMENT SET APPRID= ?, CURR_ON = ? WHERE REIMID = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, apprid);
			ps.setInt(2, repid);
			ps.setInt(3, reimid);
			int rs = ps.executeUpdate();
			if (rs> 0)
			{
				String msgsql = "INSERT INTO MESSAGES (SENDER, RECEIVER, MSG, REIMID) values (?,?,?,?)";
				PreparedStatement p = connect.prepareStatement(msgsql);
				p.setInt(1, send);
				p.setInt(2, recv);
				p.setString(3, msg);
				p.setInt(4, reimid);
				p.executeUpdate();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}

	//====================================== ADD MESSAGES =================================================
	@Override
	public void addMsg(int reimid, int s, int r, String reason)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String msgsql = "INSERT INTO MESSAGES (SENDER, RECEIVER, MSG, REIMID, RINFO) values (?,?,?,?,1)";
			PreparedStatement p = connect.prepareStatement(msgsql);
			p.setInt(1, s);
			p.setInt(2, r);
			p.setString(3, reason);
			p.setInt(4, reimid);
			p.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		
	}


	//====================================== GET BALANCE OF USER =================================================
	@Override
	public double getBalance(int ownId)
	{
		double valid = 0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "Select BALANCE FROM EMPLOYEE WHERE EMPID = ?"; 
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, ownId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())	
			{
				valid = rs.getDouble("BALANCE");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
			return valid;
	}


	//====================================== 
	@Override
	public void updateUser(int rcv_id, double crscost)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "UPDATE EMPLOYEE SET BALANCE= ? WHERE EMPID = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setDouble(1, crscost);
			ps.setInt(2, rcv_id);
			int rs = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	//==================================

	@Override
	public void updateReimAmnt(int reim_id, double cst)
	{
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "UPDATE REIMBURSEMENT SET COSTCRS= ? WHERE REIMID = ?";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setDouble(1, cst);
			ps.setInt(2, reim_id);
			int rs = ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	//==================================================
	@Override
	public double getPendingAmnt(int id)
	{
		double amnt=0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "SELECT SUM(COSTCRS) FROM REIMBURSEMENT WHERE EMPID = ? AND SID = 1";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				amnt = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return amnt;
	}
	
	@Override
	public double getAwardAmnt(int id)
	{
		double amnt=0;
		try(Connection connect = ConnectionUtil.getConnection();)
		{
			connect.setAutoCommit(false);
			String sql_count = "SELECT SUM(COSTCRS) FROM REIMBURSEMENT WHERE EMPID = ? AND SID = 3";
			PreparedStatement ps = connect.prepareStatement(sql_count);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				amnt = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return amnt;
	}
}
