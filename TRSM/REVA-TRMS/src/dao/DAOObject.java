package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import objects.Employee;
import objects.Reimburse;
import connect.ConnectionUtil;




public class DAOObject {
	/** 
	 * PREPAIRED QUESTION
	 * get all the employees in an arraylist
	 */	
	public ArrayList<Employee> getAllEmployees() {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM EMPLOYEE";
			
			PreparedStatement ps = connect.prepareStatement(sql);			
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Employee> temp = new ArrayList<Employee>();
			
			while(rs.next()){
				
				Employee n = new Employee();
				n.setFirstName(rs.getString("FIRST_NAME"));
				n.setEmail(rs.getString("EMAIL"));
				n.setLastName(rs.getString("LAST_NAME"));
				n.setPassword(rs.getString("PASS"));
				try{
					n.setReportsto(rs.getInt("REPORTSTO"));
				}catch(Exception e){
					n.setReportsto(0);
				}
				n.setPending(rs.getInt("PENDING_RE"));
				n.setAwarded(rs.getInt("AWARDED_RE"));
				n.setUserName(rs.getString("USERNAME"));
				n.setDepart(this.getDepartment(n.getUserName()));
				n.setRole(this.getRole(n.getUserName()));
				
				//set role/department
				
				
				temp.add(n);
				
			}
			
			
			connect.commit();
			connect.setAutoCommit(true);
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getRole(String userName){
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT ROLE_NAME FROM ROLE, EMPLOYEE WHERE EMPLOYEE.USERNAME = ? AND ROLE.ROLE_ID = EMPLOYEE.ROLE_ID";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			
			
			ResultSet rs = ps.executeQuery();
			String temp = null;
			while(rs.next()){
				temp = rs.getString("ROLE_NAME");				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			
			
			//// put get savings/ account balance here;
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getDepartment(String userName){
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT DEPARTMENT_NAME FROM DEPARTMENT, EMPLOYEE WHERE EMPLOYEE.USERNAME = ? AND DEPARTMENT.D_ID = EMPLOYEE.DEPARTMENT_ID";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			
			
			ResultSet rs = ps.executeQuery();
			String temp = null;
			while(rs.next()){
				temp = rs.getString("DEPARTMENT_NAME");				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			
			
			//// put get savings/ account balance here;
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public ArrayList<Reimburse> getAllReimburse(){
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM REIMBURSE";
			
			PreparedStatement ps = connect.prepareStatement(sql);			
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimburse> temp = new ArrayList<Reimburse>();
			
			while(rs.next()){
				
				Reimburse re = this.getReimbursement(rs.getInt("REIM_ID"));				
				
				
				temp.add(re);
				
			}
			
			
			connect.commit();
			connect.setAutoCommit(true);
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Attempts to get the bankmember based on their userName
	 * @param userName the username to get
	 * @return the bankmember with a matching username
	 */
	public Employee getEmployee(String userName) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM Employee WHERE USERNAME = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			
			ResultSet rs = ps.executeQuery();
			Employee n = new Employee();
			
			//gets the info
			while(rs.next()){
			
				n.setFirstName(rs.getString("FIRST_NAME"));
				
				n.setEmail(rs.getString("EMAIL"));
				
				n.setLastName(rs.getString("LAST_NAME"));
				
				n.setPassword(rs.getString("PASS"));
				
				try{
					n.setReportsto(rs.getInt("REPORTSTO"));
					
				}catch(Exception e){
					n.setReportsto(0);
				}
				n.setPending(rs.getInt("PENDING_RE"));
				
				n.setAwarded(rs.getInt("AWARDED_RE"));
				
				n.setUserName(rs.getString("USERNAME"));
				
				n.setDepart(this.getDepartment(n.getUserName()));
				
				n.setRole(this.getRole(n.getUserName()));
				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			//if its a customer i need more info
			
			//// put get savings/ account balance here;
			return n;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public Employee getEmployee(int id) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM Employee WHERE EMPLOYEE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			Employee n = new Employee();
			
			//gets the info
			while(rs.next()){
			
				n.setFirstName(rs.getString("FIRST_NAME"));
				
				n.setEmail(rs.getString("EMAIL"));
				
				n.setLastName(rs.getString("LAST_NAME"));
				
				n.setPassword(rs.getString("PASS"));
				
				try{
					n.setReportsto(rs.getInt("REPORTSTO"));
					
				}catch(Exception e){
					n.setReportsto(0);
				}
				n.setPending(rs.getInt("PENDING_RE"));
				
				n.setAwarded(rs.getInt("AWARDED_RE"));
				
				n.setUserName(rs.getString("USERNAME"));
				
				n.seteId(rs.getInt("EMPLOYEE_ID"));
				
				n.setDepart(this.getDepartment(n.getUserName()));
				
				n.setRole(this.getRole(n.getUserName()));
				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			//if its a customer i need more info
			
			//// put get savings/ account balance here;
			return n;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int addRequest(String username, Reimburse req, Employee em, int isUrgent) {
		int temp = 0;
		
	
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL addReim(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, req.getEvent_date());
			cs.setString(3, req.getEventLength());
			cs.setString(4, req.getLocation());
			cs.setString(5, req.getDescription());
			cs.setInt(6, req.getCost());
			cs.setInt(7, req.getGrade());
			cs.setString(8, req.getJustification());
			cs.setInt(9, req.getCourseID());
			cs.setInt(10, 0);
			
			
			if(em.getReportsto() == 0){ //he is the department head
				cs.setInt(11, 5);
				cs.setInt(12, 22);
			}
			else if(this.isBossDepartmentHead(em.getReportsto())){
				cs.setInt(11, 3);
				cs.setInt(12, em.getReportsto());
			}
			else{
				cs.setInt(11, 1);
				cs.setInt(12, em.getReportsto());
			}
			cs.setInt(13, isUrgent );
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	
	public Reimburse getReimbursement(int id) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM REIMBURSE WHERE REIM_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			Reimburse n = new Reimburse();
			
			//gets the info
			while(rs.next()){
				n.setCost(rs.getInt("REIMBURSE_COST"));
				n.setCourseID(rs.getInt("COURSE_ID"));
				n.setDescription(rs.getString("DESCRIPTION"));
				n.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				n.setEvent_date(rs.getString("EVENT_DATE"));
				n.setEventLength(rs.getString("EVENT_TIME"));
				n.setGrade(rs.getInt("GRADE_FORMAT"));
				n.setJustification(rs.getString("JUSTIFICATION"));
				n.setLocation(rs.getString("LOCATION"));
				n.setNumDay(rs.getInt("NUM_DAY"));
				n.setReim_id(rs.getInt("REIM_ID"));
				n.setIsUrgent(rs.getInt("URGENT"));
				
				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			//if its a customer i need more info
			
			//// put get savings/ account balance here;
			return n;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void updateStatus(int id, int stat) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "UPDATE APPROVE SET APPROVE.STATUS_NUM = ? WHERE APPROVE.R_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1,stat);
			ps.setInt(2,id);
			
			ResultSet rs = ps.executeQuery();
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public void updateNumDay(int id) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "UPDATE REIMBURSE SET NUM_DAY = 0 WHERE REIM_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	public void updateApprover(int id, int eI) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "UPDATE APPROVE SET APPROVE.E_ID = ? WHERE APPROVE.R_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1,eI);
			ps.setInt(2,id);
			
			ResultSet rs = ps.executeQuery();
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	public int getStatus(int id) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT APPROVE.STATUS_NUM FROM APPROVE WHERE APPROVE.R_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			int temp = 0;
			while(rs.next()){
				temp = rs.getInt("STATUS_NUM");
			}
				return temp;	
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public boolean isBossDepartmentHead(int id){
		Employee boss = this.getEmployee(id);
		if(boss.getReportsto() == 0) return true;
		
		return false;
	}
	
	
	
	public void updatePending(String userName, int newCost) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE.PENDING_RE = ? WHERE EMPLOYEE.USERNAME = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1,newCost);
			ps.setString(2,userName);
			
			ResultSet rs = ps.executeQuery();
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public void updateAwarded(String userName, int newCost) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "UPDATE EMPLOYEE SET EMPLOYEE.AWARDED_RE = ? WHERE EMPLOYEE.USERNAME = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1,newCost);
			ps.setString(2,userName);
			
			ResultSet rs = ps.executeQuery();
			
			
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public int addMessage(String message, int req, int whoSent) {
		int temp = 0;
		
	
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL insertMessage(?, ?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, req);
			cs.setString(2, message);
			cs.setInt(3, whoSent);
			
			
			
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> getMessage(int reID){
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT MESSAGE.MESSAGE_BODY FROM MESSAGE WHERE MESSAGE.R_ID = ? ORDER BY MESSAGE_ID ASC";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1,reID);
		
			
			ResultSet rs = ps.executeQuery();
			ArrayList<String> temp = new ArrayList<String>();
			while(rs.next()){				
				temp.add(rs.getString("MESSAGE_BODY"));
				
			}
			
				return temp;	
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void advanceDay() {
		int temp = 0;
		
	
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			
			String sql = "CALL advanceDay()";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.executeUpdate();
			
			
			
//			connect.commit();
			connect.setAutoCommit(true);			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	

}
