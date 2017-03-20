package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import objects.Employee;
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
	
	
	
	
	
	
	
	
	

}
