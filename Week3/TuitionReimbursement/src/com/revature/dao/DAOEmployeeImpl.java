package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		return employee;
	}

	@Override
	public boolean applyForReimbursement(int emp_id, String event, String eventDate, String time, int location, String formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, int approvalCutoff) {
		
		boolean applied = false;
			try(Connection connect = ConnectionUtil.getConnection();){
				connect.setAutoCommit(false);
				
				String sql = "CALL APPLYFORREIMBURSEMENT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.setInt(1, emp_id);
				cs.setString(2, event);
				cs.setString(3, eventDate);
				cs.setString(4, time);
				cs.setInt(5, location);
				cs.setString(6, formDate);
				cs.setString(7, description);
				cs.setInt(8, cost);
				cs.setInt(9, gradingId);
				cs.setInt(10, typeOfEventId);
				cs.setInt(11, urgentId);
				cs.setInt(12, approvalStepId);
				cs.setInt(13, approvalCutoff);
			
				cs.executeUpdate();
				
				connect.commit();	
				
				applied = true;
		
			}
			catch(SQLException e){
				e.printStackTrace(); 	
			}
		
		return applied;
	}

	@Override
	public ArrayList<String> getListOfLocations() {
		ArrayList<String> locations = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT location FROM Location";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String location = rs.getString(1);
				
				locations.add(location);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(locations.isEmpty()){
			locations.add("No Previous Locations");
		}
		return locations;
	}

	
	@Override
	public ArrayList<String> getListOfGradingTypes() {
		
		ArrayList<String> gradingTypes = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT grading FROM Grading";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String gradingType = rs.getString(1);
				
				gradingTypes.add(gradingType);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(gradingTypes.isEmpty()){
			gradingTypes.add("No previous grading types.");
		}
		return gradingTypes;
	}

	public ArrayList<String> getListOfEventTypes(){
		ArrayList<String> eventTypes = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT type_of_event FROM TYPE_OF_EVENT";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String eventType = rs.getString(1);
				
				eventTypes.add(eventType);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(eventTypes.isEmpty()){
			eventTypes.add("No previous event types.");
		}
		return eventTypes;
	}
}
