package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.revature.util.ConnectionUtil;

public class DAOEmployeeImpl implements DAOEmployee{
	
	@Override
	public ArrayList<String> loginEmployee(String un, String pw) {
		ArrayList<String> employee = new ArrayList<String>();
		int customerId = 0;
		String password = "";
		//if(prop.isEmpty()){
			System.out.println("empty");
			try(Connection connect = ConnectionUtil.getConnection();){
				connect.setAutoCommit(false);
				
				String sql = "CALL LOGINEMPLOYEE(?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.registerOutParameter(1, java.sql.Types.INTEGER);
				cs.setString(2, un);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			
				customerId = cs.executeUpdate();
				
				customerId = cs.getInt(1);
				password = cs.getString(3);
				
				connect.commit();	
				
				employee.add(Integer.toString(customerId));
				employee.add(password);
		
			}
			catch(SQLException e){
				e.printStackTrace(); 	
			}
	
/*		else{
			try(Connection connect = ConnectionUtil.getConnection(prop);){
				System.out.println("in");
				connect.setAutoCommit(false);
				
				String sql = "CALL LOGINEMPLOYEE(?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.registerOutParameter(1, java.sql.Types.INTEGER);
				cs.setString(2, un);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			
				customerId = cs.executeUpdate();
				
				customerId = cs.getInt(1);
				password = cs.getString(3);
				
				connect.commit();	
				
				employee.add(Integer.toString(customerId));
				employee.add(password);
				System.out.println("employee: " + employee);
		
			}
			catch(SQLException e){
				e.printStackTrace(); 	
			}
		}*/
		
		return employee;
	}

	@Override
	public boolean applyForReimbursement(String event, int eventDate, int time, String location, int formDate,
			String description, int cost, int grading_id, int typeOfEventId, int urgentId) {
		// TODO Auto-generated method stub
		return false;
	}


		
}
